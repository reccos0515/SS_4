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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {

	//variables
	@Id
	private String userName;
	private String bio;
//	@ManyToMany
//	@JoinTable(
//			name = "FRIENDS",
//			joinColumns=@JoinColumn(name="user",referencedColumnName="userName"),
//			inverseJoinColumns=@JoinColumn(name="user",referencedColumnName="userName"))
//	private List<User> friends;
//	
//	@ManyToMany
//	@JoinTable(
//			name = "REQUESTS",
//			joinColumns=@JoinColumn(name="user",referencedColumnName="userName"),
//			inverseJoinColumns=@JoinColumn(name="user",referencedColumnName="userName"))
//	private List<User> requests;
//	
//	public List<User> getRequests() {
//		return requests;
//	}
//
//	public void setRequests(List<User> requests) {
//		this.requests = requests;
//	}

	public User() {
		
	}
	public User(String userName, String bio) {
		super();
		this.userName = userName;
		this.bio = bio;
	}
	
	
//	public User(String userName, String bio, List<User> friends, List<User> requests) {
//		super();
//		this.userName = userName;
//		this.bio = bio;
//		//this.friends = friends;
//	}
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
//	public List<User> getFriends() {
//		return friends;
//	}
//	public void setFriends(List<User> friends) {
//		this.friends = friends;
//	}
//	
	
	
}
