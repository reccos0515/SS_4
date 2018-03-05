package com.conectar.conectar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
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

import util.JsonRequest;
import util.Singleton;
import util.User;


/**
 * This class is used to view a profile.  It retrieves the user information from the server and displays it in the UI
 * The user can also interact with the user by adding them to the friend list
 * The updated friend list is then sent back to the server
 */
public class ProfileViewFragment extends Fragment {

    private static int curIdNum; //id number of the user to be viewed
    private static String url = "http://proj-309-ss-4.cs.iastate.edu:9002/ben/users/1";
    private static JSONObject jsObj;
    private Context context;

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
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        int thisUserId = 1;
//        int id = 2; //set id of user want to receive
//        url += id + "";


        //----------------------------------------------------------------------//

        //----------------------------MAGGIE'S GARBAGE----------------------------------//
        /*
        Context context = getContext();
        JsonRequest.jsonObjectRequest("http://proj-309-ss-4.cs.iastate.edu:9002/ben/users/1", context);
        String text = JsonRequest.getString();
        Log.d("Request String", text);
        */
        //--------------------------------------------------------------------------------//

        context = getContext();
        //set the username on screen
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,  null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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
                Log.d("Object Get Status", "error");
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
        TextView username = view.findViewById(R.id.viewUsername);
        final String requestUrl = url;
        //when the button is pressed will add friend
        view.findViewById(R.id.addFriend).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //first id receives, second id sends
                Log.d("Got to", "Add friend onclick listener");
                JsonRequest.postRequest(null, requestUrl);
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
