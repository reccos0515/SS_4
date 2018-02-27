package util;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 2/26/2018.
 * The currently logged in user
 */

public class CurrentUser extends User {

    private boolean isLoggedIn;
    private String[] interests;
    private String userEmail;

    public CurrentUser(JSONObject js) {
        super(js);
        isLoggedIn = true;
        try {
            userEmail = js.getString("email");
            //set interests from jsonobject
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
        //set the user's session variable for bio
        //post to server from here????
    }

    /**
     * Sets the user's local variable for interests
     * @param interests an array of selected interests
     */
    public void setInterests(String[] interests){
        //do things
        //post to server from here?
    }

    /**
     * gets a list of user interests
     * @return an array of user interests
     */
    public String[] getInterests(){
        return interests;
    }

    /**
     * A list of users the current user is friends with
     * @return the list of friends
     */
    public User[] getFriends(){ //TODO figure out whether or not to use User[] or String[] of ids
        //get a list of friends from server
        return null;
    }

    /**
     * Sends a friend request
     * @param id the user the request will be sent to
     */
    public void makeFriend(String id){

    }

    /**
     * Removes a user from current user's friend list
     * @param id the user to be removed
     */
    public void removeFriend(String id){

    }


}
