package util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 3/5/2018.
 * Methods to make common android/server interactions regarding users easier
 * (Potential alternative to using User/CurrentUser objects, ask Maggie if she forgets to mention it)
 */

public class UserUtil {

    //NOTE cannot change the username or id for a user

    private static String url = "http://proj-309-ss-4.iastate.edu:9002/ben"; //base url for server
    private static String[][] userJSON; //2D array for editing fields before conversion to a JSONObject for server
    private static JSONObject userJSONObject = new JSONObject();
    private static JSONArray tempArr;

    public static void logoutUser(){
        //destroy bundle and remove shared preferences
    }

    /**
     * Updates the user's bio in the DB
     * @param bio a string of the typed bio from the user
     * @param context the context in which this method is used
     */
    public static void setBio(String bio, Context context){
        url = ""; //TODO update url
        try {
            userJSONObject.put("bio", bio);
        } catch (JSONException e) {
            Log.d("JSONObject Put Status", "Bio put failed");
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, userJSONObject, new Response.Listener<JSONObject>() {
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
     * Updates the user's interests in the DB
     * @param interests an array of selected interests
     * @param context the context in which this method is used
     */
    public static void setInterests(int[] interests, Context context){
        //set appropriate array spot with interests in userJSON
        String url = ""; //TODO update url
        try {
            userJSONObject.put("interests", interests);
        } catch (JSONException e) {
            Log.d("JSONObject Put Status", "Interests put failed");
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, userJSONObject, new Response.Listener<JSONObject>() {
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
     * Sets the user's password in the DB
     * @param pwd the new password that the user would like
     * @param context context in which this method is used
     */
    public static void setPassword(String pwd, Context context){
        String url = ""; //TODO update url
        try {
            userJSONObject.put("password", pwd);
        } catch (JSONException e) {
            Log.d("JSONObject Put Status", "Password put failed");
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, userJSONObject, new Response.Listener<JSONObject>() {
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
     * Adds a new interests to a user's list of interests if the list isn't full
     * @param newInterest the value for the new interest to be added
     * @param context the context in which this method is used
     */
    public static void addInterest(int newInterest, Context context){
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

        try {
            tempArr = userJSONObject.getJSONArray("interests"); //grabs the array of interests from user object
        } catch (JSONException e) {
            e.printStackTrace();
        }

        boolean interestSet = false;
        int temp = 0;
        int[] interests = new int[tempArr.length()]; //make interests correct size

        for(int i = 0 ; i < interests.length; i++){
            try {
                temp = tempArr.getInt(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(temp == newInterest){
                //interest already exists in list
                //TODO figure out how to toast that interests already exists to user
                interestSet = true;
            }
            else if(temp == 0 && !interestSet){ //open spot in interests array for new interest found
                interests[i] = newInterest;
                interestSet = true;
            }
            else{
                interests[i] = temp;
            }
        }
        if(!interestSet){
            //TODO figure out how to toast or something to user
            Log.d("Interest Status", "Interests full, cannot be added");
        }

        try {
            userJSONObject.put("interests", interests); //update the jsonobject for the user with new interests
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //update the JSONObject in the DB
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
     * Deletes an interest from the interest list.  Eventually, the list is passed
     * to setInterests to be added to JSONObject
     * @param badInterest interest to be deleted
     * @param context context in which this method is used
     */
    public static void deleteInterest(int badInterest, Context context){
        String url = ""; //TODO update url
        boolean isDeleted = false; //used to relay that the interest wasn't in the list in the first place

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
            tempArr = userJSONObject.getJSONArray("interests"); //grabs the array of interests from user object
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int[] interests = new int[tempArr.length()]; //array of correct size
        int temp = 0; //used to minimize number of times we need try/catch blocks

        for(int i = 0; i < interests.length; i++){
            try {
                temp = tempArr.getInt(i); //grab interest from array from DB
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(temp == badInterest && !isDeleted){ //if interest is found
                interests[i] = 0; //remove interest
                //TODO adjust as needed to show no interest
                isDeleted = true;
            }
            else {
                interests[i] = temp; //if it wasn't the interest to be deleted, keep it
            }
        }
        if(!isDeleted){ //interest wasn't in list and therefore couldn't be deleted
            //TODO figure out how to toast or something to user
            Log.d("Interest Status", "Interest not in list, cannot be deleted");
        }

        try {
            userJSONObject.put("interests", interests); //update JSONObject for user
        } catch (JSONException e) {
            Log.d("JSONObject Put Status", "Bio put failed");
            e.printStackTrace();
        }
        //update the JSONObject in the DB
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
     * Deletes all of a user's interests in the DB
     * @param context context in which this method is used
     * @param id id number for the user who will have their interests deleted
     */
    public static void deleteAllInterests(Context context, int id){
        int[] tempArr = {0,0,0,0,0};
        url = ""; //TODO update url for grabbing a specific user's information, should be same for putting I believe
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
            userJSONObject.put("interests", tempArr); //set JSONObject with deleted interests
        } catch (JSONException e) {
            e.printStackTrace();
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
     * A string array of the list of users the current user is friends with
     * @return the list of friends
     */
    public static String[] getFriends(Context context, String id){ //TODO update
        String[] friends = null;
        url += "/users/" + id + "/friends";

        //TODO receive array and parse into list
        return friends;
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


    public static void removeFriend(String userId, String friendId, Context context){//TODO update
        url += "/users/" + userId + "/friends/" + friendId+ "";
        JsonRequest.deleteRequest(url, context);
    }


    public static void removeFriend(int userId, int friendId, Context context){//TODO update
        url += "/users/" + userId + "/friends/" + friendId + "";
        JsonRequest.deleteRequest(url, context);
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
     * Updates the current user's JSONObject for server communications
     * @param fields a 2D array of the information for the fields of the JSONObject
     */
    private static void updateJSONObject(String[][] fields, Context context){ //TODO remove once other methods are updated to not piss them off
        //convert fields to JSONObject
        JSONObject js = new JSONObject();
        try {
            for(int i = 0; i < fields[0].length; i++){
                js.put(fields[0][i], fields[1][i]);
            }
            Log.d("createJsonObject Status", ("successful, " + js.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        url += "/users";
        JsonRequest.jsonObjectPutRequest(js, url, context); //TODO check that putting here is correct for updating user
    }

}
