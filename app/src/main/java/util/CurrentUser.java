package util;


import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 2/26/2018.
 * The currently logged in user
 */

public class CurrentUser extends User {

    private boolean isLoggedIn;
    private String url = "http://proj-309-ss-4.iastate.edu"; //base url for server
    private String[][] userJSON;

    public CurrentUser(JSONObject js) {
        super(js);
        isLoggedIn = true;
    }

    /**
     * Logs the user out, resets variables to pre-login value
     */
    public void logoutUser(){
        //logs the user out
        if(isLoggedIn){
            isLoggedIn = false;
            //remove values from other variables?
        }
    }

    /**
     * Set the user's local variable for bio
     * @param bio a string of the typed bio from the user
     */
    public void setBio(String bio){
        //set appropriate array spot with interests in userJSON
        updateJSONObject(userJSON);
    }

    /**
     * Sets the user's local variable for interests
     * @param interests an array of selected interests
     */
    public void setInterests(String[] interests){
        //set appropriate array spot with interests in userJSON
        updateJSONObject(userJSON);
    }


    /**
     * A list of users the current user is friends with
     * @return the list of friends
     */
    public User[] getFriends(){ //TODO figure out whether or not to use User[] or String[] of ids
        //get a list of friends from server
        url += "some garbage"; //TODO change to proper url
        JsonRequest.jsonArrayRequest(url); //send request for JSONObject array of friends
        //parse into array of friends???????
        return null;
    }

    /**
     * Sends a friend request
     * @param id the user the request will be sent to
     */
    public void makeFriend(String id){
        int friendId = Integer.parseInt(id); //converts id to an integer
        url += "some garbage" + id + ""; //TODO edit for proper url
        JsonRequest.postRequest(null, url);
    }

    /**
     * Removes a user from current user's friend list
     * @param id the user to be removed
     */
    public void removeFriend(String id){
        int friendId = Integer.parseInt(id); //converts id to an integer
        url += "some garbage" + id + ""; //TODO edit for proper url
        JsonRequest.postRequest(null, url);
    }

    /**
     * Updates the current user's JSONObject for server communications
     * @param fields a 2D array of the information for the fields of the JSONObject
     */
    private void updateJSONObject(String[][] fields){
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
        url += "garbage"; //TODO url for refreshing fields of JSONObject
        JsonRequest.postRequest(js, url);
    }


}
