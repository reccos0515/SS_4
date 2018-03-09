package util;

import android.content.Context;
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

/**
 * Created by Maggie on 3/5/2018.
 * Methods to make common android/server interactions regarding users easier
 */

public class UserUtil {

    //NOTE cannot change the username or id for a user

    private static String url = "http://proj-309-ss-4.iastate.edu:9002/ben"; //base url for server
    private static JSONObject userJSONObject = new JSONObject();
    private static JSONArray jsonArray = new JSONArray();


//    /**
//     * Updates the user's bio in the DB
//     * @param bio a string of the typed bio from the user
//     * @param context the context in which this method is used
//     */
//    public static void setBio(String bio, Context context){
//        url = ""; //TODO update url
//        try {
//            userJSONObject.put("bio", bio);
//        } catch (JSONException e) {
//            Log.d("JSONObject Put Status", "Bio put failed");
//            e.printStackTrace();
//        }
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, userJSONObject, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d("Object Put Status", "Successful Request");
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Object Put Status", "error");
//                error.printStackTrace();
//            }
//        });
//        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest);
//    }

    /**
     * Updates the user's bio in the DB
     * @param bio a string of the typed bio from the user
     * @param context the context in which this method is used
     */
    public static void setBio(String bio, Context context){
        url = ""; //TODO update url
        userJSONObject = getUser(url, context);
        try {
            userJSONObject.put("bio", bio);
        } catch (JSONException e) {
            Log.d("JSONObject Put Status", "Bio put failed");
            e.printStackTrace();
        }
        putUser(url, userJSONObject, context);
    }

//    /**
//     * Sets the user's password in the DB
//     * @param pwd the new password that the user would like
//     * @param context context in which this method is used
//     */
//    public static void setPassword(String pwd, Context context){
//        String url = ""; //TODO update url
//        try {
//            userJSONObject.put("password", pwd);
//        } catch (JSONException e) {
//            Log.d("JSONObject Put Status", "Password put failed");
//            e.printStackTrace();
//        }
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, userJSONObject, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d("Object Put Status", "Successful Request");
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Object Put Status", "error");
//                error.printStackTrace();
//            }
//        });
//        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest);
//    }

    public static void setPassword(String pwd, Context context){
        String url = ""; //TODO update url
        getUser(url, context);
        try {
            userJSONObject.put("password", pwd);
        } catch (JSONException e) {
            Log.d("JSONObject Put Status", "Password put failed");
            e.printStackTrace();
        }
        putUser(url, userJSONObject, context);
    }

    /**
     * Changes the user's status in the DB
     * @param status the new status the user wishes to switch to
     * @param id the id number of the person changing their status
     * @param context the context in which this method is used
     */
    public static void updateStatus(int status, int id, Context context){
        url = ""; //TODO update url
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,  null, //grab user
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        userJSONObject = response; //update for user grabbed
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest);

        //change status
        if(status  == 0){ //status is red
            try {
                userJSONObject.put("status", 0); //sets the status in the user object
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(status == 1){ //status is yellow
            try {
                userJSONObject.put("status", 1); //TODO revisit what value status should be set to
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(status == 2){ //status is green
            try {
                userJSONObject.put("status", 2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            Log.d("updateStatus", "wrong status input");
        }

        //update the DB to reflect deleted interests
        jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, userJSONObject, new Response.Listener<JSONObject>() {
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
     * Gets a user from the DB using a GET request
     * @param url url that the request will be sent to
     * @param context context in which this method is used
     * @return the user that was grabbed from the DB
     */
    public static JSONObject getUser(String url, Context context){
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
    public static JSONArray getArray(String url, Context context){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
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
        return jsonArray;
    }



}
