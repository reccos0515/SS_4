package messaging;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 4/5/2018.
 * User objects for use in MessageListAdapter
 */

public class User {
    private String username;
    private int profile;
    private int id;


    /**
     * Constructor for a User object from a series of strings
     * @param username the username of the user
     * @param profile the url of the user's profile picture
     * @param id the id of the user
     */
    public User(String username, int profile, int id){
        this.username = username;
        this.profile = profile;
        this.id = id;
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
    public int getProfile(){
        return profile;
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
        return "username: " + username + "   profileURL: " + profile + "   id: " + id;
    }
}
