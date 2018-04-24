package util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Class used to assist volley requests by creating the queue
 * Created by Maggie on 2/17/2018.
 * A class that manages the request queue for JSON requests
 */

public class Singleton {
    private static Singleton mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;

    /**
     * Constructor Singleton
     * @param context the context in which this method is used
     */
    private Singleton(Context context){
        mContext = context;
        requestQueue = getRequestQueue();
    }

    /**
     * Checks if there is already a request queue in this instance of Singleton and creates
     * a new one if there isn't.
     * @return the request queue to be used
     */
    private RequestQueue getRequestQueue(){
        if(requestQueue == null){ //initializes a requestqueue if none exist
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    /**
     * Checks if there is already an instance of Singleton and creates a new one if there isn't.
     * @param context the context in which this method is used
     * @return the instance of Singleton to be used
     */
    public static synchronized Singleton getmInstance(Context context){
         if(mInstance == null){ //initializes a singleton
             mInstance = new Singleton(context);
         }
         return mInstance;
    }

    /**
     * Adds a request to the request queue
     * @param request the request to be adde
     * @param <T> idk //TODO figure out what this is
     */
    public<T> void addToRequestQueue(Request<T> request){ //IDK why theres the t thing
        getRequestQueue().add(request);
    }
}
