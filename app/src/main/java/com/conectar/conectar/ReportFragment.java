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
    private boolean bio_problem; //1
    private boolean message_problem; //2
    private boolean other_problem; //4
    private JSONObject report;
    private EditText details;
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

        int userId = preferences.getInt("ID", 0); //get int of the user reporting
        report = new JSONObject(); //report object to send
        try{
            report.put("reported", userId); //put the user sending the report's id in the report
        }catch (JSONException e){
            e.printStackTrace();
        }

        view.findViewById(R.id.bio_problem).setOnClickListener(new View.OnClickListener() { //red button clicked
            @Override
            public void onClick(View view) {
                changeProblem(problem.BIO);
            }
        });
        view.findViewById(R.id.message_problem).setOnClickListener(new View.OnClickListener() { //red button clicked
            @Override
            public void onClick(View view) {
                changeProblem(problem.MESSAGE);
            }
        });
        view.findViewById(R.id.other_problem).setOnClickListener(new View.OnClickListener() { //red button clicked
            @Override
            public void onClick(View view) {
                changeProblem(problem.OTHER);
            }
        });

        view.findViewById(R.id.submit_report).setOnClickListener(new View.OnClickListener() { //red button clicked
            @Override
            public void onClick(View view) {
                int problem = 0;
                if(bio_problem){
                    problem += 1;
                }
                if(message_problem){
                    problem += 2;
                }
                if(other_problem){
                    problem += 4;
                }
                if(problem == 0){
                    Toast.makeText(getActivity(), "Please indicate where the problem is located", Toast.LENGTH_LONG).show(); //toast to tell the user they need to set a problem location
                }
                else {
                    details = view.findViewById(R.id.report_details);
                    String d = details.getText().toString();
                    addToReport(problem, d);
                }
            }
        });



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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * method used to toggle whether or not the problem is in bio, message, or other
     * @param prob enum for problem to toggle
     */
    public void changeProblem(problem prob){
        if(prob == problem.BIO){
            bio_problem = !bio_problem;
        }
        else if(prob == problem.MESSAGE){
            message_problem = !message_problem;
        }
        else if(prob == problem.OTHER){
            other_problem = !other_problem;
        }
        return;
    }

    /**
     * method used to create the button within the listener for submit report
     * @param reason int indicating the reason for reporting
     * @param details string detailing the report
     */
    public void addToReport(int reason, String details){
        try{
            report.put("reason", reason);
            report.put("details", details);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return;
    }


}
