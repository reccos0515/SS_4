package util;

/**
 * Created by Maggie on 3/23/2018.
 * Friend objects represent friends of a user.  Having a friend object helps in creating listviews
 * that have custom update-able items
 */

public class Friend {
    private String id;
    private String username;
    private String bio;
    private String interests;

    public Friend(){
        super();
    }

    public Friend(String id, String username, String bio, String interests){
        super();
        this.bio = bio;
        this.id = id;
        this.username = username;
        this.interests = interests;
    }

    /**
     * Lists all of the information regarding a Friend object
     * @return string of all Friend object attributes
     */
    public String fullFriendInfo(){
        return "Id: " + this.id + "   Username: " + this.username + "   Bio: " + this.bio + "   Interests: " + this.interests;
    }

    @Override
    public String toString(){
        return this.username;
    }

}
