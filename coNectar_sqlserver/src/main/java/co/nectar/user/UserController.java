package co.nectar.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.nectar.Message.HtmlUserList;

@RestController
@RequestMapping(path = "/ben")
public class UserController {

	@Autowired
	UserService userService;

	// test add
	@RequestMapping("/test")
	public Iterable<User> getTest() {
		User test = new User(0, "test", "hi");
		User test2 = new User(0, "blah", "hello");
		User test3 = new User(0, "Maggie", "I am Maggie");
		userService.addUser(test);
		userService.addUser(test2);
		userService.addUser(test3);
		return userService.getAllUsers();
	}

	/**
	 * Returns all users
	 * Request Method: Get
	 * URL: /users
	 * @return Calls function in userService to return.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/users")
	public Iterable<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	/**
	 * Returns all users in HtmlUserList form
	 * Request Method: Get
	 * URL: /users
	 * @return Calls function in userService to return.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/users_list")
	public HtmlUserList getAllUsers_list() {
		return userService.getAllUsers_list();
	}

	/**
	 * Adds a user from the body of the request
	 * Request Method: Post
	 * URL: /users
	 * @param user
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/users")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	/**
	 * Updates a user from the body of the request
	 * Request Method: Post
	 * URL: /users
	 * @param user
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/users")
	public void updateBio(@RequestBody User user) {
		userService.updateUser(user);
	}

	/**
	 * 
	 * @param userId
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userService.deleteUserById(userId);
	}

	// get a user
	/**
	 * @param userId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
	public User getUser(@PathVariable int userId) {
		return userService.getUserById(userId);
	}

	// update bio
	// @RequestMapping(method=RequestMethod.PUT, value = "/users/{userId}")
	// public void updateBio(@PathVariable String userId) {
	// userService.updateBio(userId);
	// }
	//

	
	
	@RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/request_friend/{friendId}")
	public String addFriend(@PathVariable int userId, @PathVariable int friendId) {
		userService.requestFriendById(userId, friendId);
		return "Requested friend";
	}

	// Force add a friend. Maybe delete this later?
	@RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/friends/{friendId}/force")
	public String addFriendWeird(@PathVariable int userId, @PathVariable int friendId) {
		userService.requestFriendById(userId, friendId);
		userService.requestFriendById(friendId, userId);
		return "added friend";
	}

	// get friends to
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/sentrequestto")
	public List<User> getFriendsTo(@PathVariable int userId) {
		return userService.getSentRequestTo(userId);
	}

	// get friends of
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/recievedrequestfrom")
	public List<User> getFriendsOf(@PathVariable int userId) {
		return userService.getRecievedRequestFrom(userId);
	}

	// remove friend
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/friends/{friendId}")
	public void deleteFriend(@PathVariable int userId, @PathVariable int friendId) {
		userService.removeFriendById(userId, friendId);
	}

	// Force Remove Friend. Maybe delete this later.
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/friends/{friendId}/force")
	public void deleteFriendWeird(@PathVariable int userId, @PathVariable int friendId) {
		userService.removeFriendById(userId, friendId);
		userService.removeFriendById(friendId, userId);
	}

	// get friends
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/friends")
	public List<User> getFriends(@PathVariable int userId) {
		return userService.getFriends(userId);
	}

	// get friend requests to me
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/incoming_requests")
	public List<User> getRequests(@PathVariable int userId) {
		return userService.getIncomingRequests(userId);
	}

	// get pending friend request to others
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/outgoing_requests")
	public List<User> getPending(@PathVariable int userId) {
		return userService.getOutgoingRequests(userId);
	}

	// get undiscovered people around me
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/discovery")
	public List<User> getDiscovery(@PathVariable int userId) {
		return userService.getDiscovery(userId);
	}
	
	// edit user status
	@RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}/status/{status}")
	public void setStatus(@PathVariable int userId, @PathVariable int status) {
		return userService.setStatus(userId, status);
	}

}
