package com.conectar.conectar;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static com.conectar.conectar.AppController.TAG;

/**
 * Created by Jessie on 2/13/2018.
 * Class to make a json request
 */

public class RequestJsonObject {
    //save a return value
    static JSONObject ret = null;
    static String strRet = null;
    static RequestQueue queue; //request queue
    //TODO implement JSONobject cancel
    /**
     * function to make a request
     * @param request JSONObj with the request
     * @param context current context (this)
     */
    public void makeRequest(JSONObject request, Context context) {
        ret = null;
        String tag_json_obj = "json_obj_req_cancel";
        String url = "http://proj-309-ss-4.cs.iastate.edu/";
        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                saveRequest(response);
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    /**
     * Used to post a new profile request
     * @param context
     * @param url
     */
    public static void postProfileRequest(final Context context, String url){
        //mPostCommentResponse.requestStarted();
        queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //mPostCommentResponse.requestCompleted();
                saveStringRequest(response);
                Log.d(TAG, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mPostCommentResponse.requestEndedWithError(error);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("userName", "jessie");
                params.put("bio", "i am jessie");
                return params;
            }



        };
        queue.add(sr);
    }


    public static void postProfileRequestJSON(final Context context, String url, final TextView textview){
        //mPostCommentResponse.requestStarted();
        queue = Volley.newRequestQueue(context);
        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //mPostCommentResponse.requestCompleted();
                saveRequest(response);
                textview.setText("Response: " + response.toString());
                //Log.d(TAG, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mPostCommentResponse.requestEndedWithError(error);
            }
        }){

        };
        queue.add(sr);
    }
//    public interface PostCommentResponseListener{
//        public void requestStarted();
//        public void requestCompleted();
//        public void requestEndedWithError(VolleyError error);
//    }


    /**
     * Function to save a request to be returned later
     * @param obj request to be saved
     */
    public static void saveRequest(JSONObject obj){
        ret = obj;
    }

    public static void saveStringRequest(String str){
        strRet = str;
    }

    /**
     * function to get the return value
     * @return the JSONobj
     * TODO decide on wait time
     */
    public JSONObject getRequests(Context context){
        if(ret == null){
            CharSequence text = "No response, try again";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return ret;
    }

    public static String getStringRequests(Context context){
        if(strRet == null){
            CharSequence text = "No response, try again";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return strRet;
    }
}
