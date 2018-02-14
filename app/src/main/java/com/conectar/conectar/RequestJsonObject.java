package com.conectar.conectar;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import static com.conectar.conectar.AppController.TAG;

/**
 * Created by Jessie on 2/13/2018.
 * Class to make a json request
 */

public class RequestJsonObject {
    //save a return value
    JSONObject ret = null;
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
     * Function to save a request to be returned later
     * @param obj request to be saved
     */
    public void saveRequest(JSONObject obj){
        ret = obj;
    }

    /**
     * function to get the return value
     * @return the JSONobj
     * TODO decide on wait time
     */
    public JSONObject getRequests(Context context){
        int i = 0;
        //wait until it is set, or times out
        while(ret == null && i < 1000000000){
            i++;
        }
        if(ret == null){
            CharSequence text = "No response, try again";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return ret;
    }
}
