package co.nectar.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesJava7;
import org.springframework.stereotype.Service;

import co.nectar.Message.*;;

@Service
public class UserService {

	@Autowired // Automatically connects relevant beans.
	private UserRepository userRepo; // Define a repository to use in this service.

	/**
	 * Returns a list of all users stored in the repository.
	 * 
	 * @return List of all users
	 */
	public HtmlUserList getAllUsers() {
		return new HtmlUserList(true, userRepo.findAll());
	}
		

	/**
	 * Returns a HtmlUserList that has the given userId.
	 * 
	 * Returns a HtmlError if:
	 * - no userId is given
	 * - userId is not found
	 * 
	 * @param userId
	 *            The id of the user you wish to return.
	 * @return HtmlUserList success: true, users: list of size 1 with found user
	 * 
	 * @return HtmlError success: false, message: detailed error message 
	 */
	public HtmlMessage getUserById(Integer userId) {
		ArrayList<User> users = new ArrayList<User>();
		User user = userRepo.findOne(userId);
		
		//check for errors
		if(userId == null) {
			return new HtmlError(false, "UserId not given");
		}else if(user == null) {
			return new HtmlError(false, "UserId not found");
		}
		users.add(user);
		return new HtmlUserList(true, users);
	}

	/**
	 * Returns a HtmlUserList that has the given username.
	 * 
	 * Returns a HtmlError if:
	 * - no username is given
	 * - username is not found
	 * 
	 * @param username
	 *            The username of the user you wish to return.
	 * @return HtmlUserList success: true, users: list of size 1 with found user
	 * 
	 * @return HtmlError success: false, message: detailed error message 
	 */
	public HtmlMessage getUserByUserName(String username) {
		ArrayList<User> users = new ArrayList<User>();
		User user = userRepo.findByUserName(username);
		
		//check for errors
		if(username == null) {
			return new HtmlError(false, "Username not given");
		}else if(user == null) {
			return new HtmlError(false, "Username not found");
		}
		users.add(user);
		return new HtmlUserList(true, users);
	}
	/**
	 * Returns a HtmlUserList that has the given user object.
	 * 
	 * Returns a HtmlError if:
	 * - no username and no userId is given
	 * - username or userId is not found
	 * - user object is empty
	 * 
	 * @param user
	 *            User object containing username or userId of user you wish to return
	 * @return HtmlUserList success: true, users: list of size 1 with found user
	 * 
	 * @return HtmlError success: false, message: detailed error message 
	 */
	public HtmlMessage getUserByObject(User user) {
		ArrayList<User> users = new ArrayList<User>();
		
		if(user.getUserName() != null) {
			user = userRepo.findByUserName(user.getUserName());
		}else if(user.getId() != null) {
			user = userRepo.findOne(user.getId());
		}else {
			return new HtmlError(false,"neither username or userId not given");
		}
		
		if(user == null) {
			return new HtmlError(false, "User not found");
		}
		users.add(user);
		return new HtmlUserList(true, users);
	}

	/**
	 * Returns a HtmlMessage of add success.
	 * 
	 * Returns a HtmlError if:
	 * - no user is given
	 * - user is not valid
	 * 		-see user isValid()
	 * 
	 * @param user
	 *            The user of the user you wish to add.
	 * @return HtmlUserList success: true, users: list of size 1 with found user
	 * 
	 * @return HtmlError success: false, message: detailed error message 
	 */
	public HtmlMessage addUser(User user) {
		boolean success = true;
		String error = "";
		ArrayList<User> users = new ArrayList<>();
		if (user == null) {
			success = false;
			error = "no user given";
		} else if (!user.isValid()) {
			success = false;
			error = "user is not valid";
		} else {
			users.add(userRepo.save(user));
			return new HtmlUserList(success, users);
		}
		return new HtmlError(success, error);
	}
	
	/**
	 * Updates a users status
	 * 
	 * @param userId
	 *            The id of the user whose status is updated.
	 * @param status
	 *			  The value of the users new status.
	 */
	public HtmlMessage setStatus(Integer userId, int status) {
		//error checking
		if(!userRepo.exists(userId))
			return new HtmlError(false, "userId not found");
		else if(status != 0 || status != 1 || status != 2)
			return new HtmlError(false, "incorrect status value, must be 1,2, or 3");
		
		//set status
		userRepo.findOne(userId).setStatus(status);
		return new HtmlError(true, "");

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
		// check to make sure id or userName are not null
		if(user.getId() == null) {
			if(user.getUserName() == null) {
				return false;
			}else {
				return userRepo.existsByUserName(user.getUserName());	
			}
		}else {
			return userRepo.exists(user.getId());
		}
	}

	/**
	 * Returns a HtmlMessage of delete success.
	 * 
	 * Returns a HtmlError if:
	 * - no userId is given
	 * - userId is not found
	 * 
	 * @param userId
	 *            The userId of the user you wish to delete.
	 * @return HtmlUserList success: true, users: list of size 0
	 * 
	 * @return HtmlError success: false, message: detailed error message 
	 */
	public HtmlError deleteUserById(Integer userId) {
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

		return new HtmlError(success, error);

	}

	
	/**
	 * Returns a HtmlMessage of update success.
	 * 
	 * Returns a HtmlError if:
	 * - no user is given
	 * - user is not valid
	 * 		-see user isValid()
	 * - user not found
	 * 
	 * @param user
	 *            The user of the user you wish to add.
	 * @return HtmlUserList success: true, users: list of size 1 with found user
	 * 
	 * @return HtmlError success: false, message: detailed error message 
	 */
	public HtmlError updateUser(User user) {
		boolean success = true;
		String error = "";
		if (user == null) {
			success = false;
			error = "user not given";
		} else if (!this.userExists(user)) {
			success = false;
			error = "given user not found";
		} else if (!user.isValid() || user.getUserName() == null) {
			success = false;
			error = "given user not completely filled";
		} else {
			//set id if not set
			if(user.getId() == null || user.getId() <= 0)
				user.setId(userRepo.findByUserName(user.getUserName()).getId());
			//check if username is duplicated
			User otherUser = userRepo.findByUserName(user.getUserName());
			if(otherUser != null && user.getId() != otherUser.getId()) {
				return new HtmlError(false, "new username exists already");
			}
			userRepo.save(user);
		}

		return new HtmlError(success, error);

	}


	/**
	 * Returns a HtmlMessage of sentRequestsTo list
	 * 
	 * Returns a HtmlError if:
	 * - no userId is given
	 * - userId is not found
	 * 
	 * @param userId
	 *            The userId of the user you wish to delete.
	 * @return HtmlUserList success: true, users: list of users sent a request
	 * 
	 * @return HtmlError success: false, message: detailed error message 
	 */
	public HtmlMessage getSentRequestTo(Integer userId) {
		boolean success = true;
		String error = "";
		List<User> users;

		if (userId == null) {
			success = false;
			error = "userId not specified";
		} else if (userRepo.findOne(userId) == null) {
			success = false;
			error = "given userId not found";
		} else {
			users = userRepo.findOne(userId).getSentRequestTo();
			return new HtmlUserList(success, users);
		}

		return new HtmlError(success, error);
		
	}

	
	/**
	 * Returns a HtmlMessage of recievedRequestFrom list
	 * 
	 * Returns a HtmlError if:
	 * - no userId is given
	 * - userId is not found
	 * 
	 * @param userId
	 *            The userId of the user you wish to delete.
	 * @return HtmlUserList success: true, users: list of users current user received a request from
	 * 
	 * @return HtmlError success: false, message: detailed error message 
	 */
	public HtmlMessage getRecievedRequestFrom(Integer userId) {
		boolean success = true;
		String error = "";
		List<User> users;

		if (userId == null) {
			success = false;
			error = "userId not specified";
		} else if (userRepo.findOne(userId) == null) {
			success = false;
			error = "given userId not found";
		} else {
			users = userRepo.findOne(userId).getRecievedRequestFrom();
			return new HtmlUserList(success, users);
		}

		return new HtmlError(success, error);
	}

	
	
	/**
	 * Returns a HtmlMessage of request success
	 * 
	 * Returns a HtmlError if: - no userId is given - no friendId is given - userId
	 * is not found - friendId is not found
	 * 
	 * @param userId
	 *            The userId to send a request from
	 * @param friendId
	 *            The friendId to send a request to
	 * @return HtmlUserList success: true, users: list size of 0 or HtmlError
	 *         success: false, message: detailed error message
	 */
	public HtmlMessage requestFriendById(int userId, int friendId) {
		HtmlMessage msg1 = this.getUserById(userId);
		HtmlMessage msg2 = this.getUserById(friendId);

		//check for errors
		boolean success = true;
		String error = "";
		
		if(!msg1.isSuccess() && !msg2.isSuccess()) {
			success = false;
			error = "error requesting both users" + ((HtmlError) msg1).getMessage()+((HtmlError) msg2).getMessage();
		}else if(!msg1.isSuccess()) {
			success = false;
			error = "error userId requesting friendId: " + ((HtmlError) msg1).getMessage();
		}else if(!msg2.isSuccess()) {
			success = false;
			error = "error frindId requesting userId: " + ((HtmlError) msg2).getMessage(); 
		}else {
			//get user and friend from first item in list
			User user = ((HtmlUserList) msg1).getUsers().iterator().next();
			User friend = ((HtmlUserList) msg2).getUsers().iterator().next();
			
			//add outgoing request to user
			user.getSentRequestTo().add(friend);
			//add incoming request to friend
			friend.getRecievedRequestFrom().add(user);

			//save user and friend
			userRepo.save(user);
			userRepo.save(friend);
		}
		//return error
		return new HtmlError(success, error);
	}

	/**
	 * Returns a HtmlMessage of removed friend success
	 * 
	 * Returns a HtmlError if: - no userId is given - no friendId is given - userId
	 * is not found - friendId is not found
	 * 
	 * @param userId
	 *            The userId to remove outgoing request
	 * @param friendId
	 *            The friendId to delete incoming request to
	 * @return HtmlUserList success: true, users: list size of 0 or HtmlError
	 *         success: false, message: detailed error message
	 */
	public HtmlMessage removeFriendById(int userId, int friendId) {
		HtmlMessage msg1 = this.getUserById(userId);
		HtmlMessage msg2 = this.getUserById(friendId);


		// check for errors
		boolean success = true;
		String error = "";

		if (!msg1.isSuccess() && !msg2.isSuccess()) {
			success = false;
			error = "error requesting both users" + ((HtmlError) msg1).getMessage() + ((HtmlError) msg2).getMessage();
		} else if (!msg1.isSuccess()) {
			success = false;
			error = "error userId requesting friendId: " + ((HtmlError) msg1).getMessage();
		} else if (!msg2.isSuccess()) {
			success = false;
			error = "error frindId requesting userId: " + ((HtmlError) msg2).getMessage();
		} else {
			// get user and friend from first item in list
			User user = ((HtmlUserList) msg1).getUsers().iterator().next();
			User friend = ((HtmlUserList) msg2).getUsers().iterator().next();

			// remove sent request
			user.getSentRequestTo().remove(friend);

			// removed received request
			friend.getRecievedRequestFrom().remove(user);

			// save user and friend
			userRepo.save(user);
			userRepo.save(friend);
		}
		// return error
		return new HtmlError(success, error);
	}

	
	/**
	 * Returns a HtmlMessage of list of friends
	 * 
	 * Returns a HtmlError if:
	 * - no userId is given
	 * - userId is not found
	 * 
	 * friends are in sentRequestTo and recievedRequestsFrom
	 * 
	 * @param userId
	 *            The userId of the user you wish to friends list.
	 * @return HtmlUserList success: true, users: list of users current friends
	 * 
	 * @return HtmlError success: false, message: detailed error message 
	 */
	public HtmlMessage getFriends(Integer userId) {
		boolean success = true;
		String error = "";
		List<User> friends = new ArrayList<User>();
		HtmlMessage msg = this.getUserById(userId);
		
		//check user 
		if (!msg.isSuccess()) {
			success = false;
			error = "error userId: " + ((HtmlError) msg).getMessage();
		} else {
			//get user
			User user = ((HtmlUserList) msg).getUsers().iterator().next();
			
			// get to and from friend lists
			List<User> to = user.getSentRequestTo();
			List<User> from = user.getRecievedRequestFrom();

			// friends are in both friendTo and friendFrom
			for (User friend : to) {
				if (from.contains(friend))
					friends.add(friend);
			}
			
			return new HtmlUserList(success, friends);
		}

		return new HtmlError(success, error);
	}

	/**
	 * Returns a HtmlMessage of list of outgoing friends
	 * 
	 * Returns a HtmlError if:
	 * - no userId is given
	 * - userId is not found
	 * 
	 * outgoing requests are in sentRequestTo but not recievedRequestsFrom
	 * 
	 * @param userId
	 *            The userId of the user you wish to friends list.
	 * @return HtmlUserList success: true, users: list of users current friends
	 * 
	 * @return HtmlError success: false, message: detailed error message 
	 */
	public HtmlMessage getOutgoingRequests(Integer userId) {
		
		boolean success = true;
		String error = "";
		List<User> requests = new ArrayList<User>();
		HtmlMessage msg = this.getUserById(userId);
		
		//check user 
		if (!msg.isSuccess()) {
			success = false;
			error = "error userId: " + ((HtmlError) msg).getMessage();
		} else {
			//get user
			User user = ((HtmlUserList) msg).getUsers().iterator().next();
			
			// get to and from friend lists
			List<User> to = user.getSentRequestTo();
			List<User> from = user.getRecievedRequestFrom();

			// friends are in sentReuquestTo but not RecievedReuqestFrom
			for (User friend : to) {
				if (!from.contains(friend))
					requests.add(friend);// add friend is not in from
			}
			
			return new HtmlUserList(success, requests);
		}

		return new HtmlError(success, error);
	}

	/**
	 * Returns a HtmlMessage of list of outgoing friends
	 * 
	 * Returns a HtmlError if:
	 * - no userId is given
	 * - userId is not found
	 * 
	 * incoming requests are in not sentRequestTo but are in recievedRequestsFrom
	 * 
	 * @param userId
	 *            The userId of the user you wish to friends list.
	 * @return HtmlUserList success: true, users: list of users current friends
	 * 
	 * @return HtmlError success: false, message: detailed error message 
	 */
	public HtmlMessage getIncomingRequests(Integer userId) {
		
		boolean success = true;
		String error = "";
		List<User> requests = new ArrayList<User>();
		HtmlMessage msg = this.getUserById(userId);
		
		//check user 
		if (!msg.isSuccess()) {
			success = false;
			error = "error userId: " + ((HtmlError) msg).getMessage();
		} else {
			//get user
			User user = ((HtmlUserList) msg).getUsers().iterator().next();
			
			// get to and from friend lists
			List<User> to = user.getSentRequestTo();
			List<User> from = user.getRecievedRequestFrom();

			//incoming requests are in not sentRequestTo but are in recievedRequestsFrom
			for (User friend : from) {
				if (!to.contains(friend))
					requests.add(friend);// add friend is not in to yet
			}
			return new HtmlUserList(success, requests);
		}

		return new HtmlError(success, error);
	}

	// a discovery get all users that the current user had not friended or requested
	/**
	 * Returns a HtmlMessage of list of outgoing friends
	 * 
	 * Returns a HtmlError if:
	 * - no userId is given
	 * - userId is not found
	 * 
	 * discovery requests are in not sentRequestTo but are in recievedRequestsFrom
	 * 
	 * @param userId
	 *            The userId of the user you wish to friends list.
	 * @return HtmlUserList success: true, users: list of users current friends
	 * 
	 * @return HtmlError success: false, message: detailed error message 
	 */
	public HtmlMessage getDiscovery(Integer userId) {
//		List<User> discovery = new ArrayList<User>();
//		User user = this.getUserById(userId);
//
//		// get all users and current user's requests
//		List<User> users = (List<User>) this.getAllUsers();
//		List<User> requests = this.getSentRequestTo(userId);
//
//		for (User user_ele : users) {
//			if (!requests.contains(user_ele) && !user_ele.equals(user))
//				discovery.add(user_ele);// add if in both to and from lists
//		}
//		return discovery;
		boolean success = true;
		String error = "";
		List<User> discovery = new ArrayList<User>();
		HtmlMessage msg = this.getUserById(userId);
		
		//check user 
		if (!msg.isSuccess()) {
			success = false;
			error = "error userId: " + ((HtmlError) msg).getMessage();
		} else {
			//get user
			User user = ((HtmlUserList) msg).getUsers().iterator().next();
			
			//get all users
			List<User> users = (List<User>) ((HtmlUserList) this.getAllUsers()).getUsers();
			// get all users sent a requests to from getSentRequestTo
			List<User> to = user.getSentRequestTo();

			//incoming requests are in not sentRequestTo but are in recievedRequestsFrom
			for (User user_ele : users) {
				if (!to.contains(user_ele) && !user_ele.equals(user))
					discovery.add(user_ele);// add if in both to and from lists
			}
			return new HtmlUserList(success, discovery);

		}

		return new HtmlError(success, error);
	}
	
	//sending ten good people to the client
	public HtmlMessage getRelevant(Integer userId) {
		boolean success = true;
		String error = "";
		List<User> relevant = new ArrayList<User>();
		HtmlMessage msg = this.getUserById(userId);
		
		//check user 
		if (!msg.isSuccess()) {
			success = false;
			error = "error userId: " + ((HtmlError) msg).getMessage();
		} else {
			//get user
			User user = ((HtmlUserList) msg).getUsers().iterator().next();
			
			//get all users
			List<User> users = (List<User>) ((HtmlUserList) this.getAllUsers()).getUsers();
			
			//getting all outgoing requests
			List<User> to = user.getSentRequestTo(); //list of users that I sent a request to
			//getting all users that have been discovered
			List<User> been = user.getBeenDiscovered();

			
			for (User user_ele : users) {
				if (!to.contains(user_ele) && !user_ele.equals(user) && !been.contains(user_ele))
					relevant.add(user_ele);// add if i have not added the user, and the user is not me.
			}
			//now i have a list of everyone that i dont know and have not discovered before (discover). 
			List<User> send = makeSend(user, relevant);
			return new HtmlUserList(success, send);
		}

		return new HtmlError(success, error);
	}
	
	//we are going to need some list to keep track of who has been discovered (nevermind).
	//this only works if there is ten users in the discover
	public List<User> makeSend(User user, List<User> relevant){
		//right now im just sending back the first ten, this will be improved soon.
		List<User> send = new ArrayList<User>();
		List<User> been = user.getBeenDiscovered();
		for(int i = 0; i<10; i++){
			send.add(relevant.get(i));
			been.add(relevant.get(i));
		}
		user.setBeenDiscovered(been); //changes the users been discovered to include that which was just found.
		userRepo.save(user);
		
		return send;
		
	}	

}