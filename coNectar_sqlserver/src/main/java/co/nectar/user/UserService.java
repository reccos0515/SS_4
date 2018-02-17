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
	
	//private List<User> users = new ArrayList<User>(Arrays.asList(new User(1,"Ben","i am ben")));
	
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


	public List<User> getFriendsTo(int userId) {
		//reutrns friends to
//		List<User> users = new ArrayList<>();
//		List<Integer> friends = this.getUser(userId).getFriendsTo();
//		
//		//get user object of all friends
//		for(int friend : friends) {
//			users.add(getUser(friend));
//		}
//		return users;
		return userRepo.findOne(userId).getFriendsTo();
	}
	
	
	public List<User> getFriendsOf(int userId) {
		//returns friends of
//		List<User> users = new ArrayList<>();
//		List<Integer> friends = this.getUser(userId).getFriendsOf();
//		
//		//get user object of all friends
//		for(int friend : friends) {
//			users.add(getUser(friend));
//		}
//		return users;
		return userRepo.findOne(userId).getFriendsOf();
	}

	public void addFriend(int userId, int friendId) {
		User user = this.getUser(userId);
		User friend = this.getUser(friendId);
		
		user.getFriendsTo().add(friend);
		friend.getFriendsOf().add(user);
	}
	
	public void removeFriend(int userId, int friendId) {
		User user = this.getUser(userId);
		User friend = this.getUser(friendId);
		
		user.getFriendsTo().remove(friend);
		friend.getFriendsOf().remove(user);
	}

}
