package util;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.conectar.conectar.R;
import com.conectar.conectar.SwipeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 3/23/2018.
 * Friend objects represent friends of a user.  Having a friend object helps in creating listviews
 * that have custom update-able items
 */

public class Friend {
    private String id;
    private String username;
    private String bio;
    private String interests;

    /**
     * Constructor for a friend
     */
    public Friend(){
        super();
    }


    /**
     * Constructor for a friend
     * @param username the username of the friend
     */
    public Friend(String username){
        super();
        this.username = username;
        this.bio = "empty";
        this.id = "emtpy";
        this.interests = "empty";
    }

    /**
     * Constructor for a friend
     * @param js a JSONObject of a friend
     */
    public Friend(JSONObject js){
        super();
        try {
            this.bio = js.getString("bio");
            this.id = js.getString("id");
            this.username = js.getString("userName");
            this.interests = js.getString("interests");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lists all of the information regarding a Friend object
     * @return string of all Friend object attributes
     */
    public String fullFriendInfo(){
        return "Id: " + this.id + "   Username: " + this.username + "   Bio: " + this.bio + "   Interests: " + this.interests;
    }


    @Override
    public String toString(){
        return this.username;
    }

}
