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
import android.widget.Button;
import android.widget.ImageView;
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
        Button button = view.findViewById(R.id.addFriend);
        ImageView statusBubble = (ImageView) view.findViewById(R.id.statusBubbleView);
        ImageView profilePic = (ImageView) view.findViewById(R.id.profileViewProfilePic);
        String interests = "00000000000";
        int numInterests;
        int id = 0;
        int status = 0;
        int pic = 0;

        try {
            username.setText(user.get("userName").toString());
            bio.setText(user.get("bio").toString());
            interests = user.getString("interests");
            id = user.getInt("id");
            status = user.getInt("status");
            pic = user.getInt("profilePic");
        } catch (JSONException e){
            e.printStackTrace();
        }


        if(status == 0){
            statusBubble.setImageResource(R.drawable.ic_status_red);
        }else if (status == 1){
            statusBubble.setImageResource(R.drawable.ic_status_yellow);
        }else if(status == 2){
            statusBubble.setImageResource(R.drawable.ic_status_green);
        }

        if(pic == 2){
            profilePic.setImageResource(R.drawable.bat_128);
        } else if (pic == 3){
            profilePic.setImageResource(R.drawable.beaver_128);
        }else if (pic == 4){
            profilePic.setImageResource(R.drawable.beetle_128);
        }else if (pic == 5){
            profilePic.setImageResource(R.drawable.bulldog_128);
        }else if (pic == 6){
            profilePic.setImageResource(R.drawable.camel_128);
        }else if (pic == 7){
            profilePic.setImageResource(R.drawable.canary_128);
        }else if (pic == 9){
            profilePic.setImageResource(R.drawable.cat_128);
        }else if (pic == 10){
            profilePic.setImageResource(R.drawable.chameleon_128);
        }else if (pic == 11){
            profilePic.setImageResource(R.drawable.chicken_128);
        }else if (pic == 12){
            profilePic.setImageResource(R.drawable.clownfish_128);
        }else if (pic == 13){
            profilePic.setImageResource(R.drawable.cobra_128);
        }else if (pic == 14){
            profilePic.setImageResource(R.drawable.cow_128);
        }else if (pic == 15){
            profilePic.setImageResource(R.drawable.crab_128);
        }else if (pic == 16){
            profilePic.setImageResource(R.drawable.crocodile_128);
        }else if (pic == 17){
            profilePic.setImageResource(R.drawable.duck_128);
        }else if (pic == 18){
            profilePic.setImageResource(R.drawable.elephant_128);
        }else if (pic == 19){
            profilePic.setImageResource(R.drawable.fox_128);
        }else if (pic == 20){
            profilePic.setImageResource(R.drawable.frog_128);
        }else if (pic == 21){
            profilePic.setImageResource(R.drawable.giraffe_128);
        }else if (pic == 22){
            profilePic.setImageResource(R.drawable.hippopotamus_128);
        }else if (pic == 23){
            profilePic.setImageResource(R.drawable.hummingbird_128);
        }else if (pic == 24){
            profilePic.setImageResource(R.drawable.kangaroo_128);
        }else if (pic == 25){
            profilePic.setImageResource(R.drawable.lion_128);
        }else if (pic == 26){
            profilePic.setImageResource(R.drawable.llama_128);
        }else if (pic == 27){
            profilePic.setImageResource(R.drawable.macaw_128);
        }else if (pic == 28){
            profilePic.setImageResource(R.drawable.monkey_128);
        }else if (pic == 29){
            profilePic.setImageResource(R.drawable.moose_128);
        }else if (pic == 30){
            profilePic.setImageResource(R.drawable.mouse_128);
        }else if (pic == 31){
            profilePic.setImageResource(R.drawable.octopus_128);
        }else if (pic == 32){
            profilePic.setImageResource(R.drawable.ostrich_128);
        }else if (pic == 33){
            profilePic.setImageResource(R.drawable.owl_128);
        }else if (pic == 34){
            profilePic.setImageResource(R.drawable.panda_128);
        }else if (pic == 35){
            profilePic.setImageResource(R.drawable.pelican_128);
        }else if (pic == 36){
            profilePic.setImageResource(R.drawable.penguin_128);
        }else if (pic == 37){
            profilePic.setImageResource(R.drawable.pig_128);
        }else if (pic == 38){
            profilePic.setImageResource(R.drawable.rabbit_128);
        }else if (pic == 39){
            profilePic.setImageResource(R.drawable.racoon_128);
        }else if (pic == 40){
            profilePic.setImageResource(R.drawable.rhinoceros_128);
        }else if (pic == 41){
            profilePic.setImageResource(R.drawable.shark_128);
        }else if (pic == 42){
            profilePic.setImageResource(R.drawable.sheep_128);
        }else if (pic == 43){
            profilePic.setImageResource(R.drawable.siberianhusky_128);
        }else if (pic == 44){
            profilePic.setImageResource(R.drawable.sloth_128);
        }else if (pic == 45){
            profilePic.setImageResource(R.drawable.snake_128);
        }else if (pic == 46){
            profilePic.setImageResource(R.drawable.squirrel_128);
        }else if (pic == 47){
            profilePic.setImageResource(R.drawable.swan_128);
        }else if (pic == 48){
            profilePic.setImageResource(R.drawable.tiger_128);
        }else if (pic == 49){
            profilePic.setImageResource(R.drawable.toucan_128);
        }else if (pic == 50){
            profilePic.setImageResource(R.drawable.turtle_128);
        }else if (pic == 51){
            profilePic.setImageResource(R.drawable.whale_128);
        } else {
            profilePic.setImageResource(R.drawable.fatbee_128);
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
        int selfId = preferences.getInt("ID", 0);
        if(id != selfId) {
            if(UserUtil.getUserToViewIsFriend()){
                button.setText("Message");
                //if it is a different person, when the button is pressed will add friend
                view.findViewById(R.id.addFriend).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //todo figure out how to set this to be messages with that specific person
                        //create the new edit profile fragment
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
        }
        else{
            button.setText("Edit");
            //if it is the same person, when the button is pressed will pull up edit profile
            view.findViewById(R.id.addFriend).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //create the new edit profile fragment
                    Fragment fragment = new EditProfileFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();

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
