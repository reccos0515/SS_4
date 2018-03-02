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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	//variables
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Integer id;
	private String userName;
	
	private String bio;

	
	
	//ManyToMany Connecion having trouble with this
	@ManyToMany
	@JsonIgnore
	@JoinTable(name="TBL_FRIENDS",
			joinColumns={@JoinColumn(name="userId",referencedColumnName = "id")},
			inverseJoinColumns={@JoinColumn(name="friendId",referencedColumnName = "id")})
	private List<User> sentRequestTo;
	
	
	@ManyToMany(mappedBy="sentRequestTo")
	@JsonIgnore
//	@JoinTable(name="TBL_FRIENDS",
//		joinColumns={@JoinColumn(name="friendId",referencedColumnName = "id")},
//		inverseJoinColumns={@JoinColumn(name="userId",referencedColumnName = "id")})
	private List<User> recievedRequestFrom;
	
	
	



	public User() {
		
	}
	public User(int id,String userName, String bio) {
		super();
		this.id = id;
		this.userName = userName;
		this.bio = bio;
		this.sentRequestTo = new ArrayList<User>();
		this.recievedRequestFrom = new ArrayList<User>();
	}
	
	public User(Integer id, String userName, String bio, List<User> sentRequestTo, List<User> recievedRequestFrom) {
		super();
		this.id = id;
		this.userName = userName;
		this.bio = bio;
		this.sentRequestTo = sentRequestTo;
		this.recievedRequestFrom = recievedRequestFrom;
	}
	
	
	
	//getter/setter methods
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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

	//friend list methods
	public List<User> getSentRequestTo() {
		return sentRequestTo;
	}
	public void setSentRequestTo(List<User> sentRequestTo) {
		this.sentRequestTo = sentRequestTo;
	}
	public List<User> getRecievedRequestFrom() {
		return recievedRequestFrom;
	}
	public void setRecievedRequestFrom(List<User> recievedRequestFrom) {
		this.recievedRequestFrom = recievedRequestFrom;
	}

	
	
//	//int friends
//	private Integer friendOf;
//	
//	private Integer friendTo;
//	//int frineds
//	public int getFriendOf() {
//		return friendOf;
//	}
//	public void setFriendOf(int friendOf) {
//		this.friendOf = friendOf;
//	}
//	public int getFriendTo() {
//		return friendTo;
//	}
//	public void setFriendTo(int friendTo) {
//		this.friendTo = friendTo;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
	
}
