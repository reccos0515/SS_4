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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {

	//variables
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String userName;
	
	private String bio;

	
	private Integer friendOf;
	
	private Integer friendTo;
	//ManyToMany Connecion having trouble with this
//	@ManyToMany
//	@JoinTable(name="TBL_FRIENDS",
//			joinColumns={@JoinColumn(name="friendId",referencedColumnName = "id")},
//			inverseJoinColumns={@JoinColumn(name="userId",referencedColumnName = "id")})
//	private List<User> friendsTo;
//	@ManyToMany
//	@JoinTable(name="TBL_FRIENDS",
//		joinColumns={@JoinColumn(name="friendId",referencedColumnName = "id")},
//		inverseJoinColumns={@JoinColumn(name="userId",referencedColumnName = "id")})
//	private List<User> friendsOf;
//	
//	public List<User> getFriendsTo() {
//	return friendsTo;
//}
//
//public void setFriendsTo(List<User> friendsTo) {
//	this.friendsTo = friendsTo;
//}
//
//public List<User> getFriendsOf() {
//	return friendsOf;
//}
//
//public void setFriendsOf(List<User> friendsOf) {
//	this.friendsOf = friendsOf;
//}



	public User() {
		
	}
	public User(int id,String userName, String bio) {
		super();
		this.id = id;
		this.userName = userName;
		this.bio = bio;
		this.friendTo = -1;
		this.friendOf = -1;
	}
	
	public User(int id, String userName, String bio, int friendTo, int friendOf) {
		super();
		this.id = id;
		this.userName = userName;
		this.bio = bio;
//		this.friendsTo = new ArrayList<User>();
//		this.friendsOf = new ArrayList<User>();
		this.friendTo = friendTo;
		this.friendOf = friendOf;
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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	//int frineds
	public int getFriendOf() {
		return friendOf;
	}
	public void setFriendOf(int friendOf) {
		this.friendOf = friendOf;
	}
	public int getFriendTo() {
		return friendTo;
	}
	public void setFriendTo(int friendTo) {
		this.friendTo = friendTo;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
