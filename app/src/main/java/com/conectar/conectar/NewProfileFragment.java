package com.conectar.conectar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import util.JsonRequest;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * This class is used to create a new profile for a new user
 */
public class NewProfileFragment extends Fragment {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private String url = "http://proj-309-ss-4.cs.iastate.edu:9001/ben/login/add";

    private OnFragmentInteractionListener mListener;

    public NewProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * the new profile fragment to make a new profile
     *
     * @return A new instance of fragment NewProfileFragment
     */

    public static NewProfileFragment newInstance() {
        NewProfileFragment fragment = new NewProfileFragment();
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
        return inflater.inflate(R.layout.fragment_new_profile, null); //opens the logout screen
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys

        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        confirmPassword = view.findViewById(R.id.confirmPassword);

        view.findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //create a toast
                Context context = getActivity().getApplicationContext();
                CharSequence text;
                int duration = Toast.LENGTH_SHORT;
                if(password.getText().toString().equals(confirmPassword.getText().toString())){
                    JSONObject js = new JSONObject();
                    JSONObject toSend = new JSONObject();
                    try {
                        js.put("id", 0);
                        //TODO add this
                        js.put("userName", username.getText().toString());
                        editor.putString("USERNAME", username.getText().toString());
                        js.put("bio", username.getText().toString() +" has not added a bio yet");
                        editor.putString("BIO", username.getText().toString() + " has not added a bio yet");
                        js.put("interests", "00000000000");
                        editor.putString("INTERESTS", "00000000000");
                        js.put("status", 0);
                        editor.putInt("STATUS", 0);
                        editor.putBoolean("ISLOGGEDIN", true); //also logs the user in
                        Boolean isLoggedIn = preferences.getBoolean("ISLOGGEDIN", false);
                        Log.d("NewProfileFragment", "ISLOGGEDIN: " + isLoggedIn.toString());
                        toSend.put("user", js); //put this in the object to send
                        toSend.put("password", password.getText().toString()); //put this in the object to send
                        editor.apply();
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                    //make the post request
                    JsonRequest.postNewUserRequest(toSend, url, getContext());
                    //if confirmPassword and password are the same, submit
                    text = "Successfully submitted";

                    //change fragments to edit profile so user can input fields
                    Fragment fragment = new EditProfileFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();
                }
                else{
                    //if confirmPassword and password are different, do not submit
                    text = "Please match passwords";
                }
                //toast whether or not it was submitted
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

    }

    //hides toolbar so user can't navigate to other fragments
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    //shows toolbar so user has access to the main menu again
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
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
