package com.conectar.conectar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import util.JsonRequest;
import util.UserUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReportFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private boolean bio_problem; //whether or not there is a bio problem
    private boolean message_problem; //whether or not there is a message problem
    private boolean other_problem; //whether or not there is an other problem
    private JSONObject report; //the report object
    private EditText details; //the details from the user
    private TextView message; //message to the user about how they have set their problem location
    private View mainView; //view from main
    private String baseUrl = "http://proj-309-ss-4.cs.iastate.edu:9001/ben/report/";
    private int userId;
    private int reportedId;
    private Context context;
    //types of problem
    enum problem{
        BIO, MESSAGE, OTHER
    }

    public ReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ReportFragment.
     */
    public static ReportFragment newInstance() {
        ReportFragment fragment = new ReportFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)

        mainView = view; //save the view in main
        context = getContext();
        userId = preferences.getInt("ID", 0); //get int of the user reporting
        reportedId = 0;
        report = new JSONObject(); //report object to send
        JSONObject reported = UserUtil.getUserToView();
        try{
            reportedId = reported.getInt("id");

            //make reporter object
            JSONObject reporter = new JSONObject();
            reporter.put("userName", preferences.getString("USERNAME", ""));
            reporter.put("bio", preferences.getString("BIO", ""));
            reporter.put("id", preferences.getInt("ID", 0));
            reporter.put("status", preferences.getInt("STATUS", 0));
            reporter.put("interests", preferences.getString("INTERESTS", "00000000000"));
            reporter.put("profilePicture", preferences.getInt("PROFILEPICTURE", 0));

            report.put("reporter", reporter); //put the user sending the report's id in the report
            report.put("reported", reported);
        }catch (JSONException e){
            e.printStackTrace();
        }
        message = view.findViewById(R.id.problem_message); //text for the message to the user

        view.findViewById(R.id.bio_problem).setOnClickListener(new View.OnClickListener() { //red button clicked
            @Override
            public void onClick(View view) {
                changeProblem(problem.BIO); //toggle the bio problem
            }
        });
        view.findViewById(R.id.message_problem).setOnClickListener(new View.OnClickListener() { //red button clicked
            @Override
            public void onClick(View view) {
                changeProblem(problem.MESSAGE); //toggle the message problem
            }
        });
        view.findViewById(R.id.other_problem).setOnClickListener(new View.OnClickListener() { //red button clicked
            @Override
            public void onClick(View view) {
                changeProblem(problem.OTHER); //toggle the other problem
            }
        });

        view.findViewById(R.id.submit_report).setOnClickListener(new View.OnClickListener() { //red button clicked
            @Override
            public void onClick(View view) {
                int problem = 0; //create an int to store the problem
                //add the amount for each type of problem
                if(bio_problem){
                    problem += 1;
                }
                if(message_problem){
                    problem += 2;
                }
                if(other_problem){
                    problem += 4;
                }
                //if no problem is set, the user needs to set a problem
                if(problem == 0){
                    Toast.makeText(getActivity(), "Please indicate where the problem is located", Toast.LENGTH_SHORT).show(); //toast to tell the user they need to set a problem location
                }
                else {
                    details = mainView.findViewById(R.id.report_details); //details from the user
                    String d = details.getText().toString(); //string of the details
                    //if it has not been changed, the user needs to provide details
                    if(d.equals("details here")){
                        Toast.makeText(getActivity(), "Please give details", Toast.LENGTH_SHORT).show(); //toast to tell the user they need to give details
                    }
                    else {
                        addToReport(problem, d); //add the problem and details to the report

                        //create the url and send the report
                        baseUrl += getRepId() + "/from/" + getid();
                        UserUtil.postReportRequest(baseUrl, context, report);

                        //tell the user it was successful
                        Toast.makeText(getActivity(), "Your report has been submitted. You may be contacted for further information", Toast.LENGTH_LONG).show(); //toast to tell the user they need to give details
                        //create the new profile view fragment
                        Fragment fragment = new ProfileViewFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.screen_area, fragment);
                        fragmentTransaction.commit();
                    }
                }
            }
        });



    }

    /**
     * method to return the id of the reported user
     * @return reported user id
     */
    private String getRepId(){
        return reportedId + "";
    }

    /**
     * method to return the id of the current user
     * @return reporter user id
     */
    private String getid(){
        return userId + "";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    /**
     * method used to toggle whether or not the problem is in bio, message, or other
     * @param prob enum for problem to toggle
     */
    public void changeProblem(problem prob){
        //toggle the correct problem
        if(prob == problem.BIO){
            bio_problem = !bio_problem;
        }
        else if(prob == problem.MESSAGE){
            message_problem = !message_problem;
        }
        else if(prob == problem.OTHER){
            other_problem = !other_problem;
        }
        //update the UI so the user knows what they have selected
        if(bio_problem){
            if(message_problem){
                if(other_problem){
                    message.setText("You have indicated a problem in the bio, messages, and other");
                }
                else{
                    message.setText("You have indicated a problem in the bio and messages");
                }
            }
            else if(other_problem){
                message.setText("You have indicated a problem in the bio and other");
            }
            else{
                message.setText("You have indicated a problem in the bio");
            }
        }
        else if(message_problem){
            if(other_problem){
                message.setText("You have indicated a problem in the messages and other");
            }
            else{
                message.setText("You have indicated a problem in the messages");
            }
        }
        else if(other_problem){
            message.setText("You have indicated an other problem");
        }
        else {
            message.setText("You have not yet indicated where the problem is");
        }
        return;
    }

    /**
     * method used to create the button within the listener for submit report
     * @param reason int indicating the reason for reporting
     * @param details string detailing the report
     */
    public void addToReport(int reason, String details){
        //add these details to the report
        try{
            report.put("reason", reason);
            report.put("details", details);
        }catch (JSONException e){
            e.printStackTrace();
        }
        Log.d("report", report.toString());
        return;
    }


}
