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
    public static void setInterests(String interests, String id, Context context){
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
    public static void addInterest(String newInterest, int id, Context context){ //TODO test
        url = "/users/" + id; //TODO update url
        userJSONObject = UserUtil.getUser(url, context); //pull the user's information
        String interests = ""; //temp string for holding interests after grabbing from obj

        try {
            interests = userJSONObject.getString("interests"); //grabs the string of interests from user object
        } catch (JSONException e) {
            e.printStackTrace();
        }

        boolean interestSet = false;
        String[] interestsArr = new String[5]; //make an array for parsed interests
        int j = 1;
        for(int i = 0; i < 5; i ++){
            char temp1 = interests.charAt(j);
            char temp2 = interests.charAt(j + 1);
            String temp3 = "" + temp1 + temp2; //concatonate the two characters
            j += 2; //increment j by 2 instead of 1 because we grabbed two characters
            interestsArr[i] = temp3;
        }
        char tempnumInterests = interests.charAt(0); //get number of interest user currently haves
        String numInterests = "" + tempnumInterests; // change to string

        for(int i = 0 ; i < interestsArr.length; i++){
            if(interestsArr[i].equals(newInterest)){
                //interest already exists in list
                //TODO figure out how to toast that interests already exists to user
                interestSet = true;
            }
            else if(interestsArr[i].equals("00") && !interestSet){ //open spot in interests array for new interest found
                interestsArr[i] = newInterest; //put new interest in list
                interestSet = true;
                int temp = Integer.getInteger(numInterests);
                temp++;
                numInterests = Integer.toString(temp); //update the number of interests that the user has
            }
        }
        if(!interestSet){
            //TODO figure out how to toast or something to user
            Log.d("Interest Status", "Interests full, cannot be added");
        }

        interests = numInterests; //start with how many interests the user has
        for(int i = 0; i < interestsArr.length; i ++){ //convert the array back to a string
            interests += interestsArr[i];
        }

        try {
            userJSONObject.put("interests", interests); //update the jsonobject for the user with new interests
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //update the JSONObject in the DB
        UserUtil.putUser(url, userJSONObject, context);
        //TODO clear userJSONObject
    }

    /**
     * Deletes an interest from the interest list.  Eventually, the list is passed
     * to setInterests to be added to JSONObject
     * @param badInterest interest to be deleted
     * @param context context in which this method is used
     */
    public static void deleteInterest(String badInterest, String id, Context context){
        String url = "/users/" + id; //TODO update url
        boolean isDeleted = false; //used to relay that the interest wasn't in the list in the first place

        userJSONObject = UserUtil.getUser(url, context);
        String interests = "";

        try {
            interests = userJSONObject.getString("interests"); //grabs the array of interests from user object
        } catch (JSONException e) {
            e.printStackTrace();
        }

        boolean interestDeleted = false;
        String[] interestsArr = new String[5]; //make an array for parsed interests
        int j = 0;
        for(int i = 0; i < 5; i ++){
            char temp1 = interests.charAt(j);
            char temp2 = interests.charAt(j + 1);
            String temp3 = "" + temp1 + temp2; //concatonate the two characters
            j += 2; //increment j by 2 instead of 1 because we grabbed two characters
            interestsArr[i] = temp3;
        }
        char tempChar = interests.charAt(0);
        String numInterests = "" + tempChar; //grab how many interests the user has

        for(int i = 0 ; i < interestsArr.length; i++){
            if(interestsArr[i].equals(badInterest)){
                //interest exists in list, delete it
                interestsArr[i] = "00";
                interestDeleted = true;
                int temp = Integer.getInteger(numInterests);
                temp --;
                numInterests = Integer.toString(temp); //decrement the number of interests that the user has
            }
        }
        if(!interestDeleted){
            //TODO figure out how to toast or something to user
            Log.d("Interest Status", "Interest not in list, could not delete");
        }

        interests = numInterests;
        for(int i = 0; i < interestsArr.length; i ++){ //convert the array back to a string
            interests += interestsArr[i];
        }

        try {
            userJSONObject.put("interests", interests); //update JSONObject for user
        } catch (JSONException e) {
            Log.d("JSONObject Put Status", "Interests put failed");
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
        String interests = "00000000000"; //0 interests, all interest slots to 0
        url = "/users/" + id; //TODO update url for grabbing a specific user's information, should be same for putting I believe
        userJSONObject = UserUtil.getUser(url, context); //grab user

        try {
            userJSONObject.put("interests", interests); //set JSONObject with deleted interests
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //update the DB to reflect deleted interests
        UserUtil.putUser(url, userJSONObject, context);
    }

    /**
     * method to return the name of the activity from the id of that activity
     * @param i two char number indicating the id of the interest
     * @return string name of interest for user to view
     */
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
            return "Lunch";
        }
        else if(i.equals("14")){
            return "Dinner";
        }
        else if(i.equals("15")){
            return "Breakfast";
        }
        else if(i.equals("16")){
            return "Ice cream";
        }
        else if(i.equals("17")){
            return "Concerts";
        }
        else if(i.equals("18")){
            return "Parks";
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
            return "Museums";
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
            return "Spa";
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
            return "Triathlon";
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
        else if(i.equals("53")){
            return "Picnicking";
        }
        else{
            return null;
        }
    }

}
