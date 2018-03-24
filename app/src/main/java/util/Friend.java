package util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 3/23/2018.
 * Friend objects represent friends of a user.  Having a friend object helps in creating listviews
 * that have custom update-able items
 */

public class Friend {
    private String id;
    private String username;
    private String bio;
    private String interests;
    private static JSONObject js = new JSONObject();
    private static JSONArray users = new JSONArray();

    public Friend(){
        super();
    }

    public Friend(String id, String username, String bio, String interests){
        super();
        this.bio = bio;
        this.id = id;
        this.username = username;
        this.interests = interests;
    }

    public Friend(JSONObject js){
        super();
        try {
            this.bio = js.getString("bio");
            this.id = js.getString("id");
            this.username = js.getString("userName");
            this.interests = js.getString("interests");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lists all of the information regarding a Friend object
     * @return string of all Friend object attributes
     */
    public String fullFriendInfo(){
        return "Id: " + this.id + "   Username: " + this.username + "   Bio: " + this.bio + "   Interests: " + this.interests;
    }

    public static Friend[] getFriends(String id, Context context){
        String url = "https://proj-309-ss-4.cs.iastate.edu:9001/ben/users/" + id + "/friends";
        Friend[] friends = null;
        String success = "";
        Boolean s = false;
        String message = "";

        //get the object with the list of friends within it
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                js = response;
                Log.d("Friend", "getFriends response from GET: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest);

        try {
            //success = js.getString("success");
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
                friends = new Friend[users.length()]; //makes the array of friends the correct size
                Log.d("Friend", "Grabbing users, looks like: " + users.toString());
                for(int i = 0; i < users.length(); i++){ //grab all friends out of the array
                    Friend newFriend = new Friend(users.getJSONObject(i)); //convert them to friend objects
                    friends[i] = newFriend; //store them in friends
                }
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

        //Log.d("Friend", "List of friends: " + friends.toString());
        return friends;
    }

    @Override
    public String toString(){
        return this.username;
    }

}
