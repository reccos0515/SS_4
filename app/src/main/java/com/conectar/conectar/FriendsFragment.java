package com.conectar.conectar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import friends.Friend;
import friends.FriendsListAdapter;
import util.JsonRequest;
import util.UserUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 * This class is used to view the friends the user has
 */
public class FriendsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    Friend[] friendsList = {new Friend("empty")}; //put a default value to be grabbed in case user doesn't have friends
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private OnFragmentInteractionListener mListener;

    public FriendsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LogoutFragment.
     */
    public static FriendsFragment newInstance() {
        FriendsFragment fragment = new FriendsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * method to be called when the fragment is first created
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * method to call to create the view
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
        return inflater.inflate(R.layout.fragment_friends, container, false); //opens the logout screen


    }

    /**
     * method to be call once the view has been created
     * This is where the UI and button listeners are set up, as well as
     * most of the code specific to this page
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO try to make code more modular/readable
        Context context = getContext();
        final SharedPreferences preferences = context.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE);
        int id = preferences.getInt("ID", 0); //get the logged in user's id

        ListView listView = (ListView) view.findViewById(R.id.friendsListView); //grabs the listview from the xml layout
        JsonRequest.getFriendsList(id, context); //get a list of friends and store in sharedpreferences


        JSONObject initial = new JSONObject();

        Set<String> temp = preferences.getStringSet("FRIENDSLISTUSERNAMES", null); //get the list of usernames from shared preferences

        //a bunch of garbage to initialize temp2 so friendsObjects doesn't bug out
        String[] fake = new String[]{"empty"};
        final Set<String> empty = new HashSet<>(Arrays.asList(fake));
        final Set<String> temp2 = preferences.getStringSet("FRIENDJSON", empty);
        final List<String> friendsObjects = new ArrayList<String>(temp2); //convert the list of JSONObjects of users to an arraylist


        if(temp2 != empty){
            friendsList = new Friend[temp2.size()];
            for(int i = 0; i < temp2.size(); i++){
                try {
                    JSONObject user = new JSONObject(friendsObjects.get(i));
                    friendsList[i] = new Friend(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }




        FriendsListAdapter adapter = new FriendsListAdapter(friendsList, getContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //if a person in the list is clicked
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(temp2 != empty){ //if there are users sent back and not the garbage initialized user
                    try {
                        UserUtil.setUserToView(new JSONObject(friendsObjects.get(i)));
                        UserUtil.setUserToViewIsFriend(true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //pull up the profile of the friend that's clicked on
                    Fragment fragment = new ProfileViewFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();

                }
                else{
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
                }

            }
        });

        view.findViewById(R.id.pendingFriendBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pull up the list of pending friends
                Fragment fragment = new PendingFriendsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment);
                fragmentTransaction.commit();
            }
        });

        listView.setAdapter(adapter);


    }

    /**
     * method to be called when the fragment is being attached
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
     * method to call when the fragment is being detached
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "Refreshed", Toast.LENGTH_SHORT).show();
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
