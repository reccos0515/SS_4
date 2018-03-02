package com.conectar.conectar;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import util.JsonRequest;

//"By running it and following the logs, can see that it does everything in Profile
//        View up to the getObj from JsonRequest class (#5 is after this request and never prints, because
//        this never returns).  It then enters getObj() correctly and starts the loop until it is 'ready'(
//        which is never happening). It yields each time within this loop, meaning the other thread /should
//        / be able to run. Next we see the Async class (ExecuteJsonRequest) has started. It calls jsonObje
//        ctRequest in the JsonRequest class. This creates the response listener and exits, returning back
//        to the Async class. This then sleeps for what /should/ be enough time to ensure it does not exit
//        and potentially kill the response listener thread before it is done executing. The variables need
//        ed by both threads in the JsonRequest class are volatile (Ready and the js object), so the same v
//        ariable /should/ be viewable by both threads. Things that are definitely not happening are that t
//        he response listener gets a response (would print "got a response", or "received an error") and t
//        hat it continues in profile view after the getObj() call (would print 5) Possible issues Jessie h
//        as identified: 1) response listener keeps yielding, so never gets its turn. 2) the Network Securi
//        ty Config settings are not set, which could be causing a problem. 3) Something within the JsonReq
//        uest Singleton class is not working with this method. 4) Use of context is incorrect. 5) Some oth
//        er unidentified error."
/**
 * Class to execute an asychronous task to send a json object request
 * Created by Jessie on 2/23/2018.
 */
//public class ExecuteJsonRequest{
//
//
//    public synchronized String execute(String url){
//        JsonRequest.jsonObjectRequest(url);
//        try{
//            wait();
//        }catch (InterruptedException e){
//            Log.d("Error", e.toString());
//        }
//        JSONObject js = JsonRequest.getObj();
//        return js.toString();
//    }
//}
//public class ExecuteJsonRequest extends AsyncTask<String, Void, String> {
//    /**
//     * Method to make the json object request
//     * @param str the url in the 0 spot
//     * @return Todo figure out what to send back
//     */
//    protected String doInBackground(String... str) {
//        Log.d("got to", "6");
//        //String url = str[0];
//        String url = "http://proj-309-ss-4.cs.iastate.edu:9002/ben/users/1"; //TODO change back?
//        Log.d("got to", "7");
//        JsonRequest.jsonObjectRequest(url);
//        Log.d("Thread will", "sleep now");
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        Log.d("Bye", "bye");
//        return "";
//    }
//}
