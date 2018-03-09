package util;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;

/**
 * Created by Maggie on 3/8/2018.
 * Methods for managing session variables for a user
 */

public class SessionUtil {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    //Keys
    private static final String PREF_NAME = "coNectarPref";
    private static final String IS_LOGIN = "false";
    private static final String KEY_USERNAME = "userName";
    private static final String KEY_ID = "id";
    private static final String KEY_STATUS = "status";
    private static final String KEY_FRIENDS = "friends";
    private static final String KEY_PENDING = "pending";
    private static final String KEY_INTERESTS = "interests";

    public SessionUtil(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void createLoginSession(String username, String id, String status){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_STATUS, status);
    }

}
