package co.nectar.message;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.nectar.user.User;
/**
 * Conversation object that relates messages between users
 * @author Ben
 *
 */
@Entity
@Table(name="conversation")
public class Conversation {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer conversationId;
	
	@OneToOne
	private User userTo;
	@OneToOne
	private User userFrom;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Message> messages;
	
	/**
	 * default constructor
	 */
	public Conversation() {
		super();
	}
	
	/**
	 * constuctor to create intial object
	 * @param userTo user recieving messages
	 * @param userFrom user sending messages
	 * @param messages list of messages between users
	 */
	public Conversation(User userTo, User userFrom, List<Message> messages) {
		super();
		this.userTo = userTo;
		this.userFrom = userFrom;
		this.messages = messages;
	}
	
	public Integer getConversationId() {
		return conversationId;
	}

	public void setConversationId(Integer conversationId) {
		this.conversationId = conversationId;
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

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	
}
