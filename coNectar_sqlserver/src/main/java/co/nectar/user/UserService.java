package co.nectar.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesJava7;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired //Automatically connects relevant beans.
	private UserRepository userRepo; //Define a repository to use in this service.

	/**
	* Returns a list of all users stored in the repository.
	* @return List of all users
	*/
	public Iterable<User> getAllUsers() {
		return userRepo.findAll();
	}

	/**
	* Returns a user that has the given ID.
	* @param userId The id of the user you wish to return.
	* @return User with id = userId.
	*/
	public User getUser(int userId) {
		return userRepo.findOne(userId);
	}
	
	public User getUserByUserName(User user) {
		return userRepo.findByUserName(user.getUserName());
	}

	/**
	* Adds a user object to the repository.
	* @param user The user object to be added
	*/
	public User addUser(User user) {
		return userRepo.save(user);
	}

	/**
	* What is this supposed to do? It seems wrong.
	* @param userName The username of the user.
	* @param bio The bio of the user.
	* @return Reports that the user was saved.
	*/
	public String addUser(String userName, String bio) {
		User user = new User();
		user.setUserName(userName);
		user.setBio(bio);
		userRepo.save(user);
		return "saved";
	}

	/**
	 * returns if specified user's id exists in db
	 *
	 * @param user
	 * @return
	 */
	public boolean userExists(User user) {
		//check to make sure id is not null
		return userRepo.existsByUserName(user.getUserName()) || (user.getId() != null && userRepo.exists(user.getId())) ;
	}
	

	
	public void deleteUser(int userId) {
		userRepo.delete(userId);
		
	}

	public void updateBio(int userId) {
		// TODO Auto-generated method stub

	}

	public void updateUser(User user) {
		userRepo.save(user);

	}


	public List<User> getSentRequestTo(int userId) {

		return userRepo.findOne(userId).getSentRequestTo();
	}


	public List<User> getRecievedRequestFrom(int userId) {
		return userRepo.findOne(userId).getRecievedRequestFrom();
	}

	public void requestFriend(int userId, int friendId) {
		User user = this.getUser(userId);
		User friend = this.getUser(friendId);

		user.getSentRequestTo().add(friend);
		friend.getRecievedRequestFrom().add(user);

		userRepo.save(user);
		userRepo.save(friend);
	}

	public void removeFriend(int userId, int friendId) {
		User user = this.getUser(userId);
		User friend = this.getUser(friendId);

		//clear friends
		user.getSentRequestTo().remove(friend);
		friend.getRecievedRequestFrom().remove(user);
		userRepo.save(user);
		userRepo.save(friend);
	}

	//friends are in both friendTo and friendFrom
	public List<User> getFriends(int userId) {
		List<User> friends = new ArrayList<User>();
		User user = this.getUser(userId);

		//get to and from friend lists
		List<User> to = user.getSentRequestTo();
		List<User> from = user.getRecievedRequestFrom();

		for (User friend : to) {
			if (from.contains(friend))
				friends.add(friend);//add if in both to and from lists
		}
		return friends;
	}

	//request are in friend to but not from
	public List<User> getOutgoingRequests(int userId) {
		List<User> requests = new ArrayList<User>();
		User user = this.getUser(userId);

		//get to and from friend lists
		List<User> to = user.getSentRequestTo();
		List<User> from = user.getRecievedRequestFrom();

		for (User friend : to) {
			if (!from.contains(friend))
				requests.add(friend);//add friend is not in from
		}
		return requests;
	}

	//pending users are in from but not in to
	public List<User> getIncomingRequests(int userId) {
		List<User> requests = new ArrayList<User>();
		User user = this.getUser(userId);

		//get to and from friend lists
		List<User> to = user.getSentRequestTo();
		List<User> from = user.getRecievedRequestFrom();

		for (User friend : from) {
			if (!to.contains(friend))
				requests.add(friend);//add friend is not in to yet
		}
		return requests;
	}
	// a discovery get all users that the current user had not friended or requested
	public List<User> getDiscovery(int userId) {
		List<User> discovery = new ArrayList<User>();
		User user = this.getUser(userId);

		//get all users and current user's requests
		List<User> users = (List<User>) this.getAllUsers();
		List<User> requests = this.getSentRequestTo(userId);

		for (User user_ele : users) {
			if (!requests.contains(user_ele) && !user_ele.equals(user))
				discovery.add(user_ele);//add if in both to and from lists
		}
		return discovery;
	}

}
