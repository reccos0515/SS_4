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
        int thisUserId = 1;
        int id = 2; //set id of user want to receive
        String url = "http://proj-309-ss-4.cs.iastate.edu:9002/ben/users/";
        url += id + "";
        //get user 1
        JsonRequest.jsonObjectRequest(url, getContext());
        String text = JsonRequest.getString(); //text to be set to the username from the user received
        //set the username on screen
        TextView username = view.findViewById(R.id.viewUsername);
        username.setText(text);
        url += "/friends/" + thisUserId + "";
        final String requestUrl = url;
        //when the button is pressed will add friend
        view.findViewById(R.id.addFriend).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //first id receives, second id sends
                JsonRequest.postRequest(null, getContext(), requestUrl);
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
}
