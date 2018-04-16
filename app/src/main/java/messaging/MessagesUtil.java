package messaging;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 4/16/2018.
 * Methods for making sending/receiving messages easier
 */

public class MessagesUtil {

    /**
     * Formats a message as a JSONObject so it can be posted using Volley
     * @return the formatted JSONObject
     */
    public JSONObject prepareSentMessage(int id, String message, String time, JSONObject userFrom, JSONObject userTo){
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
    public JSONObject prepareUserJSONObject(String bio, String interests, String userName, int id, int status){
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

        return js;
    }

}
