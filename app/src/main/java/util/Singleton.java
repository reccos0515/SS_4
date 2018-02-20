package util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Maggie on 2/17/2018.
 */

public class Singleton {
    private static Singleton mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;

    private Singleton(Context context){
        mContext = context;
        requestQueue = getRequestQueue();
    }


    public RequestQueue getRequestQueue(){
        if(requestQueue == null){ //initializes a requestqueue if none exist
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized Singleton getmInstance(Context context){
         if(mInstance == null){ //initializes a singleton
             mInstance = new Singleton(context);
         }
         return mInstance;
    }

    public<T> void addToRequestQueue(Request<T> request){ //IDK why theres the t thing
        requestQueue.add(request);
    }
}
