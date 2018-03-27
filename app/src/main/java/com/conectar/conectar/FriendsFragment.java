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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Friend;
import util.FriendsUtil;
import util.JsonRequest;
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
public class FriendsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false); //opens the logout screen


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getContext();
        final SharedPreferences preferences = context.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE);
        //final SharedPreferences.Editor editor = preferences.edit();
        int id = preferences.getInt("ID", 0);

        ListView listView = (ListView) view.findViewById(R.id.friendsListView);
        JsonRequest.getFriendsList(id, context); //get a list of friends and store in sharedpreferences

        //Friend[] grabbedFriends = Friend.getFriends(id, context);
        Friend[] friendsList = {new Friend("empty")}; //put a default value to be grabbed in case user doesn't have friends
        JSONObject initial = new JSONObject();
        final JSONObject[] friendsJSONObjects = {initial};
        Set<String> temp = preferences.getStringSet("FRIENDSLISTUSERNAMES", null); //get the list of usernames from shared preferences

        //a bunch of garbage to initialize temp2 so friendsObjects doesn't bug out
        String[] fake = new String[]{"empty"};
        Set<String> empty = new HashSet<>(Arrays.asList(fake));
        Set<String> temp2 = preferences.getStringSet("FRIENDJSON", empty);
        Log.d("FriendsFragment", "FRIENDSLISTJSONOBJECTS: " + temp2.toString());

        if(temp != null){ //gets cranky trying to typecast null
            List<String> friends = new ArrayList<String>(temp);

            friendsList = new Friend[friends.size()];
            for(int i = 0; i < friends.size(); i++){
                Friend newFriend = new Friend(friends.get(i));
                friendsList[i] = newFriend;


            }
        }
        if(temp2 != null){
            List<String> friendsObjects = new ArrayList<String>(temp2);
            for(int i = 0; i < temp2.size(); i++){
                try {
                    JSONObject user = new JSONObject(friendsObjects.get(i));
                    Log.d("FriendsFragment", "User conversion: " + user);
                    friendsJSONObjects[i] = user;
                    Log.d("FriendsFragment", "friendsJSONObjects: "+ friendsJSONObjects.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }



        Log.d("FriendsFragment", "Friendslist: " + friendsList.toString());

        ArrayAdapter<Friend> adapter = new ArrayAdapter<Friend>(getActivity(), android.R.layout.simple_list_item_1, friendsList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), ((TextView) view).getText() + " " + Integer.toString(i) , Toast.LENGTH_LONG).show();
                UserUtil.setUserToView(friendsJSONObjects[i]);
                Fragment fragment = new ProfileViewFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment);
                fragmentTransaction.commit();
            }
        });

        listView.setAdapter(adapter);


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
