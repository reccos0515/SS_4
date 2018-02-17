package co.nectar.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

		@Autowired
		UserService userService;
		
		//test add
		@RequestMapping("/test")
		public List<User> getTest() {
			User test = new User(2,"megan", "I am Megan");
			userService.addUser(test);
			return userService.getAllUsers();
		}
		
		//get all users
		@RequestMapping("/users")
		public List<User> getAllTopics() {
			return userService.getAllUsers();
		}
		//get a user
		@RequestMapping("/users/{userId}")
		public User getUser(@PathVariable int userId) {
			return userService.getUser(userId);
		}
		
		
		//add a user
		@RequestMapping(method=RequestMethod.POST, value = "/users")
		public void addUser(@RequestBody User user) {
			userService.addUser(user);
		}
		
		//update user
		@RequestMapping(method=RequestMethod.PUT, value = "/users/{userId}")
		public void updateBio(@PathVariable int userId,@RequestBody User user) {
			userService.updateUser(userId, user);
		}
		
		
		//update bio
//		@RequestMapping(method=RequestMethod.PUT, value = "/users/{userId}")
//		public void updateBio(@PathVariable String userId) {
//			userService.updateBio(userId);
//		}
//		
		
		
		//delete a user
		@RequestMapping(method=RequestMethod.DELETE, value = "/users/{userId}")
		public void addUser(@PathVariable int userId) {
			userService.deleteUser(userId);
		}
		
		//get friends
		
		@RequestMapping("/users/{userId}/friends")
		public String getFriends(@PathVariable String userId) {
			return userService.getFriends(userId);
		}
		//request friend
		
		
		//add a friend
		@RequestMapping(method=RequestMethod.POST, value = "/users/{userId}/friends/{friendId}")
		public void addFriend(@PathVariable String userId, @PathVariable String friendId) {
			userService.addFriend(userId,friendId);
		}
		
		//getfriends
		
		//remove friend
		@RequestMapping(method=RequestMethod.DELETE, value = "/users/{userId}/friends/{friendId}")
		public void deleteFriend(@PathVariable String userId, @PathVariable String friendId) {
			userService.addFriend(userId,friendId);
		}
		
		
}
