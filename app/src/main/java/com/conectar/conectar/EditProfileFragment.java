package com.conectar.conectar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import util.SessionUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class EditProfileFragment extends Fragment {

    private EditText bio; //bio to be edited by user
    private EditText newInterest; //todo this must be changed
    private String interests; //String with user's interest
    private int numInterests; //int with number of interest user currently has

    private OnFragmentInteractionListener mListener;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LogoutFragment.
     */
    //Rename and change types and number of parameters
    public static EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { //------------------------------------------------------------------------------onCreate
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //create variables that take input from UI
        bio = view.findViewById(R.id.bio);
        //TODO remove comments
        //newInterest = view.findViewById(R.id.newInterest);
        //set all the user info
        bio.setText(SessionUtil.getSessionBio());
        interests = SessionUtil.getSessionInterests();
        //int to keep track of the current number of interests
        numInterests = interests.charAt(0) - '0';
        //create a button to add an interest
        //TODO remove comments
        /*
        view.findViewById(R.id.submitInterest).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //todo update all of this
                //if there are not yet 5 interests, it can be added
                if(numInterests < 5){
                    //-------------------psuedo code to be implemented-------------------------------//
                    //update the string to add the interest
                    //increment the number of interests
                    //update the string with new number of interests
                    //toast to let the user know it worked
                    //update the server
                    numInterests++;
                    //-------------------------------------------------------------------------------//
                }
                else{
                    //let the user know there are too many interests to add another
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Interests are full";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
        */

        view.findViewById(R.id.interestOne).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 1){
                    //-----------------------------pseudo code to be implemented---------------------------//
                    //move every interest down one place starting at the first
                    //decrement number of interests
                    //update the server
                    numInterests--;
                    //-------------------------------------------------------------------------------------//
                }
            }
        });
        view.findViewById(R.id.interestTwo).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 2){
                    //-----------------------------pseudo code to be implemented---------------------------//
                    //move every interest down one place starting at the second
                    //decrement number of interests
                    //update server
                    numInterests--;
                    //------------------------------------------------------------------------------------//
                }
            }
        });
        view.findViewById(R.id.interestThree).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 3){
                    //-----------------------------pseudo code to be implemented---------------------------//
                    //move every interest down one place starting at the third
                    //decrement number of interests
                    //update server
                    numInterests--;
                    //------------------------------------------------------------------------------------//
                }
            }
        });
        view.findViewById(R.id.interestFour).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 4){
                    //-----------------------------pseudo code to be implemented---------------------------//
                    //move every interest down one place starting at the fourth
                    //decrement number of interests
                    //update server
                    numInterests--;
                }
            }
        });
        view.findViewById(R.id.interestFive).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 5){
                    //-----------------------------pseudo code to be implemented---------------------------//
                    //delete the last interest
                    //decrement number of interests
                    //update server
                    //-------------------------------------------------------------------------------------//
                    numInterests--;
                }
            }
        });
        //For debugging, outputs all the current values in interests
        view.findViewById(R.id.debugButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //-----------------------------pseudo code to be implemented---------------------------//
                //can use this to debug if needed
                //-------------------------------------------------------------------------------------//
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
        // Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}

