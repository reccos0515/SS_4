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


	public List<User> getFriendsTo(int userId) {

		return userRepo.findOne(userId).getFriendsTo();
	}
	
	
	public List<User> getFriendsOf(int userId) {
		return userRepo.findOne(userId).getFriendsOf();
	}

	public void addFriend(int userId, int friendId) {
		User user = this.getUser(userId);
		User friend = this.getUser(friendId);
		
		user.getFriendsTo().add(friend);
		friend.getFriendsOf().add(user);
		
		userRepo.save(user);
		userRepo.save(friend);
	}
	
	public void removeFriend(int userId, int friendId) {
		User user = this.getUser(userId);
		User friend = this.getUser(friendId);
		
		//clear friends
		user.getFriendsTo().remove(friend);
		friend.getFriendsOf().remove(user);
		userRepo.save(user);
		userRepo.save(friend);
	}

	//friends are in both friendTo and friendFrom
	public List<User> getFriends(int userId) {
		List<User> friends = new ArrayList<User>();
		User user = this.getUser(userId);
		
		//get to and from friend lists
		List<User> to = user.getFriendsTo();
		List<User> from = user.getFriendsOf();
		
		for(User friend : to) {
			if(from.contains(friend))
				friends.add(friend);//add if in both to and from lists
		}
		return friends;
	}

	//request are in friend to but not from
	public List<User> getRequests(int userId) {
		List<User> requests = new ArrayList<User>();
		User user = this.getUser(userId);
		
		//get to and from friend lists
		List<User> to = user.getFriendsTo();
		List<User> from = user.getFriendsOf();
		
		for(User friend : to) {
			if(!from.contains(friend))
				requests.add(friend);//add friend is not in from
		}
		return requests;
	}

	//pending users are in from but not in to
	public List<User> getPending(int userId) {
		List<User> requests = new ArrayList<User>();
		User user = this.getUser(userId);
		
		//get to and from friend lists
		List<User> to = user.getFriendsTo();
		List<User> from = user.getFriendsOf();
		
		for(User friend : from) {
			if(!from.contains(friend))
				requests.add(friend);//add friend is not in to yet
		}
		return requests;
	}
	// a discovery get all users not in current users friend list (except current user)
	public List<User> getDiscovery(int userId) {
		List<User> discovery = new ArrayList<User>();
		User user = this.getUser(userId);
		
		//get all users and current user's friends
		List<User> users = (List<User>) this.getAllUsers();
		List<User> friends = this.getFriends(userId);
		
		for(User user_ele : users) {
			if(!friends.contains(user_ele) && !user_ele.equals(user))
				discovery.add(user_ele);//add if in both to and from lists
		}
		return discovery;
	}

}
