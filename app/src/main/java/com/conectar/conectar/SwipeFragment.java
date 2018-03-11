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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import util.Singleton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SwipeFragment extends Fragment {
    private static Context context;
    private static String url;
    private int userOnDisplayLoc; //int to hold the location in the array of the user being viewed on the screen
    private int userOnDisplayID; //int to hold the id of the user on display
    private int numInterests; //TODO initialize this
    private int id; //TODO initialize this

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
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        JSONObject js = null; //TODO update this to send what is needed in a request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        boolean success; //hold the success value
                        TextView errorMessage = view.findViewById(R.id.swipeMessage); //can print error message
                        TextView firstName = view.findViewById(R.id.swipeFirstName);
                        TextView interest1 = view.findViewById(R.id.swipeInterest1);
                        TextView interest2 = view.findViewById(R.id.swipeInterest2);
                        TextView interest3 = view.findViewById(R.id.swipeInterest3);
                        TextView interest4 = view.findViewById(R.id.swipeInterest4);
                        TextView interest5 = view.findViewById(R.id.swipeInterest5);
                        //check for success
                        try{
                            success = (boolean) response.get("success");
                        }catch (JSONException e){
                            e.printStackTrace();
                            return;
                        }
                        if(!success){
                            //if failed, set text to nothing to view at this time
                            errorMessage.setText("There are no matches to view at this time");
                            //set all interests as blank
                            firstName.setText("");
                            interest1.setText("");
                            interest2.setText("");
                            interest3.setText("");
                            interest4.setText("");
                            interest5.setText("");
                        }
                        else{
                            //if succeeded, can make the text view not see-able
                            errorMessage.setText("");
                            //----------------------------------- pseudo code to be implemented later ----------------------------------------//

                            //pull the user
                            //userOnDisplayLoc = 0
                            //updateUI(user)

                            //----------------------------------------------------------------------------------------------------------------//
                        }
                        //on click listener for next
                        view.findViewById(R.id.swipeNext).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //-------------------------------------pseudo code to be implemented later ----------------------------------//

                                //pull the user at userOnDisplayLoc - 1
                                //userOnDisplayLoc--
                                //updateUser(this user)

                                //----------------------------------------------------------------------------------------------------------//
                            }
                        });
                        //on click listener for prev
                        view.findViewById(R.id.swipePrev).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //-------------------------------------pseudo code to be implemented later ----------------------------------//

                                //pull the user at userOnDisplayLoc + 1
                                //userOnDisplayLoc++
                                //updateUser(this user)

                                //----------------------------------------------------------------------------------------------------------//
                            }
                        });
                        //on click listener for view
                        view.findViewById(R.id.swipeView).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //-------------------------------------pseudo code to be implemented later ----------------------------------//

                                //call a profile view fragment with userOnDisplayLoc user

                                //----------------------------------------------------------------------------------------------------------//
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
    }

    /**
     * Helper method to update the ui to the right user
     * @param user object to be viewed
     * @param view from where it is called, to be able to update the ui
     */
    private void updateUI(JSONObject user, View view){
        //make all textviews
        TextView errorMessage = view.findViewById(R.id.swipeMessage);
        TextView firstName = view.findViewById(R.id.swipeFirstName);
        TextView interest1 = view.findViewById(R.id.swipeInterest1);
        TextView interest2 = view.findViewById(R.id.swipeInterest2);
        TextView interest3 = view.findViewById(R.id.swipeInterest3);
        TextView interest4 = view.findViewById(R.id.swipeInterest4);
        TextView interest5 = view.findViewById(R.id.swipeInterest5);
        //TODO implement userOnDisplayID = id of this user
        int viewNumInterests = 0;
        try {
            userOnDisplayID = (int) user.get("id");
            //TODO display picture
            firstName.setText(user.get("firstName").toString());
            viewNumInterests = (int) user.get("numInterests"); //TODO check if this is how we will be doing it for sure
        } catch (JSONException e){
            e.printStackTrace();
        }
        int currentCommonIntsDisplayed = 0;
        for(int i = 0; i < numInterests; i++){
            //TODO update this completely as outlined in pseudo code once clear how to
        }
        //TODO finish after loops

        //----------------------------------------------------pseudo code to be implemented later-----------------------------------------------//

        //userOnDisplayID = id of this user
        //display picture
        //display name
        //int currentInterestNumDisplayed = 0
        //for(i = 0; i < numInterests of logged in user; i++)
            //for(j = 0; j < numInterests of viewed user; j++)
                //if(loggedInUserInterests[i] == viewedUserInterests[j])
                //display this in interests[currentInterestNumDisplayed]
        //if no common interests were found, set I1 to "no common interests"
        //set everything left to ""

        //-------------------------------------------------------------------------------------------------------------------------------------//
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
