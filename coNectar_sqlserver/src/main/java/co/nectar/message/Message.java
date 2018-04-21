package co.nectar.message;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.nectar.user.User;
/**
 * class relating message information between user
 * @author Ben
 *
 */
@Entity 
@Table(name="message")
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToOne
	private User userTo;
	
	@OneToOne
	private User userFrom;
	
	@JsonIgnore
	@ManyToMany(mappedBy="messages")
	List<Conversation> owners;
	
	
	
	private String message;
	private String time;
	
	/**
	 * default constructor
	 */
	public Message () {
		super();
	}
	
	/**
	 * Constructor to create a message
	 * @param userTo user receiving message
	 * @param userFrom user sending message
	 * @param message message text
	 * @param time time send
	 */
	public Message(User userTo, User userFrom, String message, String time) {
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


	public User getUserTo() {
		return userTo;
	}


	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}


	public User getUserFrom() {
		return userFrom;
	}


	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}


	public String getMessage() {
		return message;
	}
	


	public List<Conversation> getOwners() {
		return owners;
	}

	public void setOwners(List<Conversation> owners) {
		this.owners = owners;
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
	/**
	 * method to check if nessisary fields are completed
	 * @return boolean isValid
	 */
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
