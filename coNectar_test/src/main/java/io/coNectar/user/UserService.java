package io.coNectar.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		return users;
	}

	public User getUser(String userId) {
		return userRepo.findOne(userId);
	}

	public void addUser(User user) {
		userRepo.save(user);
	}

	public void deleteUser(String userId) {
		userRepo.delete(userId);
	}

	public void updateBio(String userId) {
		// TODO Auto-generated method stub
		
	}

	public void updateUser(String userId, User user) {
		userRepo.save(user);
		
	}

//	public User getFriends(String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public void addFriend(String userId, String friendId) {
//		// TODO Auto-generated method stub
//		
//	}

}
