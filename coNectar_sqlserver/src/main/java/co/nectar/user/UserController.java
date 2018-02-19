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

@RestController
@RequestMapping(path = "/ben")
public class UserController {

	@Autowired
	UserService userService;

	//test add
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
	* @return Calls function in userService to return.
	*/
	@RequestMapping("/users")
	public Iterable<User> getAllTopics() {
		return userService.getAllUsers();
	}
	//get a user
	@RequestMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		return userService.getUser(userId);
	}
	@GetMapping(path = "/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String userName, @RequestParam String bio) {
		return userService.addUser(userName, bio);
	}

	//add a user
	@RequestMapping(method = RequestMethod.POST, value = "/users")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	//add user with username and bio


	//update user
	@RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}")
	public void updateBio(@PathVariable int userId, @RequestBody User user) {
		userService.updateUser(userId, user);
	}


	//update bio
//		@RequestMapping(method=RequestMethod.PUT, value = "/users/{userId}")
//		public void updateBio(@PathVariable String userId) {
//			userService.updateBio(userId);
//		}
//


	//delete a user
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
	public void addUser(@PathVariable int userId) {
		userService.deleteUser(userId);
	}


	//add a friend
	@RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/friends/{friendId}")
	public String addFriend(@PathVariable int userId, @PathVariable int friendId) {
		userService.addFriend(userId, friendId);
		return "added friend";
	}

	//Force add a friend. Maybe delete this later? 
	@RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/friends/{friendId}/force")
	public String addFriendWeird(@PathVariable int userId, @PathVariable int friendId) {
		userService.addFriend(userId, friendId);
		userService.addFriend(friendId, userId);
		return "added friend";
	}

	//getfriends to
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/friendsto")
	public List<User> getFriendsTo(@PathVariable int userId) {
		return userService.getFriendsTo(userId);
	}
	//get freinds of
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/friendsof")
	public List<User> getFriendsOf(@PathVariable int userId) {
		return userService.getFriendsOf(userId);
	}

	//remove friend
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/friends/{friendId}")
	public void deleteFriend(@PathVariable int userId, @PathVariable int friendId) {
		userService.removeFriend(userId, friendId);
	}

	//Force Remove Friend. Maybe delete this later.
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/friends/{friendId}/force")
	public void deleteFriend(@PathVariable int userId, @PathVariable int friendId) {
		userService.removeFriend(userId, friendId);
		userService.removeFriend(userId, friendId);
	}

	//get friends
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/friends")
	public List<User> getFriends(@PathVariable int userId) {
		return userService.getFriends(userId);
	}


	//get friend requests to me
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/requests")
	public List<User> getRequests(@PathVariable int userId) {
		return userService.getRequests(userId);
	}

	//get pending friend reuquest to others
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/pending")
	public List<User> getPending(@PathVariable int userId) {
		return userService.getPending(userId);
	}
	//get undiscovered people around me
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/discovery")
	public List<User> getDiscovery(@PathVariable int userId) {
		return userService.getDiscovery(userId);
	}


}
