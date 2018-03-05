package co.nectar.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.nectar.Message.HtmlMessage;
import co.nectar.user.User;
import co.nectar.user.UserService;

@Service
public class LoginService {
	/**
	 * flag to enable generic error messages
	 * prevents bad users from guessing all user names
	 */
	private static boolean secureMode = false;

	@Autowired
	LoginRepository loginRepo;
	
	@Autowired
	UserService userService;
	
	/**
	 * adds login to repository
	 * 
	 * returns non success if:
	 * missing password
	 * missing user id
	 * user already exists
	 * 
	 * @param login login to be added
	 * @return
	 */
	public HtmlMessage addLogin(Login login) {
		boolean success = true;
		String error = "";
		if(login.getPassword().equals("") || login.getPassword() == null) {
			success = false;
			error = "password null or empty";
		}else if(userService.userExists(login.getUser())) {
			success = false;
			error = "login exists";
		}else if(!login.getUser().isValid()){
			success = false;
			error = "user is missing requierd fields";			
		}else {
			//save login and user
			User added = userService.addUser(login.getUser());
			login.setUser(added);
			loginRepo.save(login);
		}
		
		
		//give cryptic message if secure mode is enabled
		//disallows people from guessing usernames
		if(secureMode) {
			error = "unable to login";
		}
		HtmlMessage msg = new HtmlMessage(success,error);
		return msg;
	}

	/**
	 * removes login from repository
	 * 
	 * returns non success value if:
	 * login does not exist
	 * user not found
	 * 
	 * 
	 * @param login login to be removed
	 * @return
	 */
	public HtmlMessage removeLogin(Login login) {
		boolean success = true;
		String error= "";
		
		if(!login.isValid()) {
			success  =false;
			error = "missing fields from given login";
		}
		else if(!loginRepo.existsByUser(login.getUser())) {
			success = false;
			error = "given user not found";
		}else {
			//remove login and user
			//handle if userId is empty
			User user = login.getUser();
			if(user.getId() == null)
				user = userService.getUserByUserName(user);
						
			login = loginRepo.findByUser(user);
			loginRepo.delete(login);
			userService.deleteUser(user.getId());
		}
		
		
		return null;
	}


	/**
	 * returns user information based on login information
	 * 
	 * returns error if:
	 * user/login not found
	 * passwords do not match
	 * 
	 * @param login
	 * @return
	 */
	public Object getUser(Login login) {
		// TODO Auto-generated method stub
		User user = new User(0, "Test", "no bio");
		Login test = new Login(user, "password");
		return test;
	}

}
