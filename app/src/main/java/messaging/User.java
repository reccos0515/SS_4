package messaging;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 4/5/2018.
 * User objects for use in MessageListAdapter
 */

public class User {
    private static String username;
    private static String profileURL;
    private static int id;

    /**
     * Constructor for a User object
     */
    public User(){
        super();
    }

    /**
     * Constructor for a User object from a JSONObject of a user
     * @param user a user JSONObject
     */
    public User(JSONObject user){
        try{
            username = user.getString("userName");
            id = user.getInt("id");
            //todo add profile url
        }catch (JSONException e){
            e.printStackTrace();
        }
        //Log.d("User", "Used JSONObject constructor");
    }

    /**
     * Constructor for a User object from a series of strings
     * @param username the username of the user
     * @param profileURL the url of the user's profile picture
     * @param id the id of the user
     */
    public User(String username, String profileURL, int id){
        this.username = username;
        this.profileURL = profileURL;
        this.id = id;

        //Log.d("User", "username: " + username + "   profileURL: " + profileURL + "   id: " + id);
    }

    /**
     * Gets a user's username
     * @return the user's username
     */
    public String getUsername(){
        return username;
    }

    /**
     * Gets the url of a user's profile picture
     * @return the url of a user's profile picture
     */
    public String getProfileUrl(){
        return profileURL;
    }


    /**
     * Gets the id of a user
     * @return the user's id
     */
    public int getUserId(){
        return id;
    }

    /**
     * Creates a string displaying a user's username, profileUrl, and id
     * @return a formatted string of a user object
     */
    @Override
    public String toString() {
        return "username: " + username + "   profileURL: " + profileURL + "   id: " + id;
    }
}
