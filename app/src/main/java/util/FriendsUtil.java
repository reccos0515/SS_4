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

    private static String urlBase = "http://proj-309-ss-4.cs.iastate.edu:9001/ben"; //base for all urls

    /**
     * Sends a friend request to a specific user using string ids
     * @param userId user that the request is coming from
     * @param friendId user that the request is going to
     * @param context context in which this method is used
     */
    public static void makeFriend(String userId, String friendId, Context context){
        String url = urlBase + "/users/" + userId + "/request_friend/" + friendId + "";
        UserUtil.postRequest(url, context);
    }

    /**
     * Sends a friend request to a specific user using integer ids
     * @param userId user that the request is coming from
     * @param friendId user that the request is going to
     * @param context context in which this method is used
     */
    public static void makeFriend(int userId, int friendId, Context context){
        String url = urlBase + "/users/" + userId + "/request_friend/" + friendId + "";
        UserUtil.postRequest(url, context);
    }

    //TODO implement functionality to delete a friend
    /**
     * Removes a friend from a user's friends list
     * @param userId the user who is deleting the friend
     * @param friendId the friend to be deleted from the user's list
     * @param context the context in which this method is used
     */
    public static void removeFriend(String userId, String friendId, Context context){
        String url = urlBase + "/users/" + userId + "/friends/" + friendId+ ""; //TODO review url
        UserUtil.deleteRequest(url, context);
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
}
