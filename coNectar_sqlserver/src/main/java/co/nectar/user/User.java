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

	@ManyToMany
	@JoinTable(name="TBL_FRIENDS",
			joinColumns={@JoinColumn(name="userId")},
			inverseJoinColumns={@JoinColumn(name="friendId")})
	private List<User> friendsTo;
	@ManyToMany
	@JoinTable(name="TBL_FRIENDS",
		joinColumns={@JoinColumn(name="friendId")},
		inverseJoinColumns={@JoinColumn(name="userId")})
	private List<User> friendsOf;
	



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
	
	public User(int id, String userName, String bio, List<User> friendsTo, List<User> friendsOf) {
		super();
		this.id = id;
		this.userName = userName;
		this.bio = bio;
//		this.friendsTo = new ArrayList<User>();
//		this.friendsOf = new ArrayList<User>();
		this.friendsTo = friendsTo;
		this.friendsOf = friendsOf;
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
	public List<User> getFriendsTo() {
		return friendsTo;
	}

	public void setFriendsTo(List<User> friendsTo) {
		this.friendsTo = friendsTo;
	}

	public List<User> getFriendsOf() {
		return friendsOf;
	}

	public void setFriendsOf(List<User> friendsOf) {
		this.friendsOf = friendsOf;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
