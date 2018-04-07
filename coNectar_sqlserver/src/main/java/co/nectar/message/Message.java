package co.nectar.message;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.nectar.HtmlResponce.HtmlUserListReponce;
import co.nectar.user.User;
import co.nectar.user.UserService;

@Entity 
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@JsonIgnore
	private Integer userTo;
	
	@JsonIgnore
	private Integer userFrom;
	
	@JsonIgnore
	@Autowired
	@Transient//dont store this variable in the db
	private UserService userService;
	
	
	private String message;
	private String time;
	
	
	public Message () {
		super();
	}
	public Message(Integer id, Integer userTo, Integer userFrom, String message, String time) {
		super();
		this.id = id;
		this.userTo = userTo;
		this.userFrom = userFrom;
		this.message = message;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * returns user based on from field
	 * @return user based on from field
	 */
	public User getUser() {
		return ((HtmlUserListReponce) userService.getUserById(userFrom)).getUsers().iterator().next();
	}
	
	/**
	 * add to from field based on given user
	 * 
	 * sets from to null if given username and id null in user
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		if(user.getUserName() == null && user.getId() == null) {
			this.userFrom = null;//keep from null if no id or username given
			return;
		}
		else if(user.getId() == null) {
			user = ((HtmlUserListReponce) userService.getUserByUserName(user.getUserName())).getUsers().iterator().next();
		}
		
		this.userFrom = user.getId();

	}
	public Integer getUserTo() {
		return userTo;
	}

	public void setUserTo(Integer to) {
		this.userTo = to;
	}

	public Integer getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(Integer from) {
		this.userFrom = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	} 
	
	@JsonIgnore
	boolean isValid() {
		if(userFrom == null)
			return false;
		else if(userTo == null)
			return false;
		else if(message == null)
			return false;
		else if(time == null)
			return false;
		return true;
	}
	
	
}
