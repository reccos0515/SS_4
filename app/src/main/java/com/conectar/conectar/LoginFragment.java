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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.JsonRequest;
import util.SessionUtil;
import util.Singleton;
import util.UserUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    Button loginBtn;
    EditText loginUsername, loginPassword;
    Context context;
    JSONObject user;
    JSONArray tempJSONArray = new JSONArray();
    JSONObject tempJSONObject = new JSONObject();
    //final SessionUtil session =  new SessionUtil(getContext());

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LogoutFragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        return inflater.inflate(R.layout.fragment_login, null); //opens the logout screen
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys

        context = getContext(); //get the current context for use in Volley requests

        loginPassword = view.findViewById(R.id.loginPassword);
        loginUsername = view.findViewById(R.id.loginUsername);

        view.findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener(){ //if user clicks the button to log in
            @Override
            public void onClick(View view) { //TODO review whether or not this login stuff works
                //to test whether the session can work
                //session.createSession("username", "1", "1");
                //Toast.makeText(getActivity(), "User Username" + session.getSessionusername(), Toast.LENGTH_LONG).show();
                /////Toast.makeText(getActivity(), "Login attempt made", Toast.LENGTH_LONG).show();
                //TODO grab actual values from edittext???
                String tempPassword = "Lena";
                String tempUsernmae = "Lena";

                //shared preferences stuff
                editor.putString("USERNAME", tempUsernmae); //adds the username into the key USERNAME
                editor.apply(); //places all the changes we've made into shared preferences
                String temp = preferences.getString("USERNAME", "empty"); //grabs the string under the key USERNAME, or sets temp = empty if there is nothing
                Log.d("SharedPreferences", temp);
                Toast.makeText(getActivity(), temp, Toast.LENGTH_SHORT).show();


                tempJSONObject = UserUtil.sendLoginRequest(tempUsernmae, tempPassword, context); //returns JSONArray response to login request
                //deal with whether or not the request was successful
                Boolean success = false;
                String username = "";
                /*
                try {
                    //success = tempJSONObject.getBoolean("success");
                    //user = tempJSONObject.getJSONObject("user");
                    username = tempJSONObject.getString("userName");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                */

                if(success){ //if the request was successful
                    /*
                    try {
                        user = tempJSONArray.getJSONObject(1); //get the user

                        String username = user.getString("username");
                        String id = user.getString("id");
                        String bio = user.getString("bio");
                        Log.d("User Values", "username: " + username + "   id: " + id + "   bio: " + bio);
                        JSONArray interests = user.getJSONArray("interests"); //TODO idk
                        String status = Integer.toString(user.getInt("status"));

                        //set session variables
                        //session.createSession(username, id, status);

                        //send user to swipe page
                        Fragment fragment = new SwipeFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.screen_area, fragment);
                        fragmentTransaction.commit();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else{
                    String message = "";
                    try {
                        message = tempJSONArray.getString(1); //get error message
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(message.equals("incorrect login")){ //TODO change to whatever error messages there are
                        //if user has incorrect credentials
                        Toast.makeText(getActivity(), "Login Unsuccessful", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Log.d("Login Error Message", message); //if it was an app/server error
                    }
                    */
                }


            }
        });

        view.findViewById(R.id.createAccountBtn).setOnClickListener(new View.OnClickListener() { //if create account button is clicked
            @Override
            public void onClick(View view) { //change fragments to the new profile fragment

                Fragment fragment = new NewProfileFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment);
                fragmentTransaction.commit();

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
