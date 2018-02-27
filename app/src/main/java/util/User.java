package util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 2/26/2018.
 * Superclass for user objects
 */

public class User {

    String userName;
    String userId;
    String userPassword;
    String userBio;

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

    public String getUsername(){
        return userName;
    }
    public String getUserId(){
        return userId;
    }
    public String getPassword(){
        return userPassword;
    }
    public String getBio(){
        return userBio;
    }
}
