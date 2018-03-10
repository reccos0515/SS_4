package co.nectar.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.nectar.Message.HtmlError;
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
	 * @return htmlmessage indicating sucess or error
	 */
	public HtmlError addLogin(Login login) {
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
		if(secureMode&&!success) {
			error = "unable to add login";
		}
		HtmlError msg = new HtmlError(success,error);
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
	 * @return htmlmessage(on error)
	 */
	public Object removeLogin(Login login) {
		boolean success = true;
		String error= "";
		
		//find full user to be removed
		//handle if userId is empty
		User user = login.getUser();
		if(!userService.userExists(user))
			return new HtmlError(false,"could not find user in db");
			
		if(user.getId() != null && user.getId() > 0) {
			user = userService.getUserById(user.getId());
		}else {
			user = userService.getUserByUserName(user.getUserName());
		}
		
		//set full user
		login.setUser(user);
		
		
		if(!login.isValid()) {
			success  =false;
			error = "missing fields from given login";
		}
		else if(!loginRepo.existsByUser(login.getUser())) {
			success = false;
			error = "given user not found";
		}else {	
			login = loginRepo.findByUser(user);
			loginRepo.delete(login);
			userService.deleteUserById(user.getId());
		}
		
		//give cryptic message if secure mode is enabled
		//disallows people from guessing usernames
		if(secureMode&&!success) {
			error = "unable to add login";
		}
		HtmlError msg = new HtmlError(success,error);
		return msg;
	}


	/**
	 * returns user information based on login information
	 * 
	 * returns error if:
	 * user/login not found
	 * passwords do not match
	 * 
	 * @param login
	 * @return user or htmlmessage(on error)
	 */
	public Object getUser(Login login) {
		// TODO Auto-generated method stub
		boolean success = true;
		String error = "";
		User user = login.getUser();
		if(user == null) {
			success = false;
			error = "user not given";
		}else if(!userService.userExists(user)) {
			success = false;
			error = "user not found";
		} else {
			//handle if user id is null or not valid
			if(user.getId() != null && user.getId() > 0) {
				user = userService.getUserById(user.getId());
			}else {
				user = userService.getUserByUserName(user.getUserName());
			}
			
			//check password
			String pass = loginRepo.findByUser(user).getPassword();//get password
			if(!pass.equals(login.getPassword())) {
				success = false;
				error = "incorrect password";
			}else {
				return user;
			}
		}
		
		
		//give cryptic message if secure mode is enabled
		//disallows people from guessing usernames
		if(secureMode) {
			error = "unable to login";
		}	
		HtmlError msg = new HtmlError(success, error);
		return msg;
	}

}
