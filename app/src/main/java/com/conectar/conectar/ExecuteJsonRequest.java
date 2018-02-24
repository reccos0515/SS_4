package com.conectar.conectar;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import util.JsonRequest;

/**
 * Class to execute an asychronous task to send a json object request
 * Created by Jessie on 2/23/2018.
 */
public class ExecuteJsonRequest extends AsyncTask<String, Void, String> {
    /**
     * Method to make the json obeject request
     * @param str the url in the 0 spot
     * @return Todo figure out what to send back
     */
    protected String doInBackground(String... str) {
        Log.d("got to", "6");
        String url = str[0];
        Log.d("got to", "7");
        JsonRequest.jsonObjectRequest(url);
        Log.d("Thread will", "sleep now");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.d("Bye", "bye");
        return "";
    }
}
