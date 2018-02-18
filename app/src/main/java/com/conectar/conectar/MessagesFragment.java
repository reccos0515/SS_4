package com.conectar.conectar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import util.RequestJsonObject;
import util.Singleton;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MessagesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MessagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagesFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    String serverUrl = "http://proj-309-ss-4.cs.iastate.edu:9001/test";
    String str = "didn't work";
    TextView jsonTestMsg;
    String testString = "didn't work";


    public MessagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment MessagesFragment.
     */
    public static MessagesFragment newInstance(String param1, String param2) {
        MessagesFragment fragment = new MessagesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    /**
     * Method to save the string taken from the response listener in a global variable that can be accessed elsewhere in the program
     * This should be called within the onResponse listener, after the Array has been parsed
     * @param str2
     */
    public void saveString(String str2){
        str = str2;
        Log.d("Saved String", str);
        return;
    }

    /**
     * Method to set the text on the page to what was found in the JsonObject Array
     * This should be called in a seperate button than the JsonObject was originally requested in to force it to happen in the correct order.
     * Calling it within the same button where the JsonObject is recieved will cause setText() to be
     * called BEFORE the object has been received and saved (potentially due to multi-threading?), even if it is placed AFTER within the code
     * TODO figure out how to delay so setText can be called within the same onClick (aka force it to wait until the object is recieved, parsed, and saved)
     */
    public void setText(){
        jsonTestMsg.setText(str);
        Log.d("set text to", str);
        return;
    }
    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        jsonTestMsg = view.findViewById(R.id.jsonTestMsg); //message that is viewed on screen

        //TODO make JSONObjectPost method in a util class
        Button button = getView().findViewById(R.id.addUserReq); //The button for the POST request (sending a user to the server)
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject js = new JSONObject(); //Create the JSONObject
                try { //block makes sure we are creating a valid JSONObject
                    js.put("id", "4"); //information put in the JSONObject to be sent
                    js.put("userName","Ollie");
                    js.put("bio", "Ollie likes mangos");
                } catch (JSONException e) {
                    e.printStackTrace(); //Will let us know if what we make is not a JSONObject
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://proj-309-ss-4.cs.iastate.edu:9001/users",  js, //starts the POST request and sends our JSONObject
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) { //Is currently not receiving a response?
                                /*
                                try { //will always give exception, is why need try catch
                                    jsonTestMsg.setText(response.getString("userName")); //not sure if case sensitive or not on the string input
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                */

                            }
                        }, new Response.ErrorListener() { //If the response is weird
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show(); //Send an error on screen
                        error.printStackTrace(); //print out the error in Run

                    }
                });
                Singleton.getmInstance(getContext()).addToRequestQueue(jsonObjectRequest); //add json to queue
            }
        });

        //--------------------------------------GET REQUEST (Doesn't currently work b/c server sends arrays)-------------------------------------------------------------------------//
        //TODO make a JSONObjectGET method in a util class
        Button button2 = getView().findViewById(R.id.getUserBtn); //Initializes the button for the JSON GET to be executed (grab info on a user)
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://proj-309-ss-4.cs.iastate.edu:9001/users",  null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    JSONObject foundObject = response.getJSONObject("Ollie");
                                } catch (JSONException e) {
                                    e.printStackTrace(); //Prints error to Run
                                }

                                try { //will always give exception, is why need try catch
                                    jsonTestMsg.setText(response.getString("userName")); //should put the found string message in the textview
                                } catch (JSONException e) {
                                    e.printStackTrace(); //Prints error to Run
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                });
                Singleton.getmInstance(getContext()).addToRequestQueue(jsonObjectRequest); //add json to queue
            }
        });
        //----------------------------------------------------JSON ARRAY---------------------------------------------------------------//
        //TODO Make a JSONArrayGET method in a util class

        Button button3 = getView().findViewById(R.id.getUserArrayBtn);//Button for GET JSONArray (gets all users)
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://proj-309-ss-4.cs.iastate.edu:9001/users",  //may need typecasting to string on the null?
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try{
                                    JSONObject user = response.getJSONObject(0); //get first JsonObject, this is the first user
                                    testString = user.getString("userName"); //get the userName from the first user
                                    saveString(testString); //save this in a global variable

                                    //testString = firstUser;
                                    Log.d("First User", testString);
                                    // Loop through the array elements
//                                    for(int i=0;i<response.length();i++){
//                                        // Get current json object
//                                        JSONObject user = response.getJSONObject(i);
//
//                                        // Get the current student (json object) data
//                                        //String userName = user.getString("userName");
//                                        //String bio = user.getString("bio");
//                                        if(i == 0){
//                                            String firstUserName = user.getString("userName");
//                                            jsonTestMsg.setText(firstUserName);
//                                            Log.d("First Username", firstUserName);
//                                        }
//                                    }
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                });
                Singleton.getmInstance(getContext()).addToRequestQueue(jsonArrayRequest); //add json to queue
            }
        });

        //Button to view the JsonArray that was recieved. This would need to be called after Button3
        Button button4 =getView().findViewById(R.id.viewUserBtn);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
