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
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import friends.Friend;
import friends.PendingFriendsAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PendingFriendsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PendingFriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 * This class is to view pending friend requests to the logged in user
 */
public class PendingFriendsFragment extends Fragment {
    Friend[] pendingList = {new Friend("empty")}; //put a default value to be grabbed in case user doesn't have friends

    private OnFragmentInteractionListener mListener;

    public PendingFriendsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PendingFriendsFragment.
     */

    public static PendingFriendsFragment newInstance() {
        PendingFriendsFragment fragment = new PendingFriendsFragment();
        return fragment;
    }

    /**
     * method to be called to create the fragment
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pending_friends, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getContext();
        final SharedPreferences preferences = context.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE);

        ListView listView = view.findViewById(R.id.pendingListView); //grabs the listview from the xml layout

        String[] fake = new String[]{"empty"};
        final Set<String> empty = new HashSet<>(Arrays.asList(fake));
        final Set<String> temp2 = preferences.getStringSet("PENDINGJSON", empty);
        final List<String> pendingObjects = new ArrayList<String>(temp2);

        if(temp2 != empty){
            pendingList = new Friend[temp2.size()];
            for(int i = 0; i < temp2.size(); i++){
                try {
                    JSONObject user = new JSONObject(pendingObjects.get(i));
                    pendingList[i] = new Friend(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        PendingFriendsAdapter adapter = new PendingFriendsAdapter(pendingList, getContext());

        view.findViewById(R.id.friendsListBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FriendsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment);
                fragmentTransaction.commit();
            }
        });

        listView.setAdapter(adapter);

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
