package util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.conectar.conectar.ProfileViewFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jessie on 2/19/2018.
 * Methods for sending volley requests to the server for JSONObjects, JSONArrays, and Strings
 */

public class JsonRequest {
    static String str;
    static  volatile boolean ready;
    static Context context;
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
       // while(!ready){
            //implement something here?
       // }
        return str;
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
            Log.d("createJsonObject Status", ("successful, " + js.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js;
    }

    /**
     * Sends a post request to a given url
     * @param js json object to send
     * @param url url of where this request should be sent
     */
    public static void postRequest(JSONObject js, String url){
        ready = false;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,  js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Post Request Status", ("successful, response:" + response.toString()));
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
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
    }


//    /**
//     * Method to receive a json array
//     * @param url
//     * @return
//     */
//    public static void jsonArrayRequest(String url){
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,  //may need typecasting to string on the null?
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        Singleton.getmInstance(context).addToRequestQueue(jsonArrayRequest); //add json to queue
//    }

//    /**
//     * method to recieve a json object
//     */
//    public static void jsonObjectRequest(String url){
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,  null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
//        return;
//    }


    public static void jsonObjectPutRequest(JSONObject js, String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Object Put Status", "Successful Request");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Object Put Status", "error");
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public static void jsonStringRequest(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                saveString(response);
                Log.d("String Request Status", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(stringRequest);
    }


    public static void deleteRequest(String url){ //is a string delete request
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Delete Status", "Success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(stringRequest);
    }


}
