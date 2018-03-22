package util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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
import com.conectar.conectar.R;
import com.conectar.conectar.SwipeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jessie on 2/19/2018.
 * Methods for sending volley requests to the server for JSONObjects, JSONArrays, and Strings
 */

public class JsonRequest {
    static String str;
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
     * Sends a post request to a given url
     * @param js json object to send
     * @param url url of where this request should be sent
     */
    public static void postRequest(JSONObject js, String url, Context context){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,  js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Post Request Status", ("successful, response:" + response.toString()));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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

    /**
     * Sends a PUT request for a JSONObject to the server.  Often used for updating a JSONObject.
     * @param js the JSONObject to be PUT
     * @param url the url that the request will be sent to
     */
    public static void jsonObjectPutRequest(JSONObject js, String url, Context context){
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

    /**
     * Sends a GET request that waits to receive a string response from the server
     * @param url url that the request will be sent to
     */
    public static void jsonStringRequest(String url, Context context){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                saveString(response);
                Log.d("String Request Status", "Success.  Response =" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("String Request Status", "Error");
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(stringRequest);
    }

    /**
     * Sends a DELETE request to the server
     * @param url url that the request will be sent to
     */
    public static void deleteRequest(String url, Context context){ //is a string delete request
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Delete Request Status", "Success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Delete Request Status", "Error");
                error.printStackTrace();
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(stringRequest);
    }



    public static void swipeRequest(final View nView, String url, Context context){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                SwipeFragment.saveNewObject(response); //send this back to swipe fragment for saving
                boolean success; //hold the success value
                //set up the textviews
                TextView errorMessage = nView.findViewById(R.id.swipeMessage); //can print error message
                TextView firstName = nView.findViewById(R.id.swipeFirstName);
                TextView interest1 = nView.findViewById(R.id.swipeInterest1);
                TextView interest2 = nView.findViewById(R.id.swipeInterest2);
                TextView interest3 = nView.findViewById(R.id.swipeInterest3);
                TextView interest4 = nView.findViewById(R.id.swipeInterest4);
                TextView interest5 = nView.findViewById(R.id.swipeInterest5);


                //check for success
                try{
                    success = (boolean) response.get("success"); //see if it succeeded or not
                }catch (JSONException e){
                    e.printStackTrace();
                    return;
                }
                if(!success){
                    //if failed, set text error message
                    try {
                        errorMessage.setText((String) response.get("message"));
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                    //set all interests as blank
                    firstName.setText("");
                    interest1.setText("");
                    interest2.setText("");
                    interest3.setText("");
                    interest4.setText("");
                    interest5.setText("");
                }
                else{
                    errorMessage.setText(""); //if succeeded, can make the text view invisible
                    JSONObject user = new JSONObject(); //specific user
                    try{
                        JSONArray users = (JSONArray) response.get("users"); //pull the first user
                        user = (JSONObject) users.get(0); //first user
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    SwipeFragment.updateUI(user, nView); //update the UI
                    UserUtil.setUserToView(user); //save this where profile view can access it if needed
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

}
