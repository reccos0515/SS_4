package util;


import org.json.JSONObject;

/**
 * Created by Maggie on 2/26/2018.
 * The currently logged in user
 */

public class CurrentUser extends User {
    boolean isLoggedIn;

    public CurrentUser(JSONObject js) {
        super(js);
        isLoggedIn = true;
    }

    /**
     * Logs the user out, resets variables to pre-login value
     */
    public void logoutUser(){
        //logs the user out
        if(isLoggedIn){
            isLoggedIn = false;
            //remove values from other variables
        }
    }

    public void setBio(){
        //set the user's session variable for bio
        //post to server from here????
    }


}
