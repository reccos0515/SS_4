package util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.conectar.conectar.R;
import com.conectar.conectar.SwipeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 3/5/2018.
 * Methods to make common android/server interactions regarding users easier
 */

public class UserUtil {

    //NOTE cannot change the username or id for a user

    private static String url = "http://proj-309-ss-4.iastate.edu:9001/ben"; //base url for server
    private static JSONObject userJSONObject = new JSONObject();
    private static JSONObject profView = null; //user that can be viewed in profile view next
    private static boolean friend = false;
    private static int profPic = 0;
    private static JSONArray users; //array of users to be viewed in swipe

    public static JSONObject prepareLogin(String username, String password, Context context){
        JSONObject fullJS = new JSONObject();

        try {
            userJSONObject.put("userName", username);
            fullJS.put("user", userJSONObject);
            fullJS.put("password", password);
            Log.d("UserUtil", "JSONObject from prepareLogin " + fullJS.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fullJS;
    }


    /**
     * Changes the user's status in the DB and updates the session variables
     * @param status the new status the user wishes to switch to
     * @param context the context in which this method is used
     */
    public static void updateStatus(int status, Context context, final android.support.v4.app.FragmentManager fm){
        //Set up shared preferences, has to be done within onViewCreated otherwise it will throw all sorts of null pointer exceptions
        final SharedPreferences preferences = context.getSharedPreferences("coNECTAR", Context.MODE_PRIVATE); //grabs the sharedpreferences for our session (labeled coNECTAR)
        final SharedPreferences.Editor editor = preferences.edit(); //creates editor so we can put/get things from different keys

        url = "http://proj-309-ss-4.cs.iastate.edu:9001/ben/users";
        JSONObject js = new JSONObject();
        try {
            js.put("id", preferences.getInt("ID", 0));
            js.put("userName", preferences.getString("USERNAME", "empty"));
            js.put("bio", preferences.getString("BIO", "empty"));
            js.put("interests", preferences.getString("INTERESTS", "00000000000"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        //change status
        if(status  == 0){ //status is red
            try {
                js.put("status", 0); //sets the status in the user object
                editor.putInt("STATUS", 0);
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(status == 1){ //status is yellow
            try {
                js.put("status", 1);
                editor.putInt("STATUS", 1);
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(status == 2){ //status is green
            try {
                js.put("status", 2);
                editor.putInt("STATUS", 2);
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            Log.d("updateStatus", "wrong status input");
        }
        JsonRequest.jsonObjectPutRequest(js, url, context, fm);
        UserUtil.setUsersArray(null);
        return;
    }

    public static void postRequestNoFM(String url, Context context){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,  null,
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
    

    /**
     * method to get a user to be viewed in profile view, called from profile view
     * @return
     */
    public static JSONObject getUserToView(){
        return profView;
    }

    /**
     * method to set the user that would be viewed in Profile View
     * Called from other methods when there is a situation this user may
     * be viewed in, such as swipe or page to view own profile
     * @param user that would be viewed
     */
    public static void setUserToView(JSONObject user){
        profView = user;
        return;
    }

    /**
     * method to return the temporarily saved profile picture
     * @return int corresponding to profile picture
     */
    public static int getUserProfPic(){
        return profPic;
    }

    /**
     * method to set the temporarily saved profile picture
     * @param pic int corresponding to the profile picture
     */
    public static void setUserProfPic(int pic){
        profPic = pic;
        return;
    }

    /**
     * Method to be used to set if the user is a friend. This can be used in profile view to message vs add friend
     * @param f
     */
    public static void setUserToViewIsFriend(boolean f){
        friend = f;
        return;
    }

    /**
     * method to be used to see if the user is a friend.  This can be used in profile view to message vs add friend
     * @return
     */
    public static boolean getUserToViewIsFriend(){
        return friend;
    }

    /**
     * method to be used to save the array of users
     * @param js array of users
     */
    public static void setUsersArray(JSONArray js){
        users = js;
        return;
    }

    /**
     * method to be used to return the array of users
     * @return array of users to be viewed
     */
    public static JSONArray getUsersArray(){
        return users;
    }

    /**
     * Method to set an image to a profile picture, based on the int corresponding to that image
     * @param pic int corresponding to the image (as saved in the JSONObject and preferences)
     * @param profilePic ImageView where you want the picture to be placed
     */
    public static void updateProfilePicture(int pic, ImageView profilePic){
        if(pic == 2){
            profilePic.setImageResource(R.drawable.bat_128);
        } else if (pic == 3){
            profilePic.setImageResource(R.drawable.beaver_128);
        }else if (pic == 4){
            profilePic.setImageResource(R.drawable.beetle_128);
        }else if (pic == 5){
            profilePic.setImageResource(R.drawable.bulldog_128);
        }else if (pic == 6){
            profilePic.setImageResource(R.drawable.camel_128);
        }else if (pic == 7){
            profilePic.setImageResource(R.drawable.canary_128);
        }else if (pic == 9){
            profilePic.setImageResource(R.drawable.cat_128);
        }else if (pic == 10){
            profilePic.setImageResource(R.drawable.chameleon_128);
        }else if (pic == 11){
            profilePic.setImageResource(R.drawable.chicken_128);
        }else if (pic == 12){
            profilePic.setImageResource(R.drawable.clownfish_128);
        }else if (pic == 13){
            profilePic.setImageResource(R.drawable.cobra_128);
        }else if (pic == 14){
            profilePic.setImageResource(R.drawable.cow_128);
        }else if (pic == 15){
            profilePic.setImageResource(R.drawable.crab_128);
        }else if (pic == 16){
            profilePic.setImageResource(R.drawable.crocodile_128);
        }else if (pic == 17){
            profilePic.setImageResource(R.drawable.duck_128);
        }else if (pic == 18){
            profilePic.setImageResource(R.drawable.elephant_128);
        }else if (pic == 19){
            profilePic.setImageResource(R.drawable.fox_128);
        }else if (pic == 20){
            profilePic.setImageResource(R.drawable.frog_128);
        }else if (pic == 21){
            profilePic.setImageResource(R.drawable.giraffe_128);
        }else if (pic == 22){
            profilePic.setImageResource(R.drawable.hippopotamus_128);
        }else if (pic == 23){
            profilePic.setImageResource(R.drawable.hummingbird_128);
        }else if (pic == 24){
            profilePic.setImageResource(R.drawable.kangaroo_128);
        }else if (pic == 25){
            profilePic.setImageResource(R.drawable.lion_128);
        }else if (pic == 26){
            profilePic.setImageResource(R.drawable.llama_128);
        }else if (pic == 27){
            profilePic.setImageResource(R.drawable.macaw_128);
        }else if (pic == 28){
            profilePic.setImageResource(R.drawable.monkey_128);
        }else if (pic == 29){
            profilePic.setImageResource(R.drawable.moose_128);
        }else if (pic == 30){
            profilePic.setImageResource(R.drawable.mouse_128);
        }else if (pic == 31){
            profilePic.setImageResource(R.drawable.octopus_128);
        }else if (pic == 32){
            profilePic.setImageResource(R.drawable.ostrich_128);
        }else if (pic == 33){
            profilePic.setImageResource(R.drawable.owl_128);
        }else if (pic == 34){
            profilePic.setImageResource(R.drawable.panda_128);
        }else if (pic == 35){
            profilePic.setImageResource(R.drawable.pelican_128);
        }else if (pic == 36){
            profilePic.setImageResource(R.drawable.penguin_128);
        }else if (pic == 37){
            profilePic.setImageResource(R.drawable.pig_128);
        }else if (pic == 38){
            profilePic.setImageResource(R.drawable.rabbit_128);
        }else if (pic == 39){
            profilePic.setImageResource(R.drawable.racoon_128);
        }else if (pic == 40){
            profilePic.setImageResource(R.drawable.rhinoceros_128);
        }else if (pic == 41){
            profilePic.setImageResource(R.drawable.shark_128);
        }else if (pic == 42){
            profilePic.setImageResource(R.drawable.sheep_128);
        }else if (pic == 43){
            profilePic.setImageResource(R.drawable.siberianhusky_128);
        }else if (pic == 44){
            profilePic.setImageResource(R.drawable.sloth_128);
        }else if (pic == 45){
            profilePic.setImageResource(R.drawable.snake_128);
        }else if (pic == 46){
            profilePic.setImageResource(R.drawable.squirrel_128);
        }else if (pic == 47){
            profilePic.setImageResource(R.drawable.swan_128);
        }else if (pic == 48){
            profilePic.setImageResource(R.drawable.tiger_128);
        }else if (pic == 49){
            profilePic.setImageResource(R.drawable.toucan_128);
        }else if (pic == 50){
            profilePic.setImageResource(R.drawable.turtle_128);
        }else if (pic == 51){
            profilePic.setImageResource(R.drawable.whale_128);
        } else {
            profilePic.setImageResource(R.drawable.fatbee_128);
        }
        return;
    }


    /**
     * sends a post request to the server to send a report
     * @param url to send the post request to
     * @param context of the activity the is being called from
     * @param report JSONObject of the report to be made
     */
    public static void postReportRequest(String url, Context context, JSONObject report, final android.support.v4.app.FragmentManager fm){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, report,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Post Request Status", ("successful, response:" + response.toString()));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                SwipeFragment.newErrorPage(fm);
            }
        });
        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest); //add json to queue
    }

}
