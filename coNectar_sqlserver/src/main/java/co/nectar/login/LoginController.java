package co.nectar.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.nectar.Message.HtmlError;
import co.nectar.user.User;



@RestController
@RequestMapping(path = "/ben")
public class LoginController {
	@Autowired
	LoginService loginService;
	
	//map all to feedback
	@RequestMapping("/login/feedback")
	public Object postFeedback(@RequestBody Login login) {
		return login.isValid();	
	}
	
	
	/**
	 * adds login to login list
	 * 
	 * returns error if:
	 * existing user name
	 * missing login fields 
	 * 
	 * @param login
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/login/add")
	public Object addLogin(@RequestBody Login login) {
		return loginService.addLogin(login);	
	}
	
	/**
	 * removes login from login list
	 * 
	 * returns error if unable to remove login
	 * 
	 * @param login login to be removed
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/login")
	public Object removeLogin(@RequestBody Login login) {
		return loginService.removeLogin(login);	
	}
	
	/**
	 * returns user based on given login information
	 * 
	 * returns html error object if:
	 * missing login information
	 * invalid login information
	 * login in not found
	 * 
	 * @param login
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public Object getLogin(@RequestBody Login login) {
		return loginService.getUser(login);	
	}
	

}
