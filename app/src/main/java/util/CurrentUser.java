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

    private static boolean isLoggedIn;
    private String url = "http://proj-309-ss-4.iastate.edu"; //base url for server
    private String[][] userJSON; //2D array for editing fields before conversion to a JSONObject for server

    public CurrentUser(JSONObject js) {
        super(js);
        isLoggedIn = true;
    }

    /**
     * Logs the user out, resets variables to pre-login value
     */
    public static void logoutUser(){
        //logs the user out
        if(isLoggedIn){
            isLoggedIn = false;
            //TODO destroy session variables
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
     * Sets the user's local variable for interests and updates
     * JSONObject for sending to server when necessary
     * @param interests an array of selected interests
     */
    public void setInterests(int[] interests){
        //set appropriate array spot with interests in userJSON
        updateJSONObject(userJSON);
    }

    /**
     * Adds interest to interest list.  Eventually, list is passed
     * to setInterests to be added to JSONObject
     * @param interest interest to be added
     */
    public void addInterest(int interest){
        int[] tempArr = getInterests();
        boolean interestSet = false;
        for(int i = 0; i < tempArr.length; i++){
            if(tempArr[i] == 0 && !interestSet){
                tempArr[i] = interest;
                interestSet = true;
            }
            else if(tempArr[i] == interest){
                interestSet = true;
            }
        }
        if(!interestSet){
            //TODO figure out how to toast or something to user
            Log.d("Interest Status", "Interest full, cannot be added");
        }
    }

    /**
     * Deletes an interest from the interest list.  Eventually, the list is passed
     * to setInterests to be added to JSONObject
     * @param interest interest to be deleted
     */
    public void deleteInterest(int interest){
        int[] tempArr = getInterests();
        boolean isDeleted = false;
        for(int i = 0; i < tempArr.length; i++){
            if(tempArr[i] == interest && !isDeleted){
                tempArr[i] = 0;
                isDeleted = true;
            }
        }
        if(!isDeleted){
            //TODO figure out how to toast or something to user
            Log.d("Interest Status", "Interest not in list, cannot be deleted");
        }
    }

    /**
     * Deletes all user interests.  List still needs to be passed
     * to setInterests to be added to JSONObject
     */
    public void deleteAllInterests(){
        int[] tempArr = getInterests();
        for(int i = 0; i < tempArr.length; i++){
            tempArr[i] = 0;
        }
    }


    /**
     * A string array of the list of users the current user is friends with
     * @return the list of friends
     */
    public String[] getFriends(){
        String[] friends = null;
        url += "/users/" + getUserId() + "/friends";
        JsonRequest.jsonArrayRequest(url); //send request for JSONObject array of friends
        //TODO receive array and parse into list
        return friends;
    }

    /**
     * Takes a string input of a user id and sends a friend request to that user
     * @param id the user the request will be sent to
     */
    public void makeFriend(String id){
        url += "/users/" + getUserId() + "/request_friend/" + id + ""; //TODO not sure if grabbing proper user id yet
        JsonRequest.postRequest(null, url);
    }

    /**
     * Takes an int input of a user id and sends a friend request to that user
     * @param id the user the request will be sent to
     */
    public void makeFriend(int id){
        url += "/users/" + getUserId() + "/request_friend/" + id + ""; //TODO not sure if grabbing proper user id yet
        JsonRequest.postRequest(null, url);
    }

    /**
     * Takes a string input of a user id and removes a user from current user's friend list
     * @param id the user to be removed
     */
    public void removeFriend(String id){
        url += "/users/" + getUserId() + "/friends/" + id + ""; //TODO not sure if grabbing proper user id yet
        JsonRequest.deleteRequest(url);
    }

    /**
     * Takes an int input of a user id and removes a user from curent user's friend list
     * @param id the user to be removed
     */
    public void removeFriend(int id){
        url += "/users/" + getUserId() + "/friends/" + id + ""; //TODO not sure if grabbing proper user id yet
        JsonRequest.deleteRequest(url);
    }

    public static void updateStatus(int status){ //TODO change type to whatever Ben does
        if(status  == 0){ //status is red
            //TODO set status in userJSON
            return; //did this to placate studio b/c I hate the yellow highlighting
        }
        else if(status == 1){ //status is yellow
            return;
        }
        else if(status == 2){ //status is green
            return;
        }
        else{
            Log.d("updateStatus", "wrong status input");
        }
        //updateJSONObject(userJSON);
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
        url += "/users";
        JsonRequest.jsonObjectPutRequest(js, url); //TODO check that putting here is correct for updating user
    }


}
