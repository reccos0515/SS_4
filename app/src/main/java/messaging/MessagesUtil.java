package messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.LinearGradient;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Singleton;

/**
 * Created by Maggie on 4/16/2018.
 * Methods for making sending/receiving messages easier
 */

public class MessagesUtil {

    /**
     * Formats a message as a JSONObject so it can be posted using Volley
     * @return the formatted JSONObject
     */
    public static JSONObject prepareSentMessage(String message, String time){
        JSONObject js =  new JSONObject();
        try {
            js.put("message", message);
            js.put("time", time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js;
    }


    /**
     * Method to be called to make a volley request to the server when the message is being sent.
     * Signature is void sendMessage(int id, JSONObject js, final Context context) where id is the
     * id of the logged in user, js includes the messages that will be deleted, and context is used to
     * add the request to the queue
     * @param idTo of logged in user
     * @param js messages that will be deleted
     * @param context of activity this is being called from
     */
    public static void sendMessage(int idFrom, int idTo, JSONObject js, final Context context){
        String url =  "http://proj-309-ss-4.cs.iastate.edu:9001/ben/messages/to/" + idTo + "/from/" + idFrom;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("JsonRequest", "sendMessage response from server: " + response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
        return;
    }

    public static void getConversation(final int idFrom, int idTo, final Context context){
        String url =  "http://proj-309-ss-4.cs.iastate.edu:9001/ben/messages/to/" + idTo + "/from/" + idFrom;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d("JsonRequest", "getConversation response from server: " + response.toString());
                Boolean success = false;
                try {
                    success = response.getBoolean("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (success){ //if server said request was successful
                    final SharedPreferences preferences = context.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE);
                    final SharedPreferences.Editor editor = preferences.edit();
                    JSONArray message = new JSONArray();
                    try {
                        message = response.getJSONArray("message"); //get all the messages
                        Set<String> conversation = new HashSet<>();
                        for(int i = 0; i < message.length(); i++){
                            JSONObject temp = message.getJSONObject(i);
                            String tempString = temp.toString(); //convert message JSONObject to string
                            conversation.add(tempString); //put in set
                        }
                        //Log.d("MessagesUtil", "conversation: " + conversation);
                        editor.putStringSet("MSGFROM" + idFrom, conversation); //put messages in SharedPreferences
                        editor.apply();
                        Log.d("MessagesUtil", "MSGFROM" + idFrom + ": " + preferences.getStringSet("MSGFROM" + idFrom, null));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                    String errorMessage = "";
                    try {
                        errorMessage = response.getString("message");
                        Log.d("MessagesUtil", "Error message from server for getConversation: " + errorMessage);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
        return;
    }

    public static void getConversationDelete(int idFrom, int idTo, final Context context){
        String url =  "http://proj-309-ss-4.cs.iastate.edu:9001/ben/messages/to/" + idTo + "/from/" + idFrom;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("JsonRequest", "sendMessage response from server: " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
        return;
    }


    public static MyMessage[] convertToMyMessage(Context context, int idFrom){
        SharedPreferences preferences = context.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE);
        Set<String> setConversation = preferences.getStringSet("MSGFROM" + idFrom, null);
        MyMessage[] myMessageConversation = new MyMessage[setConversation.size()];
        if(!setConversation.isEmpty()){
            List<String> listConversation = new ArrayList<>(setConversation);
            JSONObject messageJSONObject = null;
            for(int i = 0; i < setConversation.size(); i++){
                try {
                    messageJSONObject = new JSONObject(listConversation.get(i));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(messageJSONObject != null){
                    myMessageConversation[i] = new MyMessage(messageJSONObject);
                    Log.d("MessagesUtil", "myMessageConversation[" + i +"]: " + myMessageConversation[i]);
                }
                if(i == setConversation.size() -1){
                    return myMessageConversation;
                }
            }
            Log.d("MessagesUtil", "myMessageConversation: " + Arrays.toString(myMessageConversation));
        }
        return myMessageConversation;
    }

    public static JSONObject[] convertToJSONObjectArr(ArrayList<String> listConversation){
        JSONObject[] JSONObjectArr = new JSONObject[listConversation.size()];
        for(int i = 0; i < JSONObjectArr.length; i++){
            try {
                JSONObjectArr[i] = new JSONObject(listConversation.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d("MessagesUtil", "convertToJSONObjectArr: " + Arrays.toString(JSONObjectArr));
        return JSONObjectArr;
    }

    public static ArrayList<JSONObject> sortByTime(JSONObject[] JSONObjectArr){ //TODO implement so messages don't appear out of order
        ArrayList<JSONObject> sortedConversation = new ArrayList<JSONObject>();
        int j = 0;
        int timeSent = 0;
        for(int i = 0; j < JSONObjectArr.length; i++){
            Log.d("MessagesUtil", "JSONObject inside sortByTime: " + JSONObjectArr[i]);
            try {
                timeSent = JSONObjectArr[i].getInt("id"); //number for the order of a message
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(timeSent == i){
                sortedConversation.add(JSONObjectArr[i]);
                j++;
            }
        }
        Log.d("MessagesUtil", "sortByTime: " + sortedConversation.toString());
        return sortedConversation;
    }

}
