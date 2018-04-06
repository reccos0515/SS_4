package messaging;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 4/5/2018.
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
    }

    public User(String username, String profileURL, int id){
        this.username = username;
        this.profileURL = profileURL;
        this.id = id;
    }

    public static String getUsername(){
        return username;
    }

    public static String getProfileUrl(){
        return profileURL;
    }

    public static int getUserId(){
        return id;
    }
}
