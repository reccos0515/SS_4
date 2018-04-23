package com.conectar.conectar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import util.UserUtil;

//import org.omg.CORBA.Current;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChangeStatusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChangeStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 * This class allows the user to change their current status between red,
 * yellow, and green
 */
public class ChangeStatusFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ChangeStatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChangeStatusFragment.
     */
    //Rename and change types and number of parameters
    public static ChangeStatusFragment newInstance() {
        ChangeStatusFragment fragment = new ChangeStatusFragment();
        return fragment;
    }

    /**
     * method to be called when the fragment is fist created
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Method to be called when the view is being created.
     * Creates the view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_status, null); //opens the change status screen
    }

    /**
     * Method to be called once the view has been created. This sets up the UI
     * and all buttons that will be used
     * This is where most of the specific page code is implemented
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)

        final TextView currentStatus = view.findViewById(R.id.current_status); //message to tell user their current status
        //set the user a message to tell them what their current status is
        switch (preferences.getInt("STATUS", 0)){
            case 0 :
                currentStatus.setText("Status is Red");
                break;
            case 1 :
                currentStatus.setText("Status is Yellow");
                break;
            case 2 :
                currentStatus.setText("Status is Green");
                break;
        }

        view.findViewById(R.id.greenStatusBtn).setOnClickListener(new View.OnClickListener() { //green button clicked
            @Override
            public void onClick(View view) { //set status to green
                //can set it as long as it was not already green
                if(preferences.getInt("STATUS", 0) != 2) {
                    UserUtil.updateStatus(2, getContext(), getFragmentManager()); //call this to update the status
                    currentStatus.setText("Status is Green");
                    Toast.makeText(getActivity(), "Status set to green", Toast.LENGTH_SHORT).show(); //toast to tell user it worked
                } else {
                    Toast.makeText(getActivity(), "Your status was already green, silly", Toast.LENGTH_SHORT).show(); //toast to tell user they were already green
                }
            }
        });
        view.findViewById(R.id.yellowStatusBtn).setOnClickListener(new View.OnClickListener(){ //yellow button clicked
            @Override
            public void onClick(View view){ //set status to yellow
                //can set it as long as it was not already yellow
                if(preferences.getInt("STATUS", 0) != 1) {
                    UserUtil.updateStatus(1, getContext(), getFragmentManager()); //call this to update the status
                    currentStatus.setText("Status is Yellow");
                    Toast.makeText(getActivity(), "Status set to yellow", Toast.LENGTH_SHORT).show(); //toast to tell the user it worked
                } else {
                    Toast.makeText(getActivity(), "Your status was already yellow, silly", Toast.LENGTH_SHORT).show(); //toast to tell user they were already yellow
                }
            }
        });
        view.findViewById(R.id.redStatusBtn).setOnClickListener(new View.OnClickListener() { //red button clicked
            @Override
            public void onClick(View view) { //set status to red
                //can set it as long as it was not already red
                if(preferences.getInt("STATUS", 0) != 0) {
                    UserUtil.updateStatus(0, getContext(), getFragmentManager()); //call this to update the status
                    currentStatus.setText("Status is Red");
                    Toast.makeText(getActivity(), "Status set to red", Toast.LENGTH_SHORT).show(); //toast to let the user know it worked
                } else {
                    Toast.makeText(getActivity(), "Your status was already red, silly", Toast.LENGTH_SHORT).show(); //toast to tell user they were already red
                }
            }
        });
        view.findViewById(R.id.changeStatusSwipeBtn).setOnClickListener(new View.OnClickListener() { //red button clicked
            @Override
            public void onClick(View view) { //return to swipe
                Fragment fragment = new SwipeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment);
                fragmentTransaction.commit();
            }
        });

    }

    /**
     * method to be called when the fragment is being attached
     * @param context
     */
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

    /**
     * Method to be called when the fragment is being detached (closing)
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * interface for the interaction listener
     */
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
        //Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
