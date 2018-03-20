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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	//variables
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//private Integer numInterests = 6;
	private Integer id;
	private Integer status;
	private String userName;
	private String interests;
	//private Integer[numInterests] interests;
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
	
	
	//ManyToMany Connecion having trouble with this
	@ManyToMany
	@JsonIgnore
	@JoinTable(name="TBL_DISCOVERED",
			joinColumns={@JoinColumn(name="userId",referencedColumnName = "id")},
			inverseJoinColumns={@JoinColumn(name="discoveredId",referencedColumnName = "id")})
	private List<User> beenDiscovered;
	
	
	



	public User() {
		super();
		this.status = 0;
	}
	public User(int id,String userName, String bio, String interests) {
		super();
		this.id = id;
		this.userName = userName;
		this.bio = bio;
		this.interests = interests;
		this.sentRequestTo = new ArrayList<User>();
		this.recievedRequestFrom = new ArrayList<User>();
		this.beenDiscovered = new ArrayList<User>();
		this.status = 0;
	}

	
	public User(Integer id, String userName, String bio, List<User> sentRequestTo, List<User> recievedRequestFrom,String interests) {
		super();
		this.id = id;
		this.userName = userName;
		this.bio = bio;
		this.interests = interests;
		this.sentRequestTo = sentRequestTo;
		this.recievedRequestFrom = recievedRequestFrom;
		this.beenDiscovered = new ArrayList<User>();
		this.status = 0;
//		if(status == null)
//			this.status = 0;
//		else
//			this.status = status;
	}

	
	
	
	//getter/setter methods
	public Integer getId() {
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	//discovery
	public List<User> getBeenDiscovered() {
		return beenDiscovered;
	}
	public void setBeenDiscovered(List<User> beenDiscovered) {
		this.beenDiscovered = beenDiscovered;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}

	/**
	 * returns whether all required fields for user are complete
	 * @param user user to be checked
	 * @return valid: boolean status if user is valid
	 */
	@JsonIgnore
	public boolean isValid() {
		boolean valid = true;
		if(this.id == null && this.userName == null)
			valid = false;
		else if (status == null || status < 0 || status > 2)
			valid = false;
		else if(this.bio == null || this.bio == "")
			valid = false;
		else if(this.userName == null || this.userName== "")
			valid = false;
		else if(this.interests == null || this.interests == "")
			valid = false;
		return valid;
	}

	
	
}
