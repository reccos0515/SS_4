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
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        time = dateFormat.format(date);
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

    public static String formatDateTime(String createdAt){//TODO implement
        return null;
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
