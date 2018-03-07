package co.nectar.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesJava7;
import org.springframework.stereotype.Service;

import co.nectar.Message.HtmlMessage;
import co.nectar.Message.HtmlUserList;

@Service
public class UserService {

	@Autowired // Automatically connects relevant beans.
	private UserRepository userRepo; // Define a repository to use in this service.

	/**
	 * Returns a list of all users stored in the repository.
	 * 
	 * @return List of all users
	 */
	public Iterable<User> getAllUsers() {
		return userRepo.findAll();
	}

	/**
	 * Returns a user that has the given ID.
	 * 
	 * @param userId
	 *            The id of the user you wish to return.
	 * @return User with id = userId.
	 */
	public User getUserById(int userId) {
		return userRepo.findOne(userId);
	}

	public User getUserByUserName(String username) {
		return userRepo.findByUserName(username);
	}

	/**
	 * Adds a user object to the repository.
	 * 
	 * @param user
	 *            The user object to be added
	 */
	public User addUser(User user) {
		return userRepo.save(user);
	}

	/**
	 * returns if specified user's exists in db
	 * 
	 * ONLY userId or username needs to be filled in the given user object
	 *
	 * @param user
	 *            user to be checked
	 * @return if user is found or not
	 */
	public boolean userExists(User user) {
		// check to make sure id is not null
		return userRepo.existsByUserName(user.getUserName()) || (user.getId() != null && userRepo.exists(user.getId()));
	}

	/**
	 * deletes user denoted by userId from db
	 * 
	 * 
	 * @param userId
	 *            userid to be deleted
	 * @return html message indicating if operation succeeded
	 */
	public HtmlMessage deleteUserById(Integer userId) {
		boolean success = true;
		String error = "";

		if (userId == null) {
			success = false;
			error = "userId not specified";
		} else if (userRepo.findOne(userId) == null) {
			success = false;
			error = "given userId not found";
		} else {
			userRepo.delete(userId);
		}
		
		return new HtmlMessage(success, error);

	}

	/**
	 * updates user based on given user
	 * returns unsuccessfully if:
	 * -user id not specified
	 * -user is not stored currently in db
	 * -given user object is not filled out completely
	 * 
	 * @param user user to be updated
	 * @return HtmlMessage indicating success of operation and any error messages
	 */
	public HtmlMessage updateUser(User user) {
		boolean success = true;
		String error = "";
		if (user.getId() == null) {
			success = false;
			error = "userId not specified";
		} else if (!this.userExists(user)) {
			success = false;
			error = "given user not found";
		} else if(!user.isValid()) {
			success = false;
			error = "given user not completely filled";
		}else {
			userRepo.save(user);
		}
		
		return new HtmlMessage(success, error);

	}
	/**
	 * returns list of users current user send a request to
	 * @param userId user id of current user
	 * @return list of users user send a request to
	 */
	public List<User> getSentRequestTo(int userId) {

		return userRepo.findOne(userId).getSentRequestTo();
	}
	
	/** 
	 * returns HtmlUserList of users current user send a request to
	 * 
	 * returns unsuccessfully if:
	 * userId not in database
	 * 
	 * @param userId id of user to get outgoing request from
	 * @return HtmlUser list of users
	 */
	public HtmlUserList getSendRequestTo_list(Integer userId) {
		boolean success = true;
		String error = "";
		if (userId == null) {
			success = false;
			error = "userId not specified";
		}else {
			return new HtmlUserList(success, userRepo.findOne(userId).getSentRequestTo());
		}
		return new HtmlUserList(success, new ArrayList<User>()); //returns error and empty list
	}

	public List<User> getRecievedRequestFrom(int userId) {
		return userRepo.findOne(userId).getRecievedRequestFrom();
	}

	public void requestFriend(int userId, int friendId) {
		User user = this.getUserById(userId);
		User friend = this.getUserById(friendId);

		user.getSentRequestTo().add(friend);
		friend.getRecievedRequestFrom().add(user);

		userRepo.save(user);
		userRepo.save(friend);
	}

	public void removeFriend(int userId, int friendId) {
		User user = this.getUserById(userId);
		User friend = this.getUserById(friendId);

		// clear friends
		user.getSentRequestTo().remove(friend);
		friend.getRecievedRequestFrom().remove(user);
		userRepo.save(user);
		userRepo.save(friend);
	}

	// friends are in both friendTo and friendFrom
	public List<User> getFriends(int userId) {
		List<User> friends = new ArrayList<User>();
		User user = this.getUserById(userId);

		// get to and from friend lists
		List<User> to = user.getSentRequestTo();
		List<User> from = user.getRecievedRequestFrom();

		for (User friend : to) {
			if (from.contains(friend))
				friends.add(friend);// add if in both to and from lists
		}
		return friends;
	}

	// request are in friend to but not from
	public List<User> getOutgoingRequests(int userId) {
		List<User> requests = new ArrayList<User>();
		User user = this.getUserById(userId);

		// get to and from friend lists
		List<User> to = user.getSentRequestTo();
		List<User> from = user.getRecievedRequestFrom();

		for (User friend : to) {
			if (!from.contains(friend))
				requests.add(friend);// add friend is not in from
		}
		return requests;
	}

	// pending users are in from but not in to
	public List<User> getIncomingRequests(int userId) {
		List<User> requests = new ArrayList<User>();
		User user = this.getUserById(userId);

		// get to and from friend lists
		List<User> to = user.getSentRequestTo();
		List<User> from = user.getRecievedRequestFrom();

		for (User friend : from) {
			if (!to.contains(friend))
				requests.add(friend);// add friend is not in to yet
		}
		return requests;
	}

	// a discovery get all users that the current user had not friended or requested
	public List<User> getDiscovery(int userId) {
		List<User> discovery = new ArrayList<User>();
		User user = this.getUserById(userId);

		// get all users and current user's requests
		List<User> users = (List<User>) this.getAllUsers();
		List<User> requests = this.getSentRequestTo(userId);

		for (User user_ele : users) {
			if (!requests.contains(user_ele) && !user_ele.equals(user))
				discovery.add(user_ele);// add if in both to and from lists
		}
		return discovery;
	}

}
