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
    private static JSONObject userJSONObject = new JSONObject();
    private static JSONArray tempArr;

    /**
     * Returns a JSONArray of all the friends a specific user has
     * @param context the context in which this method is used
     * @param id the id number of the friend whose friends list is wanted
     * @return the list of friends
     */
    public static JSONArray getFriends(Context context, String id){ //TODO test
        String url = urlBase + "/users/" + id + "/friends";
       tempArr = UserUtil.getArray(url, context);
        return tempArr;
    }

    /**
     * Returns a JSONArray of all the friends a specific user has
     * @param context the context in which this method is used
     * @param id the id number of the friend whose friends list is wanted
     * @return the list of friends
     */
    public static JSONArray getFriends(Context context, int id){ //TODO test
        String url = urlBase + "/users/" + id + "/friends";
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
        String url = urlBase + "/users/" + id + "pending_friends"; //TODO review url
        tempArr = UserUtil.getArray(url, context);
        return tempArr;
    }

    /**
     * Returns a JSONArray of all the pending friend requests a specific user has
     * @param context the context in which this method is used
     * @param id the id number of the friend whose friends list is wanted
     * @return the list of friends
     */
    public static JSONArray getPending(Context context, int id){ //TODO revisit, don't think it works
        String url = urlBase + "/users/" + id + "pending_friends"; //TODO review url
        tempArr = UserUtil.getArray(url, context);
        return tempArr;
    }

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
