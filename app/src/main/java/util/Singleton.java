package util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Maggie on 2/17/2018.
 * A class to help simplify some of the processes needed to use Volley
 */

public class Singleton {
    private static Singleton mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;

    private Singleton(Context context){ //a getter so we can use the current context and request queue within Singleton
        mContext = context;
        requestQueue = getRequestQueue();
    }


    public RequestQueue getRequestQueue(){ //makes a new request queue if one does not already exist
        if(requestQueue == null){ //initializes a requestqueue if none exist
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized Singleton getmInstance(Context context){ //creates a new instance of Singleton if one does not already exist
         if(mInstance == null){ //initializes a singleton
             mInstance = new Singleton(context);
         }
         return mInstance;
    }

    public<T> void addToRequestQueue(Request<T> request){ //Adds a request to the end of the queue, IDK why there's the t thing
        requestQueue.add(request);
    }
}
