package util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Maggie on 3/5/2018.
 * Methods to make common android/server interactions regarding users easier
 */

public class UserUtil {

    //NOTE cannot change the username or id for a user

    private static String url = "http://proj-309-ss-4.iastate.edu:9001/ben"; //base url for server
    private static JSONObject userJSONObject = new JSONObject();
    private static JSONArray jsonArray = new JSONArray();
    private static JSONArray jsonArray2 = new JSONArray();
    private static JSONObject profView = null; //user that can be viewed in profile view next

    public static JSONObject prepareLogin(String username, String password, Context context){
        JSONObject fullJS = new JSONObject();

        try { //TODO modify for how Ben wants info
            userJSONObject.put("userName", username);
            fullJS.put("user", userJSONObject);
            fullJS.put("password", password);
            Log.d("UserUtil", "JSONObject from prepareLogin " + fullJS.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fullJS;
    }


    /**
     * Changes the user's status in the DB and updates the session variables
     * @param status the new status the user wishes to switch to
     * @param context the context in which this method is used
     */
    public static void updateStatus(int status, Context context){
        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = context.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys

        url = "http://proj-309-ss-4.cs.iastate.edu:9001/ben/users";
        JSONObject js = new JSONObject();
        try {
            js.put("id", preferences.getInt("ID", 0));
            js.put("userName", preferences.getString("USERNAME", "empty"));
            js.put("bio", preferences.getString("BIO", "empty"));
            js.put("interests", preferences.getString("INTERESTS", "00000000000"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        //change status
        if(status  == 0){ //status is red
            try {
                js.put("status", 0); //sets the status in the user object
                editor.putInt("STATUS", 0);
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(status == 1){ //status is yellow
            try {
                js.put("status", 1);
                editor.putInt("STATUS", 1);
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(status == 2){ //status is green
            try {
                js.put("status", 2);
                editor.putInt("STATUS", 2);
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            Log.d("updateStatus", "wrong status input");
        }
        JsonRequest.jsonObjectPutRequest(js, url, context);

    }

    /**
     * Gets a user from the DB using a GET request
     * @param url url that the request will be sent to
     * @param context context in which this method is used
     * @return the user that was grabbed from the DB
     */
    public static JSONObject getUser(String url, Context context){ //TODO outer part is object not array
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        jsonArray = response; //grabs the array from the server's response
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonArrayRequest); //puts request in queue
        Boolean success = false;
        try {
            success = jsonArray.getBoolean(1); //grabs status message from request
        } catch (JSONException e) { //if something goes wrong with Volley
            e.printStackTrace();
        }


        String message = ""; //only actually message when success = false

        if(!success){
            try {
                message = jsonArray.getString(2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("Volley Request Error", message); //sends what went wrong to logs
        }
        else{
            try {
                userJSONObject = jsonArray.getJSONObject(2); //grabs user
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return userJSONObject; //returns the user for manipulation
    }

    /**
     * Updates a user in the DB with a PUT request
     * @param url url that the put request will be sent to
     * @param context context in which this method is used
     */
    public static void putUser(String url, JSONObject js, Context context){
        //update the DB to reflect deleted interests
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
     * Returns a JSONArray from a specific url
     * @param url url the request will be sent to
     * @param context context in which this method is used
     * @return the JSONArray returned from the server
     */
    public static JSONArray getArray(String url, Context context){ //TODO outer part is object not array
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, //grab first layer of array
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        jsonArray = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonArrayRequest); //add json to queue

        Boolean success = false;
        try {
            success = jsonArray.getBoolean(1); //grabs status message from request
        } catch (JSONException e) { //if something goes wrong with Volley
            e.printStackTrace();
        }


        String message = ""; //only actually message when success = false

        if(!success){
            try {
                message = jsonArray.getString(2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("Volley Request Error", message); //sends what went wrong to logs
        }
        else{
            try {
                jsonArray2 = jsonArray.getJSONArray(3); //grabs array of users
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonArray2;
    }

    /**
     * sends a post request to the server. Used for activities such as make friend
     * @param url to send the post request to
     * @param context of the activity the is being called from
     */
    public static void postRequest(String url, Context context){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,  null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Post Request Status", ("successful, response:" + response.toString()));
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
     * Request to delete, used for activities such as deleting a user or friend
     * @param url to send the request to
     * @param context of the activity this is being called from
     */
    public static void deleteRequest(String url, Context context){
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Delete Request Status", "Success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Delete Request Status", "Error");
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(stringRequest);
    }

    /**
     * method to get a user to be viewed in profile view, called from profile view
     * @return
     */
    public static JSONObject getUserToView(){
        return profView;
    }

    /**
     * method to set the user that would be viewed in Profile View
     * Called from other methods when there is a situation this user may
     * be viewed in, such as swipe or page to view own profile
     * @param user that would be viewed
     */
    public static void setUserToView(JSONObject user){
        profView = user;
        return;
    }




}
