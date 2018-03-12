package util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 3/7/2018.
 * Methods pertaining to a user's friends
 */

public class FriendsUtil {

    private static String url = ""; //TODO update with base for all other urls
    private static JSONObject userJSONObject = new JSONObject();
    private static JSONArray tempArr;

    /**
     * Returns a JSONArray of all the friends a specific user has
     * @param context the context in which this method is used
     * @param id the id number of the friend whose friends list is wanted
     * @return the list of friends
     */
    public static JSONArray getFriends(Context context, String id){ //TODO revisit, don't think it works
        url = "";
        //TODO accommodate success status
       tempArr = UserUtil.getArray(url, context);

        return tempArr;
    }

    /**
     * Returns a JSONArray of all the pending friend requests a specific user has
     * @param context the context in which this method is used
     * @param id the id number of the friend whose friends list is wanted
     * @return the list of friends
     */
    public static JSONArray getPending(Context context, String id){ //TODO revisit, don't think it works
        url = "";
        //TODO accommodate success status
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

        try {
            tempArr = userJSONObject.getJSONArray("friends");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tempArr;
    }

    /**
     * Sends a friend request to a specific user using string ids
     * @param userId user that the request is coming from
     * @param friendId user that the request is going to
     * @param context context in which this method is used
     */
    public static void makeFriend(String userId, String friendId, Context context){
        url += "/users/" + userId + "/request_friend/" + friendId + "";
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
     * Sends a friend request to a specific user using integer ids
     * @param userId user that the request is coming from
     * @param friendId user that the request is going to
     * @param context context in which this method is used
     */
    public static void makeFriend(int userId, int friendId, Context context){
        url += "/users/" + userId + "/request_friend/" + friendId + "";
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
     * Removes a friend from a user's friends list
     * @param userId the user who is deleting the friend
     * @param friendId the friend to be deleted from the user's list
     * @param context the context in which this method is used
     */
    public static void removeFriend(String userId, String friendId, Context context){
        url += "/users/" + userId + "/friends/" + friendId+ ""; //TODO update url
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
     * Removes a friend from a user's friends list
     * @param userId the user who is deleting the friend
     * @param friendId the friend to be deleted from the user's list
     * @param context the context in which this method is used
     */
    public static void removeFriend(int userId, int friendId, Context context){
        url += "/users/" + userId + "/friends/" + friendId + ""; //TODO update url
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
}
