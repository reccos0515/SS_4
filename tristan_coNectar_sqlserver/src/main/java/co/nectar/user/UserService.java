package co.nectar.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesJava7;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	
	public Iterable<User> getAllUsers() {
		//return users;
		return userRepo.findAll();
	}

	public User getUser(int userId) {
		return userRepo.findOne(userId);
	}

	public void addUser(User user) {
		userRepo.save(user);
	}
	
	public String addUser(String userName, String bio) {
		User user = new User();
		user.setUserName(userName);
		user.setBio(bio);
		userRepo.save(user);
		return "saved";
	}

	public void deleteUser(int userId) {
		userRepo.delete(userId);
	}

	public void updateBio(int userId) {
		// TODO Auto-generated method stub
		
	}

	public void updateUser(int userId, User user) {
		userRepo.save(user);
		
	}


	public int getFriendsTo(int userId) {

		return userRepo.findOne(userId).getFriendTo();
	}
	
	
	public int getFriendsOf(int userId) {
		return userRepo.findOne(userId).getFriendOf();
	}

	public void addFriend(int userId, int friendId) {
		User user = this.getUser(userId);
		User friend = this.getUser(friendId);
		
		user.setFriendTo(friendId);
		friend.setFriendOf(userId);
		
		userRepo.save(user);
		userRepo.save(friend);
	}
	
	public void removeFriend(int userId, int friendId) {
		User user = this.getUser(userId);
		User friend = this.getUser(friendId);
		
		//clear friends
		user.setFriendTo(-1);
		friend.setFriendOf(-1);
		userRepo.save(user);
		userRepo.save(friend);
	}

}
