package com.conectar.conectar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.InterestsUtil;
import util.JsonRequest;
import util.SessionUtil;
import util.Singleton;
import util.SwipeStubs;
import util.UserUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogoutFragment#newInstance} factory method to
 * create an instance of this fragment.
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
    //Rename and change types and number of parameters
    public static SwipeFragment newInstance() {
        SwipeFragment fragment = new SwipeFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe, null); //opens the logout screen
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("SwipeFragment", "Entered the swipe fragment");
        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys

        interests = preferences.getString("INTERESTS", "empty"); //set logged in user's interests from session variables


        numInterests = interests.charAt(0) - '0'; //get the number of interests the logged in user has
        int id = preferences.getInt("ID", 0); //set the id
        String thisId = Integer.toString(id);
        url = "http://proj-309-ss-4.cs.iastate.edu:9001/ben/users/" + thisId + "/relevant"; //set the url

        context = getActivity().getApplicationContext(); //get the context
        JsonRequest.swipeRequest(view, url, context); //call this to send the request
        mainView = view; //set the view

                        //on click listener for next
        view.findViewById(R.id.swipeNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SwipeFragment", "Clicked swipe next");
                if(success) {
                    userOnDisplayLoc++; //go to next user
                    //if it has reached the end, return to 0
                    if (userOnDisplayLoc >= len) {
                        userOnDisplayLoc = 0;
                    }
                    try {
                        JSONObject user = users.getJSONObject(userOnDisplayLoc); //pull this user
                        updateUI(user); //update the ui with this user
                        UserUtil.setUserToView(user); //save this where profile view can access if needed
                    } catch (JSONException e) {
                        TextView errorMessage = view.findViewById(R.id.swipeMessage); //can print error message
                        errorMessage.setText("Sorry, we ran into a problem");//set an error for the user to see
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
                if(success) {
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
                        errorMessage.setText("Sorry, we ran into a problem"); //set an error for the user to see
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
                if(success) {
                    //call a profile view fragment with userOnDisplayLoc user
                    Fragment fragment = new ProfileViewFragment();
                    if (fragment != null) { //Changes the screens
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.screen_area, fragment);
                        fragmentTransaction.commit();
                    }
                }
            }
        });
    }

    /**
     * Helper method to update the ui to the right user
     * @param user object to be viewed
     */
    public static void updateUI(JSONObject user){

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
            //TODO display picture
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
     * Method to save the received object. Can be accessed after a user presses a button
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
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return;
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
        //Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
