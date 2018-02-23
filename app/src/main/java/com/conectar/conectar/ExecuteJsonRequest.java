package com.conectar.conectar;

import android.content.Context;
import android.os.AsyncTask;

import util.JsonRequest;

/**
 * Class to execute an asychronous task to send a json object request
 * Created by Jessie on 2/23/2018.
 */
public class ExecuteJsonRequest extends AsyncTask<String, Void, String> {
    Context context; //context to use for json object request

    /**
     * method to override onPreExecute, to set the context
     * @param context context of fragment this is being called from
     */
    protected void onPreExecute(Context context){
        this.context = context;
    }

    /**
     * Method to make the json obeject request
     * @param str the url in the 0 spot
     * @return Todo figure out what to send back
     */
    protected String doInBackground(String... str) {
        String url = str[0];
        JsonRequest.jsonObjectRequest(url, context);
        return "";
    }
}
