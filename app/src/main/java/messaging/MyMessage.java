package messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Maggie on 4/5/2018.
 */

public class MyMessage {

    private static Context mContext;
    private static String time;
    private static String message;

    public MyMessage(){
        super();
    }

    public MyMessage(String s){
        message = s;
        //may need to update this
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        time = formatDateTime(dateFormat.format(date));
        Log.d("time ", time);
    }

    public static String getMessage(){ //TODO implement
        return message;
    }


    /**
     * need to send context first with setContext()
     * @return
     */
    public static User getSender(){//TODO implement
        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = mContext.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        User user = new User(preferences.getString("USERNAME", ""), "", preferences.getInt("ID", 0));
        return user;
    }

    public static MyMessage get(int position){//TODO implement (???)
        return null;
    }

    public static String formatDateTime(String createdAt){
        int hour = (createdAt.charAt(0) - '0') * 10 + createdAt.charAt(1) - '0';
        hour -= 6;
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

    public static String getCreatedAt(){
        return time;
    }


    public static void displayRoundImageFromUrl(Context context, String url, ImageView profileImage){

    }
    public static void setContext(Context context){
        mContext = context;
        return;
    }


}
