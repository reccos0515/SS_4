package util;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.conectar.conectar.R;
import com.conectar.conectar.SwipeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 3/23/2018.
 * Friend objects represent friends of a user.  Having a friend object helps in creating listviews
 * Also includes functionality to add or delete a friend
 * that have custom update-able items
 */

public class Friend {
    private String id;
    private String username;
    private String bio;
    private String interests;
    private static String urlBase = "http://proj-309-ss-4.cs.iastate.edu:9001/ben"; //base for all urls

    /**
     * Constructor for a friend
     */
    public Friend(){
        super();
    }


    /**
     * Constructor for a friend
     * @param username the username of the friend
     */
    public Friend(String username){
        super();
        this.username = username;
        this.bio = "empty";
        this.id = "emtpy";
        this.interests = "empty";
    }

    /**
     * Constructor for a friend
     * @param js a JSONObject of a friend
     */
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

    /**
     * Sends a friend request to a specific user using integer ids
     * @param userId user that the request is coming from
     * @param friendId user that the request is going to
     * @param context context in which this method is used
     */
    public static void makeFriend(int userId, int friendId, Context context){
        String url = urlBase + "/users/" + userId + "" + "/request_friend/" + friendId + "";
        UserUtil.postRequest(url, context);
    }


    //TODO implement functionality to delete a friend
    /**
     * Removes a friend from a user's friends list
     * @param userId the user who is deleting the friend
     * @param friendId the friend to be deleted from the user's list
     * @param context the context in which this method is used
     */
    public static void removeFriend(int userId, int friendId, Context context){
        String url = urlBase + "/users/" + userId + "/friends/" + friendId + ""; //TODO review url
        UserUtil.deleteRequest(url, context);
    }


    @Override
    public String toString(){
        return this.username;
    }

}
