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

        newMessage = view.findViewById(R.id.edittext_chatbox); //typed message from user


        String url = "http://proj-309-ss-4.cs.iastate.edu:9001/ben/";
        int userIDNum = preferences.getInt("ID", 0);
        Log.d("MessagesFragment", getContext().toString());
        /*
        JSONObject user = UserUtil.getUserToView(); //get the user that should be shown
        int msgUserIDNum;
        try{
            msgUserIDNum = user.getInt("id");
        } catch (JSONException e){
            e.printStackTrace();
        }
        */
        //todo create full url

        MyMessage.setContext(getContext());


        final List<MyMessage> mMessageList = new ArrayList<MyMessage>();
        //Log.d("MessagesFragment", "fakeMessage: " + fakeMessage);
        /*
        MyMessage fakeMessage = createFakeMessage();
        mMessageList.add(fakeMessage);
        MyMessage fakeMessage2 = createFakeMessage2();
        mMessageList.add(fakeMessage2);
        Log.d("MessagesFragment", "mMessageList: " + mMessageList.toString());
        */

        //ListView listView = (ListView) view.findViewById(R.id.reyclerview_message_list);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.reyclerview_message_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final MessageListAdapter adapter = new MessageListAdapter(getContext(), mMessageList);
        recyclerView.setAdapter(adapter);

       //Log.d("MessagesFragment", "m: " + m.toString());


        view.findViewById(R.id.button_chatbox_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //MyMessage m = new MyMessage("hello");
                //mMessageList.add(m);
                //adapter.notifyDataSetChanged();

                String messageString = newMessage.getText().toString();
                if(!messageString.equals("")){
                    MyMessage[] temp = MessagesUtil.convertToMyMessage(getContext(), 2);
                    mMessageList.addAll(Arrays.asList(temp));
                    Log.d("MessagesFragment", "mMessageList before setting adapter: " + mMessageList.toString());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Message Sent", Toast.LENGTH_LONG).show();
                }
                //Date thisDate = new Date(System.currentTimeMillis());
                //MyMessage newMessage = new MyMessage(messageString);

                //Log.d("MessagesFragment", "Typed message from user: " + messageString + "   Time: " + newMessage.getCreatedAt());
                //JSONObject messageObject = MessagesUtil.prepareSentMessage(messageString, newMessage.getCreatedAt());
                //Log.d("MessagesFragment", "message object prepared for sending: " + messageObject.toString());
                //MessagesUtil.sendMessage(2, 1, messageObject, getContext());

                //MessagesUtil.getConversation(2, 1, getContext());



            }
        });
    }

    /**
     * this method is used to create a fake message. It is only used for testing.
     * the signature is myMessage createFakeMessage(), and it takes no parameters
     * but returns a message
     * @return the new fake message
     */
    public MyMessage createFakeMessage(){
        String message = "I like dogs";
        String time = "test time";
        String username = "Frank";
        String profileUrl = "1";
        int id = 3;
        User testUser = new User(username, profileUrl, id);
        //Log.d("MessagesFragment", "createFakeMessage: " + testUser.toString());
        return new MyMessage(message, testUser, time, profileUrl);
    }

    public MyMessage createFakeMessage2(){
        String message = "I like cats";
        String time = "test time";
        String username = "Renaldo";
        String profileUrl = "2";
        int id = 4;
        User testUser = new User(username, profileUrl, id);
        //Log.d("MessagesFragment", "createFakeMessage2: " + testUser.toString());
        return new MyMessage(message, testUser, time, profileUrl);
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
