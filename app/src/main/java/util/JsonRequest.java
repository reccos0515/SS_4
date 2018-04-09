package util;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.conectar.conectar.ProfileViewFragment;
import com.conectar.conectar.R;
import com.conectar.conectar.SwipeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jessie on 2/19/2018.
 * Methods for sending volley requests to the server for JSONObjects, JSONArrays, and Strings
 * This also holds methods held in specific fragments to edit their UIs, because fragments
 * cannot send volley requests
 */

public class JsonRequest {

    /**
     * Sends a post request to a given url to add a new user
     * @param js json object to send
     * @param url url of where this request should be sent
     */
    public static void postNewUserRequest(JSONObject js, String url, final Context context){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,  js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
                        final SharedPreferences preferences = context.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
                        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys
                        Log.d("Post Request Status", ("successful, response:" + response.toString()));
                        try {
                            JSONArray ja = response.getJSONArray("users"); //get the json array with the user in it
                            JSONObject js = ja.getJSONObject(0); //pull the user from the array
                            int id = js.getInt("id"); //pull the id from the user
                            editor.putInt("ID", id); //save this in preference variables
                            editor.apply(); //apply this change
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        //todo make swipe page
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
    }

    /**
     * Sends the server a post request to verify a user's login information.  If the post is
     * successful, the user's session variables are set and they are "logged in"
     * @param js the formatted user object to be posted
     * @param url the url the user object will be posted to
     * @param context the context in which this method is used
     */
    public static void loginPostRequest(JSONObject js, String url, Context context){
        final Context context1 = context;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,  js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Post Request Status", ("successful, response:" + response.toString()));
                        final SharedPreferences preferences = context1.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
                        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys
                        String username = "";
                        int id = 0;
                        String bio = "";
                        String interests = "";
                        int status = 0;
                        Boolean success = false;
                        String message = "";
                        try {
                            success = response.getBoolean("success"); //get whether or not the request was successful from server
                            Log.d("loginPostRequest", "Success response from server: " + success);
                            editor.putBoolean("ISLOGGEDIN", success);
                            editor.apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(success){ //If server said it was successful
                            Log.d("loginPostRequest", "Entered success");
                            try { //grab the user's info from response object
                                JSONArray userArr = response.getJSONArray("users");
                                JSONObject user = userArr.getJSONObject(0);
                                Log.d("loginPostRequest", "user: " + user.toString());
                                username = user.getString("userName");
                                id = user.getInt("id");
                                bio = user.getString("bio");
                                interests = user.getString("interests");
                                status = user.getInt("status");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            editor.putString("USERNAME", username); //set the session variables
                            editor.putInt("ID", id);
                            editor.putString("BIO", bio);
                            editor.putString("INTERESTS", interests);
                            editor.putInt("STATUS", status);
                            editor.putBoolean("ISLOGGEDIN", true);
                            editor.apply();
                            //test whether or not the session variables are set and log it
                            String test = preferences.getString("USERNAME", "empty");
                            Log.d("loginPostRequest", "Logged in user is: " + test + " with id: " + id);
                        }
                        else{ //if the server sends back an error
                            try {
                                message = response.getString("message");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("loginPostRequest", "Error message from server: " + message);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
    }

    /**
     * Sends a PUT request for a JSONObject to the server.  Often used for updating a JSONObject.
     * @param js the JSONObject to be PUT
     * @param url the url that the request will be sent to
     */
    public static void jsonObjectPutRequest(JSONObject js, String url, Context context){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Object Put Status", "Successful Request");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Object Put Status", "error");
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    /**
     * Method to be used in Swipe Fragment to get the users to view
     * and to show them on the UI
     * @param nView view from that page so the UI can be updated
     * @param url url to send the request to
     * @param context context from that page
     */
    public static void swipeRequest(final View nView, String url, Context context){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                SwipeFragment.saveNewObject(response); //send this back to swipe fragment for saving
                boolean success; //hold the success value
                //set up the textviews
                TextView errorMessage = nView.findViewById(R.id.swipeMessage); //can print error message
                TextView firstName = nView.findViewById(R.id.swipeFirstName);
                TextView interest1 = nView.findViewById(R.id.swipeInterest1);
                TextView interest2 = nView.findViewById(R.id.swipeInterest2);
                TextView interest3 = nView.findViewById(R.id.swipeInterest3);
                TextView interest4 = nView.findViewById(R.id.swipeInterest4);
                TextView interest5 = nView.findViewById(R.id.swipeInterest5);
                Button button = nView.findViewById(R.id.swipeView);


                //check for success
                try{
                    success = (boolean) response.get("success"); //see if it succeeded or not
                    Log.d("swipeFragment", "Success status: " + success);
                }catch (JSONException e){
                    e.printStackTrace();
                    return;
                }
                if(!success){
                    //if failed, set text error message
                    try {
                        errorMessage.setText((String) response.get("message"));
                        Log.d("swipeFragment", "Error message from server: " + response.getString("message"));
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                    //set all interests as blank
                    firstName.setText("");
                    interest1.setText("");
                    interest2.setText("");
                    interest3.setText("");
                    interest4.setText("");
                    interest5.setText("");
                    //if the problem is the user is red, the button will be used to change the status
                    if(errorMessage.getText().toString().equals("User is RED")){
                        button.setText("Change status");
                    }
                }
                else{
                    errorMessage.setText(""); //if succeeded, can make the text view invisible
                    JSONObject user = new JSONObject(); //specific user
                    try{
                        JSONArray users = (JSONArray) response.get("users"); //pull the first user
                        Log.d("swipeRequest","Array of users from server: " + users.toString());
                        user = (JSONObject) users.get(0); //first user
                        Log.d("swipeRequest", "User from JSONArray: " + user.toString());
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    SwipeFragment.updateUI(user); //update the UI
                    UserUtil.setUserToView(user); //save this where profile view can access it if needed
                    UserUtil.setUserToViewIsFriend(false); //user is not friends with this person
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
        return;
    }

    /**
     * Sends a GET request to the server to obtain a list of friends that a certain user has.  The list
     * of users are stored in session variables to be used elsewhere
     * @param id the id number of the user who's friends we want a list of
     * @param context the context in which this method is used
     */
    public static void getFriendsList(int id, final Context context){
        String url =  "http://proj-309-ss-4.cs.iastate.edu:9001/ben/users/" + id +"/friends";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject js;
                js = response;
                Log.d("Friend", "getFriends response from GET: " + response);
                Boolean s = false;
                JSONArray users;
                String message = "";
                Context context1 = context;
                final SharedPreferences preferences = context1.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = preferences.edit();

                try {
                    s = js.getBoolean("success");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(js == null){
                    Log.d("Friend", "getFriends no response from server");
                }
                if(s){ //if the request was successful
                    try {
                        users = js.getJSONArray("users"); //get JSONArray of all users the user is friends with

                        Log.d("Friend", "Grabbing users, looks like: " + users.toString());
                        Set<String> friendsList = new HashSet<>();
                        Set<String> friendsJSON = new HashSet<>();
                        for(int i = 0; i < users.length(); i++){ //grab all friends out of the array
                            JSONObject thisFriend = users.getJSONObject(i); //get the user we're talking about
                            String thisUser = thisFriend.toString(); //convert it to a string for FRIENDSLISTJSONOBJECTS
                            String thisUsername = thisFriend.getString("userName"); //get the username
                            friendsList.add(thisUsername);
                            friendsJSON.add(thisUser);

                        }
                        editor.putStringSet("FRIENDSLISTUSERNAMES", friendsList);//shared pref variable for string usernames
                        editor.putStringSet("FRIENDJSON", friendsJSON); //sharedpref variable for string versions of all user objects for friends
                        editor.apply();
                        Log.d("JsonRequest", "Friendslistusernames set: " + preferences.getStringSet("FRIENDSLISTUSERNAMES", null));
                        Log.d("JsonRequest", "FRIENDSLISTJSONOBJECTS: " + preferences.getStringSet("FRIENDJSON", null));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{ //if the server returns an error, display it in logs
                    try {
                        js.getString("message");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("Friend", "getFriends error message: " + message);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
        return;
    }

    /**
     * method used to delete a user
     * @param js user to be deleted
     * @param url url to send this request to
     * @param context context from the activity where this is called
     */
    public static void deleteUserRequest(JSONObject js, String url, Context context){
        final Context context1 = context;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url,  js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Post Request Status", ("successful, response:" + response.toString()));
                        final SharedPreferences preferences = context1.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
                        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys
                        Boolean success = false;
                        String message = "";

                        try {
                            success = response.getBoolean("success"); //get whether or not the request was successful from server
                            Log.d("deleteUserRequest", "Success response from server: " + success);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(success){ //If server said it was successful
                            Log.d("loginPostRequest", "Entered success");
                            editor.clear();
                            editor.apply();
                        }
                        else{ //if the server sends back an error
                            try {
                                message = response.getString("message");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("loginPostRequest", "Error message from server: " + message);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            /* import com.android.volley.toolbox.HttpHeaderParser; */
            public void onErrorResponse(VolleyError error) {

                // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
    }

    public static void getMessages(int id, final Context context){
        String url =  "http://proj-309-ss-4.cs.iastate.edu:9001/ben/users/" + id +"/messages";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject js; //full object
                js = response;
                Log.d("JsonRequest", "getMessages response from GET: " + response);
                Boolean s = false;
                JSONArray messages;
                String successMessage = "";
                Context context1 = context;
                final SharedPreferences preferences = context1.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = preferences.edit();

                try {
                    s = js.getBoolean("success");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(js == null){
                    Log.d("JsonRequest", "getMessages no response from server");
                }
                if(s){ //if the request was successful
                    try {
                        messages = js.getJSONArray("message"); //get JSONArray of all of the messages a user has received

                        Log.d("JsonRequest", "Grabbing messages, looks like: " + messages.toString());
                        Set<String> messageSet = new HashSet<>();
                        for(int i = 0; i < messages.length(); i++){ //grab all friends out of the array1
                            JSONObject thisMessage = messages.getJSONObject(i); //get the user we're talking about
                            String strMessage = thisMessage.toString();
                            messageSet.add(strMessage); //add the message to the set for sharedPreferences
                        }
                        editor.putStringSet("MESSAGES", messageSet);//shared pref variable for string messages
                        editor.apply();
                        Log.d("JsonRequest", "messageSet set: " + preferences.getStringSet("MESSAGES", null));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{ //if the server returns an error, display it in logs
                    try {
                        js.getString("message");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("JsonRequest", "getMessages error message: " + successMessage);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
        return;
    }

    public static void getMessagesDelete(int id, final Context context){
        String url =  "http://proj-309-ss-4.cs.iastate.edu:9001/ben/users/" + id +"/messages";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject js; //full object
                js = response;
                Log.d("JsonRequest", "getMessages response from GET: " + response);
                Boolean s = false;
                JSONArray messages;
                String successMessage = "";
                Context context1 = context;
                final SharedPreferences preferences = context1.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = preferences.edit();

                try {
                    s = js.getBoolean("success");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(js == null){
                    Log.d("JsonRequest", "getMessages no response from server");
                }
                if(s){ //if the request was successful
                    try {
                        messages = js.getJSONArray("message"); //get JSONArray of all of the messages a user has received

                        Log.d("JsonRequest", "Grabbing messages, looks like: " + messages.toString());
                        Set<String> messageSet = new HashSet<>();
                        for(int i = 0; i < messages.length(); i++){ //grab all friends out of the array1
                            JSONObject thisMessage = messages.getJSONObject(i); //get the user we're talking about
                            String strMessage = thisMessage.toString();
                            messageSet.add(strMessage); //add the message to the set for sharedPreferences
                        }
                        editor.putStringSet("MESSAGES", messageSet);//shared pref variable for string messages
                        editor.apply();
                        Log.d("JsonRequest", "messageSet set: " + preferences.getStringSet("MESSAGES", null));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{ //if the server returns an error, display it in logs
                    try {
                        js.getString("message");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("JsonRequest", "getMessages error message: " + successMessage);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
        return;
    }

    public static void sendMessage(int id, JSONObject js, final Context context){
        String url =  "http://proj-309-ss-4.cs.iastate.edu:9001/ben/users/" + id +"/received_messages"; //TODO change if need be
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("JsonRequest", "sendMessage response from server: " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
        return;
    }



}
