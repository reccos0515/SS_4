package com.conectar.conectar;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.Singleton;

/**
 * Created by Jessie on 2/19/2018.
 */

public class JsonRequest {
    String serverUrl = "http://proj-309-ss-4.cs.iastate.edu:9002/test";
    static String str;
    //String for Json Array Req to server for all users "http://proj-309-ss-4.cs.iastate.edu:9002/ben/users"
    //String for Json Array Req to server to see a certain user's friends "http://proj-309-ss-4.cs.iastate.edu:9002/ben/users/<useridnumber>/friends"

    /**
     * Method to save the string taken from the response listener in a global variable that can be accessed elsewhere in the program
     * This should be called within the onResponse listener, after the Array has been parsed
     * @param str2
     */
    public static void saveString(String str2){
        str = str2;
        Log.d("Saved String", str);
        return;
    }

    /**
     * method to get the first user's name
     * @return name of first user
     */
   public static String getString() {
       return str;
   }

    /**
     * create a json object from a 2D array
     * @param obj 2D array of Strings, where obj[0] is the key and obj[1] is the value for each field. Will always be size obj[2][x] where x is the number of fields
     * @return the jsonobject created
     */
    public static JSONObject createJsonObject(String[][] obj){
        JSONObject js = new JSONObject();
        try {
            for(int i = 0; i < obj[0].length; i++){
                js.put(obj[0][i], obj[1][i]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js;
    }

    /**
     * Sends a post request to a given url
     * @param js json object to send
     * @param context context of the current system (getContext(), this, etc)
     * @param url url of where this request should be sent
     */
    public static void postRequest(JSONObject js, Context context, String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,  js, //may need typecasting to string on the null?
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                                /*
                                try { //will always give exception, is why need try catch
                                    jsonTestMsg.setText(response.getString("userName")); //not sure if case sensitive or not on the string input
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                */
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //fill here
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
    }

    /**
     *
     * @param url
     * @return
     */
    public void jsonArrayRequest(String url, Context context){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,  //may need typecasting to string on the null?
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            JSONObject user = response.getJSONObject(0); //get first JsonObject, this is the first user
                            String firstUser = user.getString("userName"); //get the userName from the first user
                            saveString(firstUser); //save this in a global variable

                            Log.d("First User", firstUser);
                            // Loop through the array elements
//                                    for(int i=0;i<response.length();i++){
//                                        // Get current json object
//                                        JSONObject user = response.getJSONObject(i);
//
//                                        // Get the current student (json object) data
//                                        //String userName = user.getString("userName");
//                                        //String bio = user.getString("bio");
//                                        if(i == 0){
//                                            String firstUserName = user.getString("userName");
//                                            jsonTestMsg.setText(firstUserName);
//                                            Log.d("First Username", firstUserName);
//                                        }
//                                    }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //implement error response
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonArrayRequest); //add json to queue
    }

    /**
     *
     */
    public static void jsonObjectRequest(String url, Context context){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,  null, //may need typecasting to string on the null?
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try { //will always give exception, is why need try catch
                            saveString(response.getString("userName")); //not sure if case sensitive or not on the string input
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
}
}
