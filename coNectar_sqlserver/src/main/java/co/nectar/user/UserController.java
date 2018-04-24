package co.nectar.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import co.nectar.HtmlResponce.HtmlError;
import co.nectar.HtmlResponce.HtmlResponce;

@CrossOrigin
@RestController
@RequestMapping(path = "/ben")
public class UserController {

	@Autowired
	UserService userService;

//	// test add
//	@RequestMapping("/test")
//	public HtmlMessage getTest() {
//		User test = new User(0, "test", "hi","");
//		User test2 = new User(0, "blah", "hello","");
//		User test3 = new User(0, "Maggie", "I am Maggie","");
//		userService.addUser(test);
//		userService.addUser(test2);
//		userService.addUser(test3);
//		return userService.getAllUsers();
//	}

	

	/**
	 * Returns all users
	 * Request Method: Get
	 * URL: /users
	 * @return Calls function in userService to return.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/users")
	public HtmlResponce getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * Updates a user from the body of the request
	 * Request Method: Post
	 * URL: /users
	 * @param user
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/users")
	public HtmlResponce updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	// get a user
	/**
	 * @param userId
	 * @return
	 */
	
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
	public HtmlResponce getUser(@PathVariable int userId) {
		return userService.getUserById(userId);
	}

	
	
	@RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/request_friend/{friendId}")
	public HtmlResponce addFriend(@PathVariable int userId, @PathVariable int friendId) {
		return userService.requestFriendById(userId, friendId);
	}

	// Force add a friend. Maybe delete this later?
	@RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/friends/{friendId}/force")
	public HtmlResponce addFriendWeird(@PathVariable int userId, @PathVariable int friendId) {
		HtmlResponce msg1 = userService.requestFriendById(userId, friendId);
		HtmlResponce msg2 = userService.requestFriendById(friendId, userId);
		
		boolean success = true;
		String error = "";
		
		if(!msg1.isSuccess() && !msg2.isSuccess()) {
			success = false;
			error = "error adding both users";
		}else if(!msg1.isSuccess()) {
			success = false;
			error = "error userId adding friendId: " + ((HtmlError) msg1).getMessage();
		}else if(!msg2.isSuccess()) {
			success = false;
			error = "error frindId adding userId: " + ((HtmlError) msg2).getMessage(); 
		}
		return new HtmlError(success, error);
	}

	// get friends to
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/sentrequestto")
	public HtmlResponce getFriendsTo(@PathVariable int userId) {
		return userService.getSentRequestTo(userId);
	}

	// get friends of
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/recievedrequestfrom")
	public HtmlResponce getFriendsOf(@PathVariable int userId) {
		return userService.getRecievedRequestFrom(userId);
	}

	// remove friend
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/friends/{friendId}")
	public HtmlResponce deleteFriend(@PathVariable int userId, @PathVariable int friendId) {
		return userService.removeFriendById(userId, friendId);
	}

	// Force Remove Friend. Maybe delete this later.
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/friends/{friendId}/force")
	public HtmlResponce deleteFriendWeird(@PathVariable int userId, @PathVariable int friendId) {
		HtmlResponce msg1 = userService.removeFriendById(userId, friendId);
		HtmlResponce msg2 = userService.removeFriendById(friendId, userId);
		
		boolean success = true;
		String error = "";
		
		if(!msg1.isSuccess() && !msg2.isSuccess()) {
			success = false;
			error = "error adding both users";
		}else if(!msg1.isSuccess()) {
			success = false;
			error = "error userId removing friendId: " + ((HtmlError) msg1).getMessage();
		}else if(!msg2.isSuccess()) {
			success = false;
			error = "error frindId removing userId: " + ((HtmlError) msg2).getMessage(); 
		}
		return new HtmlError(success, error);
		
	}

	// get friends
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/friends")
	public HtmlResponce getFriends(@PathVariable int userId) {
		return userService.getFriends(userId);
	}

	// get friend requests to me
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/incoming_requests")
	public HtmlResponce getRequests(@PathVariable int userId) {
		return userService.getIncomingRequests(userId);
	}

	// get pending friend request to others
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/outgoing_requests")
	public HtmlResponce getPending(@PathVariable int userId) {
		return userService.getOutgoingRequests(userId);
	}

	// get undiscovered people around me
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/discovery")
	public HtmlResponce getDiscovery(@PathVariable int userId) {
		return userService.getDiscovery(userId);
	}
	
	// get relevant people around me
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/relevant")
	public HtmlResponce getRelevant(@PathVariable int userId) {
		return userService.getRelevant(userId);
	}
	
	// edit user status
	@RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}/status/{status}")
	public HtmlResponce setStatus(@PathVariable int userId, @PathVariable int status) {
		return userService.setStatus(userId, status);
	}
}