package com.conectar.conectar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 * This class facilitates log out for the user
 */
public class LogoutFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public LogoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogoutFragment.
     */
    //Rename and change types and number of parameters
    public static LogoutFragment newInstance(String param1, String param2) {
        LogoutFragment fragment = new LogoutFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * method to be called when the fragment is being created
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * method to be called to create the view
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
        return inflater.inflate(R.layout.fragment_logout, null); //opens the logout screen
    }

    /**
     * method to be called after the view has been created. This sets up the UI and the button listeners
     * as well as includes most of the code specific to this page
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", MODE_PRIVATE);

        view.findViewById(R.id.logoutBtn).setOnClickListener(new View.OnClickListener() { //if logout button is pressed
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "You are logged out!", Toast.LENGTH_SHORT).show();
                Log.d("LogoutFragment", "User was logged in, proof prior to destruction of session: " + preferences.getString("USERNAME", "empty"));
                destroySession(preferences); //remove sharedPreferences session variables
                Log.d("LogoutFragment", "User logged out, proof: " + preferences.getString("USERNAME", "empty"));

                //Send user to the login screen
                Fragment fragment = new LoginFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment);
                fragmentTransaction.commit();
            }
        });


    }

    /**
     * Destroys all session variables for the current session
     * This is called when the user logs out. Its signature is
     * void destroySession(SharedPreferences preferences). It takes the preferences and destroys them,
     * so there is no return.
     * @param preferences the instance of sharedPreferences from which the variables will be destroyed
     */
    public void destroySession(SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear(); //destroy the session variables
        editor.apply();
    }

    /**
     * method to be called to attach the fragment
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
     * method to be called to detach the fragment
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
