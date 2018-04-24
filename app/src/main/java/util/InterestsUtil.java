package util;


/**
 * Created by Maggie on 3/7/2018.
 * Methods pertaining to a user's interests, such as getting the string that corresponds to the number value
 * Interests are stored in the server as two digit strings of numbers, and this can convert that into the
 * activity for the user to view
 */

public class InterestsUtil {

    /**
     * method to return the name of the activity from the id of that activity.  Each activity is assigned a two
     * char "id" where both chars are numbers.  These are what are stored in the server.  This class is used to
     * convert those chars into a full string explanation of what the interest is that can be used in the UI to
     * tell the user what this interest is.  The signature is String getInterest(String i) where the parameter
     * is the two char 'id' for the interest and the return string is the name of the interest for the user to see.
     * @param i two char numbers indicating the id of the interest
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
            return "Pottery";
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
