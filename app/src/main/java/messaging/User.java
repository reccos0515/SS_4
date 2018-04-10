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

    public User(){
        super();
    }

    public User(JSONObject user){
        try{
            username = user.getString("userName");
            id = user.getInt("id");
            //todo add profile url
        }catch (JSONException e){
            e.printStackTrace();
        }
        Log.d("User", "Used JSONObject constructor");
    }

    public User(String username, String profileURL, int id){
        this.username = username;
        this.profileURL = profileURL;
        this.id = id;

        Log.d("User", "username: " + username + "   profileURL: " + profileURL + "   id: " + id);
    }

    public String getUsername(){
        return username;
    }

    public String getProfileUrl(){
        return profileURL;
    }

    public int getUserId(){
        return id;
    }

    @Override
    public String toString() {
        return "username: " + username + "   profileURL: " + profileURL + "   id: " + id;
    }
}
