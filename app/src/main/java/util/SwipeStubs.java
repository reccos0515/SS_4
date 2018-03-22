package util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Temporary file to create a stub for SwipeFragment testing
 * Created by jabad on 3/17/2018.
 */

public class SwipeStubs {
    public static JSONObject getFail(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("success", false);
            obj.put("message", "No users to view at this time");
        } catch (JSONException e){
            e.printStackTrace();
        }
        return obj;
    }
}
