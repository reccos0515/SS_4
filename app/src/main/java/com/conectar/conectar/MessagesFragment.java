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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import messaging.MessageListAdapter;
import messaging.MessagesUtil;
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
    private EditText newMessage;
    int msgUserIDNum = 0; //id of the user the logged in user is having a conversation with
    int userIDNum = 0; //id of the user who is logged in
    List<MyMessage> mMessageList = new ArrayList<MyMessage>();
    boolean newMessagesFragment = true;


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
        newMessagesFragment = true;
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = getActivity().getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)

        newMessage = view.findViewById(R.id.edittext_chatbox); //typed message from user
        userIDNum = preferences.getInt("ID", 0); //id of logged in user

        //grab the user id of the user the logged in user is having a conversation with
        JSONObject user = UserUtil.getUserToView(); //get the user that should be shown
        try{
            msgUserIDNum = user.getInt("id");
        } catch (JSONException e){
            e.printStackTrace();
        }

        //set up the recyclerview to display messages
        MyMessage.setContext(getContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.reyclerview_message_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final MessageListAdapter adapter = new MessageListAdapter(getContext(), mMessageList);
        recyclerView.setAdapter(adapter);

        if(newMessagesFragment) {
            Log.d("MessagesFragment", "Entered first setup of messages");
            newMessagesFragment = false;
            Set<String> setConversation = preferences.getStringSet("MSGFROM" + msgUserIDNum, null); //grab conversation from Volley request
            if (setConversation != null) {

                MyMessage[] temp = new MyMessage[setConversation.size()];
                if (!setConversation.isEmpty()) {
                    List<String> listConversation = new ArrayList<>(setConversation);
                    JSONObject messageJSONObject = null;

                    for (int i = 0; i < setConversation.size(); i++) {
                        try {
                            MyMessage temp2 = null;
                            messageJSONObject = new JSONObject(listConversation.get(i));
                            temp[i] = new MyMessage(messageJSONObject);
                            if (i == listConversation.size() - 1) {
                                Log.d("MessagesFragment", "temp before adding to mMessageList: " + Arrays.toString(temp));
                                mMessageList.clear();
                                mMessageList.addAll(Arrays.asList(temp));
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }


        view.findViewById(R.id.button_chatbox_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String messageString = newMessage.getText().toString(); //the message that the user typed
                if(!messageString.equals("")){ //if it isn't empty
                    Set<String> setConversation = preferences.getStringSet("MSGFROM" + msgUserIDNum, null); //grab conversation from Volley request
                    if(setConversation != null) {

                        MyMessage[] temp = new MyMessage[setConversation.size()];
                        if (!setConversation.isEmpty()) {
                            List<String> listConversation = new ArrayList<>(setConversation);
                            JSONObject messageJSONObject = null;

                            for (int i = 0; i < setConversation.size(); i++) {
                                try {
                                    MyMessage temp2 = null;
                                    messageJSONObject = new JSONObject(listConversation.get(i));
                                    temp[i] = new MyMessage(messageJSONObject);
                                    if (i == listConversation.size() - 1) {
                                        Log.d("MessagesFragment", "temp before adding to mMessageList: " + Arrays.toString(temp));
                                        mMessageList.clear();
                                        mMessageList.addAll(Arrays.asList(temp));
                                        adapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Toast.makeText(getActivity(), "Message Sent", Toast.LENGTH_LONG).show();
                    MyMessage newMessage = new MyMessage(messageString);

                    Log.d("MessagesFragment", "Typed message from user: " + messageString + "   Time: " + newMessage.getCreatedAt());
                    JSONObject messageObject = MessagesUtil.prepareSentMessage(messageString, newMessage.getCreatedAt());
                    Log.d("MessagesFragment", "message object prepared for sending: " + messageObject.toString());
                    MessagesUtil.sendMessage(userIDNum, msgUserIDNum, messageObject, getContext());

                    MessagesUtil.getConversation(userIDNum, msgUserIDNum, getContext());
                }else {
                    Log.d("MessagesFragment", "Entered blank click of the send button");
                        Set<String> setConversation = preferences.getStringSet("MSGFROM" + msgUserIDNum, null); //grab conversation from Volley request
                        Log.d("MessagesFragment", "msgUserIDNum: " + msgUserIDNum);
                        if(setConversation != null) {
                            Log.d("MessagesFragment", "setConversation: " + setConversation.toString());
                            MyMessage[] temp = new MyMessage[setConversation.size()];
                            if (!setConversation.isEmpty()) {
                                List<String> listConversation = new ArrayList<>(setConversation);
                                JSONObject messageJSONObject = null;

                                for (int i = 0; i < setConversation.size(); i++) {
                                    try {
                                        MyMessage temp2 = null;
                                        messageJSONObject = new JSONObject(listConversation.get(i));
                                        temp[i] = new MyMessage(messageJSONObject);
                                        if (i == listConversation.size() - 1) {
                                            Log.d("MessagesFragment", "temp before adding to mMessageList on blank send click: " + Arrays.toString(temp));
                                            mMessageList.clear();
                                            mMessageList.addAll(Arrays.asList(temp));
                                            adapter.notifyDataSetChanged();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                }
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
