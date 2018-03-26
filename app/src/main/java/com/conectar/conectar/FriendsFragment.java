package com.conectar.conectar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;
import java.util.Set;

import util.Friend;
import util.FriendsUtil;
import util.JsonRequest;
import util.Singleton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static JSONArray friends;
    private static JSONArray pending;
    String[] friendsUsernames;
    int[] friendsIds;

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

        //Initialize the textviews on the friends page
        Context context = getContext();
        final SharedPreferences preferences = context.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        //TextView actualFriend = view.findViewById(R.id.friend1); //First full friend
        //TextView pendingFriend = view.findViewById(R.id.pendingFriend1); //First pending friend
        int id = preferences.getInt("ID", 0);

        ListView listView = (ListView) view.findViewById(R.id.friendsListView);

        //Friend[] grabbedFriends = Friend.getFriends(id, context);
        JsonRequest.getFriendsList(id, context);
        Set<String> temp = preferences.getStringSet("FRIENDSLISTUSERNAMES", null);
        List<String> friends = new ArrayList<String>(temp);
        Friend[] friendsList = new Friend[friends.size()];
        for(int i = 0; i < friends.size(); i++){
            Friend newFriend = new Friend(friends.get(i));
            friendsList[i] = newFriend;
        }
        Log.d("FriendsFragment", "Friendslist: " + friendsList.toString());

        //Log.d("FriendsFragment", "Grabbed friends list: " + grabbedFriends.toString());

        Friend[] testFriends = {
            new Friend("4", "Lena", "I am a cat", "00000000000"),
                new Friend("2", "Simon", "I am also a cat", "00000000000"),
                new Friend("5", "Ollie", "I am a dog", "00000000000"),
                new Friend("7", "Sparkle", "I do not know what I am", "00000000000"),
                new Friend("12", "Pepper", "I am concerned", "00000000000"),
        };

        ArrayAdapter<Friend> adapter = new ArrayAdapter<Friend>(getActivity(), android.R.layout.simple_list_item_1, friendsList);

        // listView.setOnItemClickListener(new ListClickHandler());
        listView.setAdapter(adapter);

        /*
        friends = FriendsUtil.getFriends(context, id);
        for(int i = 0; i < friends.length(); i++){ //set arrays for listing items
            JSONObject tempUser = new JSONObject();
            try {
                tempUser = friends.getJSONObject(i);
                friendsUsernames[i] = tempUser.getString("username");
                friendsIds[i] = tempUser.getInt("id"); //TODO is id an int or string?
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        */

//        ListAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.fragment_friends, R.id.friendUsername, friendsUsernames);
//        ListView listView = (ListView) view.findViewById(R.id.friendsListView); //grabs the list view our items will be in
//        listView.setAdapter(listAdapter); //tells list view to use the items we have
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
