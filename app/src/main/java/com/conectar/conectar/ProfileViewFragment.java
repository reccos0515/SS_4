package com.conectar.conectar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import util.FriendsUtil;
import util.Singleton;


/**
 * This class is used to view a profile.  It retrieves the user information from the server and displays it in the UI
 * The user can also interact with the user by adding them to the friend list
 * The updated friend list is then sent back to the server
 */
public class ProfileViewFragment extends Fragment {

    private static String url = "http://proj-309-ss-4.cs.iastate.edu:9002/ben/users/"; //url to be modified
    private static int userIDNum = 1; //change this to a global variable of the logged in user
    private static JSONObject jsObj; //json object that is received. Can only be accessed within a button
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
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        //set the username on screen
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url + userIDNum + "",  null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        jsObj = response;
                        TextView username = view.findViewById(R.id.viewUsername);
                        TextView bio = view.findViewById(R.id.viewBio);
                        try {
                            username.setText(response.get("userName").toString());
                            bio.setText(response.get("bio").toString());
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
        TextView username = view.findViewById(R.id.viewUsername);
        //when the button is pressed will add friend
        view.findViewById(R.id.addFriend).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //first id receives, second id sends
                try{
                    FriendsUtil.makeFriend(userIDNum + "", (int)jsObj.get("id") + "", getContext());
                } catch (JSONException e){
                    e.printStackTrace();
                }

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
        //Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
