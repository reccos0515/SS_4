package messaging;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * A single message object holds the message, the user, and the time
 * Created by Maggie on 4/5/2018.
 */

public class MyMessage {

    private static Context mContext;
    private String time;
    private String message;
    private User user; //user needs id, username, and profile pic

    /**
     * The constructor for a MyMessage
     */
    public MyMessage(){
        super();
    }

    /**
     * The constructor for a MyMessage
     * This constructor is used to create a new message that the current user has created
     * @param s a string of the full message object
     */
    public MyMessage(String s){
        final SharedPreferences preferences = mContext.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)

        message = s;
        //may need to update this
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("HH:mm"); //Suppress is so there isn't yellow crap telling us to ask the user for location
        Date date = new Date();
        time = formatDateTime(dateFormat.format(date));
        user = new User(preferences.getString("USERNAME", ""), preferences.getInt("PROFILEPICTURE", 0), preferences.getInt("ID", 0));
        Log.d("MyMessage", "In MyMessage string constructor");
    }


    /**
     * This constructor is used to create a MyMessage object from a json object that has been
     * created from another user
     *
     * @param js must include message, time, and userFrom. userFrom must include id, userName, and profilePicture
     */
    public MyMessage(JSONObject js){
        JSONObject userFrom;
        String userFromUsername;
        int userFromId;
        int profile;

        try {
            userFrom = js.getJSONObject("userFrom"); //pull the user

            //set message and time
            message = js.getString("message");
            time = js.getString("time");

            //create the user
            userFromUsername = userFrom.getString("userName");
            userFromId = userFrom.getInt("id");
            profile = userFrom.getInt("profilePicture");
            user = new User(userFromUsername, profile, userFromId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the message
     * @return message from a MyMessage object
     */
    public String getMessage(){ //TODO implement
        return message;
    }


    /**
     * Gets who sent a message
     * need to send context first with setContext()
     * @return the sender of the MyMessage object
     */
    public User getSender(){
        return user;
    }

    /**
     * Formats the date/time for display within messages
     * @param createdAt the time the message was created
     * @return newly formatted date/time
     */
    public String formatDateTime(String createdAt){
        int hour = (createdAt.charAt(0) - '0') * 10 + createdAt.charAt(1) - '0';
        hour -= 5;
        if(hour == 0){
            return "12:" + createdAt.charAt(2) + createdAt.charAt(3) + createdAt.charAt(4) + " AM";
        }
        else if(hour < 12){
            return hour + "" + createdAt.charAt(2) + createdAt.charAt(3) + createdAt.charAt(4) + " AM";
        }
        else if(hour == 12){
            return hour + "" + createdAt.charAt(2) + createdAt.charAt(3) + createdAt.charAt(4) + " PM";
        }
        else{
            return (hour - 12) + "" + createdAt.charAt(2) + createdAt.charAt(3) + createdAt.charAt(4) + " PM";
        }
    }

    /**
     * Gets when the message was created
     * @return when the message was created
     */
    public String getCreatedAt(){
        return time;
    }


    /**
     * sets the context for a MyMessage object
     * @param context the context in which the MyMessage object is used
     */
    public static void setContext(Context context){
        mContext = context;
    }

    /**
     * Returns the time, message, and user of a MyMessage object in string format
     * @return a formatted string with a MyMessage's time, message, and user
     */
    @Override
    public String toString() {
        return "time: " + time + "   message: " + message + "   user: {" + user.toString() + "}";
    }
}
