package messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;

/**
 * Created by Maggie on 4/5/2018.
 */

public class Message {

    private static Context mContext;
    private static String time;

    public static String getMessage(){ //TODO implement
        return null;
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

    public static Message get(int position){//TODO implement (???)
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
