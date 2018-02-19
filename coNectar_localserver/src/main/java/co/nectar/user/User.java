/**
 * This class defines the user object for the connector app
 * 
 * it contains the variables
 * userName
 * bio
 * friends
 * 
 * userName is the id of the user
 * bio is the current users bio
 * friends is a list of user objects that have accepted the current user as a friend
 * 
 */


package co.nectar.user;

import java.util.ArrayList;
import java.util.List;


public class User {

	//variables
	private int id;
	private String userName;
	
	private String bio;

	private List<Integer> friendsTo;

	private List<Integer> friendsOf;
	



	public User() {
		
	}
	public User(int id,String userName, String bio) {
		super();
		this.id = id;
		this.userName = userName;
		this.bio = bio;
		this.friendsTo = new ArrayList<>();
		this.friendsOf = new ArrayList<>();;
	}
	
	public User(int id, String userName, String bio, List<Integer> friendsTo, List<Integer> friendsOf) {
		super();
		this.id = id;
		this.userName = userName;
		this.bio = bio;
		this.friendsTo = new ArrayList<>();
		this.friendsOf = new ArrayList<>();
//		this.friendsTo = friendsTo;
//		this.friendsOf = friendsOf;
	}

	
	

	//getter/setter methods
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public List<Integer> getFriendsTo() {
		return friendsTo;
	}

	public void setFriendsTo(List<Integer> friendsTo) {
		this.friendsTo = friendsTo;
	}

	public List<Integer> getFriendsOf() {
		return friendsOf;
	}

	public void setFriendsOf(List<Integer> friendsOf) {
		this.friendsOf = friendsOf;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
