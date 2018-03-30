package com.conectar.conectar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import util.Friend;
import util.InterestsUtil;
import util.UserUtil;


/**
 * This class is used to view a profile.  It retrieves the user information from the server and displays it in the UI
 * The user can also interact with the user by adding them to the friend list
 * The updated friend list is then sent back to the server
 */
public class ProfileViewFragment extends Fragment {

    private static int userIDNum; //change this to a global variable of the logged in user
    private static JSONObject user; //user to display
    private Context context;
    //TODO update this to work with swipe fragment

    private OnFragmentInteractionListener mListener;

    public ProfileViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * ProfileView by pulling up the profile of a user from
     * their user ID
     * @return A new instance of fragment ProfileViewFragment.
     */
    public static ProfileViewFragment newInstance() {
        ProfileViewFragment fragment = new ProfileViewFragment();
        return fragment;
    }

    /**
     * method to create the fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * method to create the view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_view, container, false);
    }

    /**
     * method to be called once the view has been created to set up the UI and the button listeners
     * This is where most of the code specific to this page is implemented
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys

        userIDNum = preferences.getInt("ID", 0);
        user = UserUtil.getUserToView(); //get the user that should be shown
        TextView int1 = view.findViewById(R.id.prof_int1);
        TextView int2 = view.findViewById(R.id.prof_int2);
        TextView int3 = view.findViewById(R.id.prof_int3);
        TextView int4 = view.findViewById(R.id.prof_int4);
        TextView int5 = view.findViewById(R.id.prof_int5);
        TextView username = view.findViewById(R.id.viewUsername);
        TextView bio = view.findViewById(R.id.viewBio);
        String interests = "00000000000";
        int numInterests;
        int id = 0;

        try {
            username.setText(user.get("userName").toString());
            bio.setText(user.get("bio").toString());
            interests = user.getString("interests");
            id = user.getInt("id");
        } catch (JSONException e){
            e.printStackTrace();
        }
        numInterests = interests.charAt(0) - '0';
        if(numInterests > 0){
            int1.setText(InterestsUtil.getInterest(interests.charAt(1) + "" + interests.charAt(2) + ""));
        }
        else{
            int1.setText("");
        }
        if(numInterests > 1){
            int2.setText(InterestsUtil.getInterest(interests.charAt(3) + "" + interests.charAt(4) + ""));
        }
        else{
            int2.setText("");
        }
        if(numInterests > 2){
            int3.setText(InterestsUtil.getInterest(interests.charAt(5) + "" + interests.charAt(6) + ""));
        }
        else{
            int3.setText("");
        }
        if(numInterests > 3){
            int4.setText(InterestsUtil.getInterest(interests.charAt(7) + "" + interests.charAt(8) + ""));
        }
        else{
            int4.setText("");
        }
        if(numInterests > 4){
            int5.setText(InterestsUtil.getInterest(interests.charAt(9) + "" + interests.charAt(10) + ""));
        }
        else{
            int5.setText("");
        }
        //make the add friend button as long as it is not their own page
        if(id != preferences.getInt("ID", 0)) {
            //when the button is pressed will add friend
            view.findViewById(R.id.addFriend).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //first id receives, second id sends
                    try {
                        Friend.makeFriend(userIDNum, user.getInt("id"), getContext());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    /**
     * method to attach fragment
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
     * method to detach fragment
     */
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
        //Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
