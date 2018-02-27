package util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Maggie on 2/26/2018.
 * Superclass for user objects
 * Currently logged in user's variables will be stored in CurrentUser object, all other users will
 * be manipulated as regular Users
 */

public class User {

    private String userName; //idk if these should be private, yell at me if not
    private String userId;
    private String userPassword;
    private String userBio;
    private String userEmail;
    private int[] userInterests;

    /**
     * Constructor for creating a user object from a JSONObject.  Is used
     * when a JSONObject is received from the server to convert the JSONObject
     * to a useful form.
     * @param js JSONObject from server call
     */
    public User(JSONObject js){
        try {
            userName = js.getString("userName");
            userId = js.getString("id");
            userPassword = js.getString("password");
            userBio = js.getString("bio");
            userEmail = js.getString("email");

            //get interests from JSON
            JSONArray interests = js.getJSONArray("interests");
            userInterests = Arrays.copyOf(userInterests, interests.length()); //changes the userInterests array to size of interests from JSON
            for (int i = 0; i < userInterests.length; i++) {
                int temp = Integer.parseInt(interests.getString(i)); //grabs individual interest and converts to int
                userInterests[i] = temp; //places in useful array
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for user's username
     * @return the user's username
     */
    public String getUsername(){
        return userName;
    }

    /**
     * Getter for user id
     * @return the user's id
     */
    public String getUserId(){
        return userId;
    }

    /**
     * Getter for user's password
     * @return password
     */
    public String getPassword(){
        return userPassword;
    }

    /**
     * Getter for user's bio
     * @return user's bio
     */
    public String getBio(){
        return userBio;
    }

    /**
     * Getter for current user's email
     * @return current user's email
     */
    public String getUserEmail(){
        return userEmail;
    }

    /**
     * gets a list of user interests
     * @return an array of user interests
     */
    public int[] getInterests(){
        return userInterests;
    }
}
