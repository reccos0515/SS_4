package co.nectar.Message;

import java.util.List;

import co.nectar.user.User;

/**
 * This class defines an object to be converted to a json object
 * 
 * success indicates if the request was filled correctly
 * users is a list of the users returned
 * 
 * @author basimon
 *
 */
public class HtmlUserList {

	private boolean success;
	private List<User> users;
	

	//constuctors
	public HtmlUserList(boolean success, List<User> users) {
		super();
		this.success = success;
		this.users = users;
	}
	
	//getters and setters
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
