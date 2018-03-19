package util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maggie on 3/7/2018.
 * Methods pertaining to a user's interests
 */

public class InterestsUtil {

    private static String url = ""; //TODO update url for base for other urls
    private static JSONArray tempArr;
    private static JSONObject userJSONObject = new JSONObject();

    /**
     * Updates the user's interests in the DB
     * @param interests an array of selected interests
     * @param context the context in which this method is used
     */
    public static void setInterests(int[] interests, String id, Context context){ //TODO change for string format
        //set appropriate array spot with interests in userJSON
        String url = ""; //TODO update url
        userJSONObject = UserUtil.getUser(url, context);
        try {
            userJSONObject.put("interests", interests);
        } catch (JSONException e) {
            Log.d("JSONObject Put Status", "Interests put failed");
            e.printStackTrace();
        }
        UserUtil.putUser(url, userJSONObject, context);
    }

    /**
     * Adds a new interests to a user's list of interests if the list isn't full
     * @param newInterest the value for the new interest to be added
     * @param context the context in which this method is used
     */
    public static void addInterest(int newInterest, Context context){ //TODO change for string format
        url = ""; //TODO update url
        userJSONObject = UserUtil.getUser(url, context);

        try {
            tempArr = userJSONObject.getJSONArray("interests"); //grabs the array of interests from user object
        } catch (JSONException e) {
            e.printStackTrace();
        }

        boolean interestSet = false;
        int temp = 0;
        int[] interests = new int[tempArr.length()]; //make interests correct size

        for(int i = 0 ; i < interests.length; i++){
            try {
                temp = tempArr.getInt(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(temp == newInterest){
                //interest already exists in list
                //TODO figure out how to toast that interests already exists to user
                interestSet = true;
            }
            else if(temp == 0 && !interestSet){ //open spot in interests array for new interest found
                interests[i] = newInterest;
                interestSet = true;
            }
            else{
                interests[i] = temp;
            }
        }
        if(!interestSet){
            //TODO figure out how to toast or something to user
            Log.d("Interest Status", "Interests full, cannot be added");
        }

        try {
            userJSONObject.put("interests", interests); //update the jsonobject for the user with new interests
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //update the JSONObject in the DB
        UserUtil.putUser(url, userJSONObject, context);
    }

    /**
     * Deletes an interest from the interest list.  Eventually, the list is passed
     * to setInterests to be added to JSONObject
     * @param badInterest interest to be deleted
     * @param context context in which this method is used
     */
    public static void deleteInterest(int badInterest, Context context){ //TODO change for strings
        String url = ""; //TODO update url
        boolean isDeleted = false; //used to relay that the interest wasn't in the list in the first place

        userJSONObject = UserUtil.getUser(url, context);

        try {
            tempArr = userJSONObject.getJSONArray("interests"); //grabs the array of interests from user object
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int[] interests = new int[tempArr.length()]; //array of correct size
        int temp = 0; //used to minimize number of times we need try/catch blocks

        for(int i = 0; i < interests.length; i++){
            try {
                temp = tempArr.getInt(i); //grab interest from array from DB
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(temp == badInterest && !isDeleted){ //if interest is found
                interests[i] = 0; //remove interest
                //TODO adjust as needed to show no interest
                isDeleted = true;
            }
            else {
                interests[i] = temp; //if it wasn't the interest to be deleted, keep it
            }
        }
        if(!isDeleted){ //interest wasn't in list and therefore couldn't be deleted
            //TODO figure out how to toast or something to user
            Log.d("Interest Status", "Interest not in list, cannot be deleted");
        }

        try {
            userJSONObject.put("interests", interests); //update JSONObject for user
        } catch (JSONException e) {
            Log.d("JSONObject Put Status", "Bio put failed");
            e.printStackTrace();
        }
        //update the JSONObject in the DB
        UserUtil.putUser(url, userJSONObject, context);
    }

    /**
     * Deletes all of a user's interests in the DB
     * @param context context in which this method is used
     * @param id id number for the user who will have their interests deleted
     */
    public static void deleteAllInterests(Context context, int id){
        int[] tempArr = {0,0,0,0,0};
        url = ""; //TODO update url for grabbing a specific user's information, should be same for putting I believe
        userJSONObject = UserUtil.getUser(url, context);

        try {
            userJSONObject.put("interests", tempArr); //set JSONObject with deleted interests
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //update the DB to reflect deleted interests
        UserUtil.putUser(url, userJSONObject, context);
    }

    public static String getInterest(String i){
        if(i.equals("01")){
            return "Snowboarding";
        }
        else if(i.equals("02")){
            return "Movies";
        }
        else if(i.equals("03")){
            return "Hammocking";
        }
        else if(i.equals("04")){
            return "Volunteering";
        }
        else if(i.equals("05")){
            return "Video Games";
        }
        else if(i.equals("06")){
            return "Board Games";
        }
        else if(i.equals("07")){
            return "Shopping";
        }
        else if(i.equals("08")){
            return "Bowling";
        }
        else if(i.equals("09")){
            return "Ice Skating";
        }
        else if(i.equals("10")){
            return "Roller Blading";
        }
        else if(i.equals("11")){
            return "Laser Tag";
        }
        else if(i.equals("12")){
            return "Painting pottery";
        }
        else if(i.equals("13")){
            return "Getting lunch";
        }
        else if(i.equals("14")){
            return "Getting dinner";
        }
        else if(i.equals("15")){
            return "Getting breakfast";
        }
        else if(i.equals("16")){
            return "Getting ice cream";
        }
        else if(i.equals("17")){
            return "Going to concerts";
        }
        else if(i.equals("18")){
            return "Going to the park";
        }
        else if(i.equals("19")){
            return "Hiking";
        }
        else if(i.equals("20")){
            return "Biking";
        }
        else if(i.equals("21")){
            return "Skateboarding";
        }
        else if(i.equals("22")){
            return "Going to museums";
        }
        else if(i.equals("23")){
            return "Jogging";
        }
        else if(i.equals("24")){
            return "Swing dancing";
        }
        else if(i.equals("25")){
            return "Square dancing";
        }
        else if(i.equals("26")){
            return "Photography";
        }
        else if(i.equals("27")){
            return "Working out";
        }
        else if(i.equals("28")){
            return "Football";
        }
        else if(i.equals("29")){
            return "Soccer";
        }
        else if(i.equals("30")){
            return "Volleyball";
        }
        else if(i.equals("31")){
            return "Tennis";
        }
        else if(i.equals("32")){
            return "Baseball";
        }
        else if(i.equals("33")){
            return "Swimming";
        }
        else if(i.equals("34")){
            return "Go to a spa";
        }
        else if(i.equals("35")){
            return "Chess";
        }
        else if(i.equals("36")){
            return "Basketball";
        }
        else if(i.equals("37")){
            return "Hockey";
        }
        else if(i.equals("38")){
            return "Skydiving";
        }
        else if(i.equals("39")){
            return "Surfing";
        }
        else if(i.equals("40")){
            return "Snorkeling";
        }
        else if(i.equals("41")){
            return "Rock climbing";
        }
        else if(i.equals("42")){
            return "Pool";
        }
        else if(i.equals("43")){
            return "Playing cards";
        }
        else if(i.equals("44")){
            return "Fishing";
        }
        else if(i.equals("45")){
            return "Golf";
        }
        else if(i.equals("46")){
            return "Camping";
        }
        else if(i.equals("47")){
            return "Running a triathlon";
        }
        else if(i.equals("48")){
            return "Playing music";
        }
        else if(i.equals("49")){
            return "Amusement parks";
        }
        else if(i.equals("50")){
            return "Boating";
        }
        else if(i.equals("51")){
            return "Kayaking";
        }
        else if(i.equals("52")){
            return "Cooking";
        }
        else{
            return null;
        }
    }

}
