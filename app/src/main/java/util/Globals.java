package util;

/**
 * Created by Maggie on 3/5/2018.
 * Allows for manipulation of global variables to be used when user is logged in
 */

public class Globals {

    private static Globals instance;

    private String currentId;
    private String currentUsername;
    private String currentBio;
    private boolean isLoggedIn;

    private Globals(){}

    public void setCurrentId(String str){
        this.currentId = str;
    }

    public void setCurrentUsername(String str){
        this.currentUsername = str;
    }

    public void setCurrentBio(String str){
        this.currentBio = str;
    }

    public void setLoggedIn(Boolean bool){
        this.isLoggedIn = bool;
    }

    public String getCurrentId(){
        return currentId;
    }

    public String getCurrentUsername(){
        return currentUsername;
    }

    public String getCurrentBio(){
        return currentBio;
    }

    public boolean getLoggedIn(){
        return isLoggedIn;
    }

    public static synchronized  Globals getInstance(){
        if(instance == null){
            instance = new Globals();
        }
        return instance;
    }

}
