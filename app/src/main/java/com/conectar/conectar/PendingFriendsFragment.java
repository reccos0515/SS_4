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

import util.JsonRequest;


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

    private OnFragmentInteractionListener mListener;

    public PendingFriendsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PendingFriendsFragment.
     */

    public static PendingFriendsFragment newInstance(String param1, String param2) {
        PendingFriendsFragment fragment = new PendingFriendsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        int id = preferences.getInt("ID", 0); //get the logged in user's id

        ListView listView = (ListView) view.findViewById(R.id.pendingListView); //grabs the listview from the xml layout
        JsonRequest.getPendingFriends(id, context); //get a list of friends and store in sharedpreferences
        JsonRequest.getFriendsList(id, context); //get friends too so we can check who from pending is already accepted

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
