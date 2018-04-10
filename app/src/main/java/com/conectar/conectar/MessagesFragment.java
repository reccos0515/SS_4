package com.conectar.conectar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import messaging.MessageListAdapter;
import messaging.MyMessage;

import messaging.User;
import util.UserUtil;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MessagesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MessagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagesFragment extends Fragment {


    private OnFragmentInteractionListener mListener; //TODO figure out if this is necessary


    public MessagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment MessagesFragment.
     */
    public static MessagesFragment newInstance() {
        MessagesFragment fragment = new MessagesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_messages);

//        RecyclerView mMessageRecycler = (RecyclerView) getView().findViewById(R.id.reyclerview_message_list);
//        MessageListAdapter mMessageAdapter = new MessageListAdapter(this, messageList);
//        mMessageRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys

        String url = "http://proj-309-ss-4.cs.iastate.edu:9001/ben/";
        int userIDNum = preferences.getInt("ID", 0);
        JSONObject user = UserUtil.getUserToView(); //get the user that should be shown
        int msgUserIDNum;
        try{
            msgUserIDNum = user.getInt("id");
        } catch (JSONException e){
            e.printStackTrace();
        }

        //todo create full url





        MyMessage.setContext(getContext());

        String message = "test message";
        String time = "test time";
        String username = "testUsername";
        String profileUrl = "testUrl";
        int id = 5;
        User testUser = new User(username, profileUrl, id);
        Log.d("MessagesFragment", "user: " + testUser.toString());
        MyMessage fakeMessage = new MyMessage(message, testUser, time);
        List<MyMessage> mMessageList = new ArrayList<MyMessage>();
        Log.d("MessagesFragment", "fakeMessage: " + fakeMessage);
        mMessageList.add(fakeMessage);
        Log.d("MessagesFragment", "mMessageList: " + mMessageList.toString());

        //ListView listView = (ListView) view.findViewById(R.id.reyclerview_message_list);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.reyclerview_message_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new MessageListAdapter(getContext(), mMessageList);
        recyclerView.setAdapter(adapter);

       MyMessage m = new MyMessage("hello");
       Log.d("MessagesFragment", m.toString());


        view.findViewById(R.id.button_chatbox_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Message Sent", Toast.LENGTH_LONG).show();
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
        // Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
