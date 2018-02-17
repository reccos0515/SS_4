package io.coNectar.user;

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
		
		//get all users
		@RequestMapping("/users")
		public List<User> getAllTopics() {
			return userService.getAllUsers();
		}
		//get a user
		@RequestMapping("/users/{userId}")
		public User getUser(@PathVariable String userId) {
			return userService.getUser(userId);
		}
		
		
		//add a user
		@RequestMapping(method=RequestMethod.POST, value = "/users")
		public void addUser(@RequestBody User user) {
			userService.addUser(user);
		}
		
		//change a user ??
		
		//delete a user
		@RequestMapping(method=RequestMethod.DELETE, value = "/users/{userId}")
		public void addUser(@PathVariable String userId) {
			userService.deleteUser(userId);
		}
		
//		//get friends
//		
//		@RequestMapping("/users/{userId}/friends")
//		public String getFriends(@PathVariable String userId) {
//			return userService.getFriends(userId);
//		}
//		//request friend
//		
//		
//		//add a friend
//		@RequestMapping(method=RequestMethod.POST, value = "/users/{userId}/friends/{friendId}")
//		public void addFriend(@PathVariable String userId, @PathVariable String friendId) {
//			userService.addFriend(userId,friendId);
//		}
//		
//		//getfriends
//		
//		//remove friend
//		@RequestMapping(method=RequestMethod.DELETE, value = "/users/{userId}/friends/{friendId}")
//		public void deleteFriend(@PathVariable String userId, @PathVariable String friendId) {
//			userService.addFriend(userId,friendId);
//		}
		
		
}
