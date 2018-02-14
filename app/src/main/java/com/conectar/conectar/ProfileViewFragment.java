package com.conectar.conectar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This class is used to view a profile.  It retrieves the user information from the server and displays it in the UI
 * The user can also interact with the user by adding them to the friend list
 * The updated friend list is then sent back to the server
 */
public class ProfileViewFragment extends Fragment {

    private int[] friendList = new int[20]; //list of up to 20 friend's id numbers
    private int numFriends; //number of friends in friendList
    private String curUsername; //the username of the user to be viewed
    private static int curIdNum; //id number of the user to be viewed

    private OnFragmentInteractionListener mListener;

    public ProfileViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * ProfileView by pulling up the profile of a user from
     * their user ID
     * @param idNum an int that represents the id number of the user to be viewed
     * @return A new instance of fragment ProfileViewFragment.
     */
    public static ProfileViewFragment newInstance(int idNum) {
        ProfileViewFragment fragment = new ProfileViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        curIdNum = idNum;
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
        return inflater.inflate(R.layout.fragment_profile_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO send JSON request to get the needed user information from the curIdNum
        numFriends = 0; //set numFriends TODO will get numFriends from JSONobj
        String text = "trial"; //text to be set to the curUsername.  TODO Will get username from returned JSONobj
        final int curIdNum = 0; //set id of the user TODO will get id from JSONobj
        //set the username on screen
        TextView username = view.findViewById(R.id.viewUsername);
        username.setText(text);
        //when the button is pressed, will add friend if there is space in friendsList
        view.findViewById(R.id.addFriend).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numFriends < 20){
                    friendList[numFriends] = curIdNum; //add friend
                    numFriends++;
                }
            }
        });
        //TODO when moving to a new page, send back updated information
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
}
