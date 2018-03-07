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

    private EditText bio;
    private EditText newInterest;
    //TODO figure out how to use delete interest buttons with interests at size 5.  Extra space here is unideal
    private String[] interests = new String[6];
    private int numInterests;

    private OnFragmentInteractionListener mListener;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogoutFragment.
     */
    //Rename and change types and number of parameters
    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        newInterest = view.findViewById(R.id.newInterest);
        //int to keep track of the current number of interests
        numInterests = 0;
        //create a button to add an interest
        view.findViewById(R.id.submitInterest).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //if there are not yet 5 interests, it can be added
                if(numInterests < 5){
                    //toast to let the user know it worked
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Interests are not full";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    //add the interest
                    interests[numInterests] = newInterest.getText().toString();
                    //increment the number of interests
                    numInterests++;
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

        view.findViewById(R.id.interestOne).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 1){
                    //move every interest down one place
                    for(int i = 0; i < numInterests; i++){
                        interests[i] = interests[i + 1];
                        Toast.makeText(getActivity(), "Interest One Removed", Toast.LENGTH_SHORT).show();
                    }
                    //decrement number of interests
                    numInterests--;
                }
            }
        });
        view.findViewById(R.id.interestTwo).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 2){
                    //move every interest down one place
                    for(int i = 1; i < numInterests; i++){
                        interests[i] = interests[i + 1];
                        Toast.makeText(getActivity(), "Interest Two Removed", Toast.LENGTH_SHORT).show();
                    }
                    //decrement number of interests
                    numInterests--;
                }
            }
        });
        view.findViewById(R.id.interestThree).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 3){
                    //move every interest down one place
                    for(int i = 2; i < numInterests; i++){
                        interests[i] = interests[i + 1];
                        Toast.makeText(getActivity(), "Interest Three Removed", Toast.LENGTH_SHORT).show();
                    }
                    //decrement number of interests
                    numInterests--;
                }
            }
        });
        view.findViewById(R.id.interestFour).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 4){
                    //move every interest down one place
                    for(int i = 3; i < numInterests; i++){
                        interests[i] = interests[i + 1];
                        Toast.makeText(getActivity(), "Interest Four Removed", Toast.LENGTH_SHORT).show();
                    }
                    //decrement number of interests
                    numInterests--;
                }
            }
        });
        view.findViewById(R.id.interestFive).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numInterests >= 5){
                    //move every interest down one place
                    for(int i = 4; i < numInterests; i++){
                        interests[i] = interests[i + 1];
                        Toast.makeText(getActivity(), "Interest Five Removed", Toast.LENGTH_SHORT).show();
                    }
                    //decrement number of interests
                    numInterests--;
                }
            }
        });
        //For debugging, outputs all the current values in interests
        view.findViewById(R.id.debugButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Context context = getActivity().getApplicationContext();
                CharSequence text = (CharSequence)(interests[0] + interests[1] + interests[2] + interests[3] + interests[4]);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
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

