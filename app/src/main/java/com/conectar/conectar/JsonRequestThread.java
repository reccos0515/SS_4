package com.conectar.conectar;

import android.content.Context;

import util.JsonRequest;

/**
 * Class to create a seperate thread to call the jsonObjectRequest
 * Created by Jessie on 2/21/2018.
 */

//TODO figure out how to move this to util and still allow access from com.conectar.conectar
public class JsonRequestThread extends Thread {
    Context context; //context it was called from in the Fragment/Activity
    String url; //url will be sending request to
    JsonRequestThread(Context context, String url){
        this.context = context;
        this.url = url;
    }
    /**
     * Method to make the jsonObjectRequest call on the thread
     */
    public void run(){
        JsonRequest.jsonObjectRequest(url, context);
    }
}
