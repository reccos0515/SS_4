package co.nectar.message;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.nectar.user.User;

@Entity 
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@JsonIgnore
	@OneToOne
	private User userTo;
	
	@OneToOne
	private User user;
	
	
	
	private String message;
	private String time;
	
	
	public Message () {
		super();
	}
	
	
	public Message(Integer id, User userTo, User user, String message, String time) {
		super();
		this.id = id;
		this.userTo = userTo;
		this.user = user;
		this.message = message;
		this.time = time;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public User getUserTo() {
		return userTo;
	}


	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
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
		if(user == null)
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
