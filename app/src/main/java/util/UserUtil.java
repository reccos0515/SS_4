package util;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 3/5/2018.
 * Methods to make common android/server interactions regarding users easier
 * (Potential alternative to using User/CurrentUser objects, ask Maggie if she forgets to mention it)
 */

public class UserUtil {

    private static String url = "http://proj-309-ss-4.iastate.edu:9002/ben"; //base url for server
    private static String[][] userJSON; //2D array for editing fields before conversion to a JSONObject for server

    public static void logoutUser(){
        //destroy bundle and remove shared preferences
    }

    /**
     * Set the user's local variable for bio
     * @param bio a string of the typed bio from the user
     */
    public static void setBio(String bio, Context context){
        //set appropriate array spot with interests in userJSON
        updateJSONObject(userJSON, context);
    }

    /**
     * Sets the user's local variable for interests and updates
     * JSONObject for sending to server when necessary
     * @param interests an array of selected interests
     */
    public static void setInterests(int[] interests, Context context){
        //set appropriate array spot with interests in userJSON
        updateJSONObject(userJSON, context);
    }

    /**
     * Adds interest to interest list.  Eventually, list is passed
     * to setInterests to be added to JSONObject
     * @param interest interest to be added
     */
    public static void addInterest(int interest){
        int[] tempArr = null;
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
    public static void deleteInterest(int interest){
        int[] tempArr = null;
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
    public static void deleteAllInterests(Context context){
        int[] tempArr = null;
        for(int i = 0; i < tempArr.length; i++){
            tempArr[i] = 0;
        }
        updateJSONObject(userJSON, context);
    }


    /**
     * A string array of the list of users the current user is friends with
     * @return the list of friends
     */
    public static String[] getFriends(Context context, String id){
        String[] friends = null;
        url += "/users/" + id + "/friends";

        //TODO receive array and parse into list
        return friends;
    }

    public static void makeFriend(String userId, String friendId, Context context){
        url += "/users/" + userId + "/request_friend/" + friendId + ""; //TODO not sure if grabbing proper user id yet
        JsonRequest.postRequest(null, url, context);
    }

    public static void makeFriend(int userId, int friendId, Context context){
        url += "/users/" + userId + "/request_friend/" + friendId + ""; //TODO not sure if grabbing proper user id yet
        JsonRequest.postRequest(null, url, context);
    }


    public static void removeFriend(String userId, String friendId, Context context){
        url += "/users/" + userId + "/friends/" + friendId+ ""; //TODO not sure if grabbing proper user id yet
        JsonRequest.deleteRequest(url, context);
    }


    public static void removeFriend(int userId, int friendId, Context context){
        url += "/users/" + userId + "/friends/" + friendId + ""; //TODO not sure if grabbing proper user id yet
        JsonRequest.deleteRequest(url, context);
    }

    public static void updateStatus(int status, Context context){ //TODO change type to whatever Ben does
        if(status  == 0){ //status is red
            //TODO set status in userJSON
            updateJSONObject(userJSON, context);
        }
        else if(status == 1){ //status is yellow
            //TODO set status in userJSON
            updateJSONObject(userJSON, context);
        }
        else if(status == 2){ //status is green
            //TODO set status in userJSON
            updateJSONObject(userJSON, context);
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
    private static void updateJSONObject(String[][] fields, Context context){
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
