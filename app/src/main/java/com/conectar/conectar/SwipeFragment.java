package com.conectar.conectar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.InterestsUtil;
import util.JsonRequest;
import util.UserUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 * Class to be used to look through other user the logged in user has matched with
 */
public class SwipeFragment extends Fragment {
    private static View mainView; //view from the main part of the fragment
    private Context context; //context to be used to add JSONRequest to queue
    private static String url; //beginning of url
    private static int userOnDisplayLoc; //int to hold the location in the array of the current user being viewed on the screen
    private static int numInterests; //number of interests of the logged in user
    private static String interests; //logged in user's interests
    private static JSONArray users; //array of users found in response
    private static int len = 0; //length of array of users
    private static boolean success = false; //if the server could successfully send

    private OnFragmentInteractionListener mListener;

    public SwipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    public static SwipeFragment newInstance() {
        SwipeFragment fragment = new SwipeFragment();
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe, null); //opens the logout screen
    }

    /**
     * method to be called once the view is created to set up the UI and button listeners
     * Most of the code specific to this page is implemented here
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainView = view; //set the view
        Log.d("SwipeFragment", "Entered the swipe fragment");
        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys


        //sends the user to the login page if they aren't logged in
        Boolean isLoggedIn = preferences.getBoolean("ISLOGGEDIN", false);
        Log.d("SwipeFragment", "ISLOGGEDIN: " + isLoggedIn);
        if(!isLoggedIn){ //if user isn't already logged in
            Fragment fragment = new LoginFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area, fragment);
            fragmentTransaction.commit();
        }

        interests = preferences.getString("INTERESTS", "empty"); //set logged in user's interests from session variables


        numInterests = interests.charAt(0) - '0'; //get the number of interests the logged in user has
        int id = preferences.getInt("ID", 0); //set the id todo fix this line
        String thisId = Integer.toString(id);
        Log.d("SwipeFragment", "Id of user: " + thisId);
        url = "http://proj-309-ss-4.cs.iastate.edu:9001/ben/users/" + thisId + "/relevant"; //set the url
        Log.d("SwipeFragment", "Url posted to: " + url);

        context = getActivity().getApplicationContext(); //get the context
        //if should get a new list of users
        if(UserUtil.getUsersArray() == null) {
            JsonRequest.swipeRequest(view, url, context); //call this to send the request
        } else {
            users = UserUtil.getUsersArray();
            len = users.length();
            try {
                updateUI((JSONObject) users.get(userOnDisplayLoc));
            } catch (JSONException e){
                e.printStackTrace();
            }
        }

                        //on click listener for next
        view.findViewById(R.id.swipeNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SwipeFragment", "Clicked swipe next");
                if(success && users.length() > 0) {
                    userOnDisplayLoc++; //go to next user
                    //if it has reached the end, return to 0
                    if (userOnDisplayLoc >= len) {
                        JsonRequest.swipeRequest(mainView, url, context); //call this to send the request
                    }
                    try {
                        JSONObject user = users.getJSONObject(userOnDisplayLoc); //pull this user
                        updateUI(user); //update the ui with this user
                        UserUtil.setUserToView(user); //save this where profile view can access if needed
                    } catch (JSONException e) {
                        TextView errorMessage = view.findViewById(R.id.swipeMessage); //can print error message
                        TextView username = view.findViewById(R.id.swipeFirstName);
                        TextView int1 = view.findViewById(R.id.interestOne);
                        TextView int2 = view.findViewById(R.id.interestTwo);
                        TextView int3 = view.findViewById(R.id.interestThree);
                        TextView int4 = view.findViewById(R.id.interestFour);
                        TextView int5 = view.findViewById(R.id.interestFive);
                        errorMessage.setText("Sorry, we ran into problem 0001");//set an error for the user to see
                        username.setText("");
                        int1.setText("");
                        int2.setText("");
                        int3.setText("");
                        int4.setText("");
                        int5.setText("");
                        e.printStackTrace();
                    }
                }

            }
        });
        //on click listener for prev
        view.findViewById(R.id.swipePrev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SwipeFragment", "Clicked swipe previous");
                if(success && users.length() > 0) {
                    userOnDisplayLoc--; //go to previous
                    //if it has passed the beginning, return to the end
                    if (userOnDisplayLoc < 0) {
                        userOnDisplayLoc = len - 1;
                    }
                    try {
                        JSONObject user = users.getJSONObject(userOnDisplayLoc); //pull this user
                        updateUI(user); //update the ui
                        UserUtil.setUserToView(user); //save this where profile view can access if needed
                    } catch (JSONException e) {
                        TextView errorMessage = view.findViewById(R.id.swipeMessage); //can print error message
                        errorMessage.setText("Sorry, we ran into problem 0002"); //set an error for the user to see

                        e.printStackTrace();
                    }
                }
            }
        });
        //on click listener for view
        view.findViewById(R.id.swipeView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SwipeFragment", "Clicked view profile");
                TextView errorMessage = mainView.findViewById(R.id.swipeMessage);
                Button button = mainView.findViewById(R.id.swipeView);
                if(success && users.length() > 0) {
                    //call a profile view fragment with userOnDisplayLoc user
                    Fragment fragment = new ProfileViewFragment();
                    if (fragment != null) { //Changes the screens
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.screen_area, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                } else if(errorMessage.getText().toString().equals("User is RED")){
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.screen_area, new ChangeStatusFragment());
                    fragmentTransaction.commit();
                }
            }
        });
    }

    /**
     * Helper method to update the ui to the right user.
     * This is called every time the user goes to the next
     * user to view.  It updates all of the information on the UI
     * so they are seeing the next user.  The signature is
     * void updateUI(JSONObject user).  It takes in the user that
     * is going to be viewed. It does not return anything because
     * it updates the UI
     * @param user object to be viewed
     */
    public static void updateUI(JSONObject user){

        updateProfilePic(user);
        //set up all the textviews
        TextView errorMessage = mainView.findViewById(R.id.swipeMessage);
        TextView firstName = mainView.findViewById(R.id.swipeFirstName);
        TextView interest1 = mainView.findViewById(R.id.swipeInterest1);
        TextView interest2 = mainView.findViewById(R.id.swipeInterest2);
        TextView interest3 = mainView.findViewById(R.id.swipeInterest3);
        TextView interest4 = mainView.findViewById(R.id.swipeInterest4);
        TextView interest5 = mainView.findViewById(R.id.swipeInterest5);

        String viewInterests; //interests of the current user on display
        try{
            viewInterests = (String) user.get("interests");
            String s = user.getString("userName"); //get the username
            firstName.setText(s); //set the username

        }catch (JSONException e){
            errorMessage.setText("Sorry, we ran into a problem"); //set error message to show to user
            e.printStackTrace();
            return;
        }
        int currentCommonIntsDisplayed = 0; //how many common interests are being displayed
        int viewNumInterests = viewInterests.charAt(0) - '0'; //the first char in this String is the number
        //as long as they both have at least 1 interest, compare interests
        if(numInterests > 0 && viewNumInterests > 0) {
            for (int i = 0; i < numInterests; i++) {
                for (int j = 0; j < viewNumInterests; j++) {
                    //compare interests
                    if (interests.charAt(2 * i + 1) == viewInterests.charAt(2 * j + 1) && interests.charAt(2 * i + 2) == viewInterests.charAt(2 * j + 2)) {
                        //update the correct interest

                        if (currentCommonIntsDisplayed == 0) {
                            interest1.setText(InterestsUtil.getInterest(interests.charAt(2 * i + 1) + "" + interests.charAt(2 * i + 2) + "")); //set this to be interests(i)
                        } else if (currentCommonIntsDisplayed == 1) {
                            interest2.setText(InterestsUtil.getInterest(interests.charAt(2 * i + 1) + "" + interests.charAt(2 * i + 2) + "")); //set this to be interests(i)
                        } else if (currentCommonIntsDisplayed == 2) {
                            interest3.setText(InterestsUtil.getInterest(interests.charAt(2 * i + 1) + "" + interests.charAt(2 * i + 2) + "")); //set this to be interests(i)
                        } else if (currentCommonIntsDisplayed == 3) {
                            interest4.setText(InterestsUtil.getInterest(interests.charAt(2 * i + 1) + "" + interests.charAt(2 * i + 2) + "")); //set this to be interests(i)
                        } else if (currentCommonIntsDisplayed == 4) {
                            interest5.setText(InterestsUtil.getInterest(interests.charAt(2 * i + 1) + "" + interests.charAt(2 * i + 2) + "")); //set this to be interests(i)
                        }

                        currentCommonIntsDisplayed++; //increment count of how many common interests are being displayed
                    }
                }
            }
        }
        //set all non-common interests to ""

        if(currentCommonIntsDisplayed == 0){
            interest1.setText("No common interests found"); //if there are no common interests, tell the user this
        }
        if(currentCommonIntsDisplayed < 2){
            interest2.setText("");
        }
        if(currentCommonIntsDisplayed < 3){
            interest3.setText("");
        }
        if(currentCommonIntsDisplayed < 4){
            interest4.setText("");
        }
        if(currentCommonIntsDisplayed < 5){
            interest5.setText("");
        }

        errorMessage.setText(""); //set no error
        return;
    }

    /**
     * Method to save the received object. Will set the variable users which can be accessed after
     * the first button call.
     * This method saves the Json Object that was received in the volley call to the server.
     * This json object includes a success value, and either an error message or array of users
     * to view. This is called when the request is received. The signature is void saveNewObject(JSONObject js)
     * where JSONObject js is the object received in the volley request and nothing is returned because
     * this object is saved in the variables in this class.
     * @param js json object found in response
     */
    public static void saveNewObject(JSONObject js){
        try{
            success = (boolean) js.get("success"); //see what the success was
        }catch (JSONException e){
            e.printStackTrace();
            return;
        }
        if(!success) {
            len = 0; // length of array is 0
        }
        else{
            userOnDisplayLoc = 0; //update place in array to 0
            try{
                users = js.getJSONArray("users");  //set users to this array
                len = users.length(); //set length of the array
                UserUtil.setUsersArray(users); //save this array
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return;
    }

    /**
     * Method to update the profile picture
     * @param user to be viewed
     */
    public static void updateProfilePic(JSONObject user){

        ImageView profilePic = mainView.findViewById(R.id.profilePicView);
        int pic = 0;
        try{
            pic = user.getInt("profilePicture");
        } catch (JSONException e){
            e.printStackTrace();
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
        return;
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
