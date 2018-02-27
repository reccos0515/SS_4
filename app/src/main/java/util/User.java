package util;

import org.json.JSONException;
import org.json.JSONObject;

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

    public User(JSONObject js){
        //parse jsonobject into fields
        try {
            userName = js.getString("userName");
            userId = js.getString("id");
            userPassword = js.getString("password");
            userBio = js.getString("bio");
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
}
