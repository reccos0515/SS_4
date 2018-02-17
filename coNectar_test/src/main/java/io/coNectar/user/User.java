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


package io.coNectar.user;

import java.util.List;

import javax.persistence.Id;

public class User {

	//variables
	@Id
	private String userName;
	private String bio;
	private List<User> friends;
	
	public User() {
		
	}
	
	public User(String userName, String bio, List<User> friends) {
		super();
		this.userName = userName;
		this.bio = bio;
		this.friends = friends;
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
	public List<User> getFriends() {
		return friends;
	}
	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	
	
	
}
