package util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Maggie on 3/8/2018.
 * Methods for managing session variables for a user
 */

public class SessionUtil {

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static Context context;
    private static int PRIVATE_MODE = 0;

    //Keys
    private static final String PREF_NAME = "coNectarPref";
    private static final String IS_LOGIN = "false";
    private static final String KEY_USERNAME = "userName";
    private static final String KEY_ID = "id";
    private static final String KEY_STATUS = "status";
    private static final String KEY_FRIENDS = "friends"; //not sure if we even need to keep these on hand in session variables
    private static final String KEY_PENDING = "pending";
    private static final String KEY_BIO = "bio";
    private static final String KEY_INTERESTS = "interests";

    public SessionUtil(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    /**
     * Creates a new session and places a user's session variables in sharedPreferences
     * @param username the user's username
     * @param id the user's id number
     * @param status the user's current status
     */
    public static void createSession(String username, String id, String status){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_STATUS, status);
        editor.apply();
    }

    /**
     * Destroys a user's session variables in sharedPreferences
     */
    public static void destroySession(){
        editor.clear();
        editor.commit();
    }

    /**
     * Returns map of all of a user's session variables for their information from
     * sharedPreferences
     * @return a hashmap of the details
     */
    public static HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USERNAME, preferences.getString(KEY_USERNAME, null));
        user.put(KEY_ID, preferences.getString(KEY_ID, null));
        user.put(KEY_STATUS, preferences.getString(KEY_STATUS, null));
        return user;
    }

    /**
     * Returns the user's username as stored in sharedPreferences
     * @return the user's username
     */
    public static String getSessionusername(){
        return preferences.getString(KEY_USERNAME, null);
    }

    /**
     * Returns the user's current status as stored in sharedPreferences
     * @return the user's current status
     */
    public static String getSessionId(){
        return preferences.getString(KEY_ID, null);
    }

    /**
     * Returns the user's status stored in sharedPreferences
     * @return the user's current status
     */
    public static String getSessionStatus(){
        return preferences.getString(KEY_STATUS, null);
    }
    /**
     * Returns the user's interests stored in sharedPreferences
     * @return the user's current interests
     */
    public static String getSessionInterests(){
        return preferences.getString(KEY_INTERESTS, null);
    }

    /**
     * Returns the user's bio stored in sharedPreferences
     * @return the user's bio
     */
    public static String getSessionBio(){
        return preferences.getString(KEY_BIO, null);
    }

}
