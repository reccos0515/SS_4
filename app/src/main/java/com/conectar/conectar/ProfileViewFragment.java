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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import messaging.MessagesUtil;
import messaging.User;
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

        userIDNum = preferences.getInt("ID", 0); //get the user's id
        user = UserUtil.getUserToView(); //get the user that should be shown

        //set up the UI interface
        TextView int1 = view.findViewById(R.id.prof_int1);
        TextView int2 = view.findViewById(R.id.prof_int2);
        TextView int3 = view.findViewById(R.id.prof_int3);
        TextView int4 = view.findViewById(R.id.prof_int4);
        TextView int5 = view.findViewById(R.id.prof_int5);
        TextView username = view.findViewById(R.id.viewUsername);
        TextView bio = view.findViewById(R.id.viewBio);
        Button button = view.findViewById(R.id.addFriend);
        Button report = view.findViewById(R.id.report_button);
        Button back = view.findViewById(R.id.viewBackBtn);
        ImageView statusBubble = view.findViewById(R.id.statusBubbleView);
        ImageView profilePic = view.findViewById(R.id.profileViewProfilePic);

        //set default values to all variables
        String interests = "00000000000";
        int numInterests;
        int id = 0;
        int status = 0;
        int pic = 0;

        //pull all the information from the json object
        try {
            username.setText(user.get("userName").toString());
            bio.setText(user.get("bio").toString());
            interests = user.getString("interests");
            id = user.getInt("id");
            status = user.getInt("status");
            pic = user.getInt("profilePicture");
        } catch (JSONException e){
            e.printStackTrace();
        }

        //set the status bubble to show the user what status they have
        if(status == 0){
            statusBubble.setImageResource(R.drawable.ic_status_red);
        }else if (status == 1){
            statusBubble.setImageResource(R.drawable.ic_status_yellow);
        }else if(status == 2){
            statusBubble.setImageResource(R.drawable.ic_status_green);
        }

        UserUtil.updateProfilePicture(pic, profilePic); //set the profile picture

        numInterests = interests.charAt(0) - '0'; //get number of interests

        //show all the interests
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
        if(id != userIDNum) {
            //find out if this is a friend
            if(UserUtil.getUserToViewIsFriend()){
                button.setText("Message");
                //if it is a friend, will open a messages page
                view.findViewById(R.id.addFriend).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //create the new edit profile fragment
                        MessagesUtil.getConversation(2, 1, getContext()); //get the current conversation
                        Fragment fragment = new MessagesFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.screen_area, fragment);
                        fragmentTransaction.commit();
                    }
                });
            }
            else {
                //if it is a different person, when the button is pressed will add friend
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
            //make a report
            view.findViewById(R.id.report_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //create the new edit profile fragment
                    Fragment fragment = new ReportFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();
                }
            });
            //go back
            view.findViewById(R.id.viewBackBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //return to the last fragment
                    FragmentManager fm = getFragmentManager();
                    if(fm.getBackStackEntryCount() > 0){
                        Log.d("There was something ", "on the stack");
                        fm.popBackStackImmediate();
                    } else {
                        Log.d("There was nothing ", "on the stack");
                    }
                }
            });
        }
        else{
            button.setText("Edit"); //when the user views their own profile, the first botton is edit
            report.setText("Change Status"); //the second button is change status
            report.setTextSize(10);
            back.setText("friends"); //the third button will go to friends list

            //if it is the same person, when the button is pressed will pull up edit profile
            view.findViewById(R.id.addFriend).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserUtil.setUserProfPic(0); //can clear any previously saved profile pics
                    //create the new edit profile fragment
                    Fragment fragment = new EditProfileFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();

                }
            });
            //if it is the same person, when the button is pressed will pull up change status
            view.findViewById(R.id.report_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //create the new edit profile fragment
                    Fragment fragment = new ChangeStatusFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();

                }
            });
            //if it is the same person, when the button is pressed will go to friends list
            view.findViewById(R.id.viewBackBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //create the new edit profile fragment
                    Fragment fragment = new FriendsFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();

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
