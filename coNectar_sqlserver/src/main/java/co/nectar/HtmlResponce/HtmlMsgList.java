package co.nectar.HtmlResponce;

import co.nectar.message.Message;

/**
 * Html response object for returning a list of messages
 * @author Ben
 *
 */
public class HtmlMsgList implements HtmlResponce{
	private boolean success;
	private Iterable<Message> recieved_messages;
	
	public HtmlMsgList(boolean success, Iterable<Message> message) {
		super();
		this.success = success;
		this.recieved_messages = message;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Iterable<Message> getMessage() {
		return recieved_messages;
	}
	public void setMessage(Iterable<Message> message) {
		this.recieved_messages = message;
	}
	
	
}
