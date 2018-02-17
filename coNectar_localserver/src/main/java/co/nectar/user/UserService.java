package co.nectar.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesJava7;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	
	private List<User> users = new ArrayList<User>(Arrays.asList(new User(1,"Ben","i am ben")));
	
	public List<User> getAllUsers() {
		return users;
	}

	public User getUser(int userId) {
		return users.stream().filter(t -> (t.getId() == userId)).findFirst().get();
	}

	public void addUser(User user) {
		users.add(user);
	}

	public void deleteUser(int userId) {
		users.remove(userId);
	}

	public void updateBio(int userId) {
		// TODO Auto-generated method stub
		
	}

	public void updateUser(int userId, User user) {
		for(int i = 0; i <users.size(); i++) {
			User t =users.get(i);
			if(t.getId() == (userId)) {
				users.set(i, user);
				return;
			}
		}
		
	}


	public List<User> getFriendsTo(int userId) {
		//reutrns friends to
		List<User> users = new ArrayList<>();
		List<Integer> friends = this.getUser(userId).getFriendsTo();
		
		//get user object of all friends
		for(int friend : friends) {
			users.add(getUser(friend));
		}
		return users;
	}
	
	
	public List<User> getFriendsOf(int userId) {
		//returns friends of
		List<User> users = new ArrayList<>();
		List<Integer> friends = this.getUser(userId).getFriendsOf();
		
		//get user object of all friends
		for(int friend : friends) {
			users.add(getUser(friend));
		}
		return users;
	}

	public void addFriend(int userId, int friendId) {
		this.getUser(userId).getFriendsTo().add(friendId);
		this.getUser(friendId).getFriendsOf().add(userId);
	}
	
	public void removeFriend(int userId, int friendId) {
		this.getUser(userId).getFriendsTo().remove(friendId);
		this.getUser(friendId).getFriendsOf().remove(userId);
	}

}
