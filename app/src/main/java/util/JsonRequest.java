package util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.Singleton;

/**
 * Created by Jessie on 2/19/2018.
 */

public class JsonRequest {
    static String str;
    static JSONObject jsObj;
    static JSONArray jsArr;
    static boolean ready = false;
    //String for Json Array Req to server for all users "http://proj-309-ss-4.cs.iastate.edu:9002/ben/users"
    //String for Adding 10 users to the DB "http://projec-309-ss-4.cs.iastate.edu:9002/ben/test"
    //String for Json Array Req to server to see a certain user's friends "http://proj-309-ss-4.cs.iastate.edu:9002/ben/users/<useridnumber>/friends"

    /**
     * Method to save the string taken from the response listener in a global variable that can be accessed elsewhere in the program
     * This should be called within the onResponse listener, after the Array has been parsed
     * @param str2
     */
    public static void saveString(String str2){
        str = str2;
        ready = true;
        Log.d("Saved String", str);
        return;
    }

    /**
     * method to get the first user's name
     * @return name of first user
     */
    public static String getString() {
        while(!ready){
            //implement something here?
        }
        return str;
    }

    /**
     * method to save the most recent json object received
     * @param obj most recent json object received
     */
    public static void saveObj(JSONObject obj){
        Log.d("it made it to save", "that's good");
        jsObj = obj;
        ready = true;
        Log.d("it even saved it", obj.toString());
        return;
    }

    /**
     * method to get the most recent json object received
     * @return most recent json object received
     */
    public static JSONObject getObj(){
        Log.d("Maybe it worked? 8", ready + "");
        while(!ready){
            //implement something here
        }
        return jsObj;
    }

    /**
     * method to save the most recent json array received
     * @param arr the most recent json array recieved
     */
    public static void saveArr(JSONArray arr){
        jsArr = arr;
        ready = true;
        return;
    }

    /**
     * method to get the most recent json array received
     * @return most recent json array received
     */
    public static JSONArray getArr(){
        while(!ready){
            //implement something here?
        }
        return jsArr;
    }


    public static void clearString(){ str = "Cleared";}

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
        ready = false;
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
     * Method to receive a json array
     * @param url
     * @return
     */
    public static void jsonArrayRequest(String url, Context context){
        ready = false;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,  //may need typecasting to string on the null?
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        try{
                            if(response.length() <= 0){ //If the array is empty
                                Log.d("JSONArray Status: ","Empty List");
                                saveArr(null);
                            }
                            else { //If the array isn't empty
                                Log.d("Json array received", response.toString());
                                saveArr(response); //save this in a global variable
                            }
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                        }
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
     * method to recieve a json object
     */
    public static void jsonObjectRequest(String url, Context context){
        ready = false;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,  null, //may need typecasting to string on the null?
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Got a response", "it was cool");
//                        try { //will always give exception, is why need try catch
                            saveObj(response); //not sure if case sensitive or not on the string input
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
    }

    /*
    public static void jsonObjectDeleteRequest(String url, Context context){
        JsonObjectRequest jsonObjectRequest = new jsonObjectRequest(Request.Method.DELETE, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    onResponse(Response response){

                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(){

                    }
                });
    }

    public static void stringDeleteRequest(String url, Context context){
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(Response response){

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }
    */
}
