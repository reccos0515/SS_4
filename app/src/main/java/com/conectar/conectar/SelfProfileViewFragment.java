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

import org.json.JSONException;
import org.json.JSONObject;

import util.UserUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelfProfileViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelfProfileViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * This class is used to set the user as their own user to view then create a profile view fragment for them to see their own profile
 */
public class SelfProfileViewFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public SelfProfileViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SelfProfileViewFragment.
     */
    public static SelfProfileViewFragment newInstance() {
        SelfProfileViewFragment fragment = new SelfProfileViewFragment();
        return fragment;
    }

    /**
     * method to create the new fragment
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
        return inflater.inflate(R.layout.fragment_self_profile_view, container, false);
    }

    /**
     * method to set up the UI and buttons. This is where most of the code unique to this class is located
     */
    /**
     * method to be used once the view has been created to set up the UI and button listeners
     * This is where most of the code specific to this page is implemented
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys

        //pull all of the user's information
        int id = preferences.getInt("ID", 0);
        String bio = preferences.getString("BIO", "");
        int status = preferences.getInt("STATUS", 0);
        String username = preferences.getString("USERNAME", "");
        String interests = preferences.getString("INTERESTS", "00000000000");
        int pic = preferences.getInt("PROFILEPICTURE", 0);
        //put it in a json object
        JSONObject user = new JSONObject();
        try{
            user.put("id", id);
            user.put("bio", bio);
            user.put("status", status);
            user.put("interests", interests);
            user.put("userName", username);
            user.put("profilePicture", pic);
        }catch (JSONException e){

            e.printStackTrace();
        }
        //set this as the user to view
        UserUtil.setUserToView(user);
        UserUtil.setUserToViewIsFriend(false); //not friends with themself
        //create the new profile view fragment
        Fragment fragment = new ProfileViewFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.screen_area, fragment);
        fragmentTransaction.commit();

    }

    /**
     * method to attach the fragment
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
     * method to detach the fragment
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
        void onFragmentInteraction(Uri uri);
    }
}
