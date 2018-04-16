package messaging;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

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
    public static JSONObject prepareSentMessage(int id, String message, String time, JSONObject userFrom, JSONObject userTo){
        JSONObject js =  new JSONObject();
        try {
            js.put("id", id);
            js.put("message", message);
            js.put("time", time);
            js.put("userFrom", userFrom);
            js.put("userTo", userTo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("MessagesUtil", "Prepared message JSONObject: "+ js.toString());
        return js;
    }

    /**
     * Formats a JSONObject of a user to be used for Volley calls
     * @param bio bio of the user
     * @param interests interests of the user
     * @param userName userName of the user
     * @param id id of the user
     * @param status status of the user
     * @return a formatted JSONObject of a user
     */
    public static JSONObject prepareUserJSONObject(String bio, String interests, String userName, int id, int status){
        JSONObject js =  new JSONObject();
        try {
            js.put("bio", bio);
            js.put("interests", interests);
            js.put("userName", userName);
            js.put("id", id);
            js.put("status", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("MessagesUtil", "Prepared user JSONObject: "+ js.toString());
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
        String url =  "http://proj-309-ss-4.cs.iastate.edu:9001/ben/messages/to/" + idTo + "/from/" + idFrom; //TODO change if need be
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


}
