package co.nectar.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.nectar.HtmlResponce.HtmlErrorResponce;
import co.nectar.HtmlResponce.HtmlMessage;
import co.nectar.HtmlResponce.HtmlMessageListReponce;
import co.nectar.HtmlResponce.HtmlUserListReponce;
import co.nectar.user.User;
import co.nectar.user.UserService;

@Service
public class MessageService {
	@Autowired
	private MessageRepository msgRepo;
	
	private UserService userService;
	
	public HtmlMessage getMessages(Integer toId) {
		boolean success = false;
		String error = "";
		
		if(!userService.userExistsById(toId)) {
			success = false;
			error = "userId not valid";
		}else {
			User user =  ((HtmlUserListReponce) userService.getUserById(toId)).getUsers().iterator().next();
			List<Message> msgs = msgRepo.findByUser(user);
			
			return new HtmlMessageListReponce(success, msgs);
			
		}
		return new  HtmlErrorResponce(success, error);
	}

	public HtmlMessage deleteMessages(Integer toId) {
		// TODO Auto-generated method stub
		return null;
	}

	public HtmlMessage addMessages(Integer toId, Message msg) {
		boolean success = false;
		String error = "";
		
		if(!userService.userExistsById(toId)) {
			success = false;
			error = "toId not valid";
		} else if( !userService.userExists(msg.getUser()) ) {
			success = false;
			error = "given user not valid";
		}else if(msg.getTime() == null) {
			success = false;
			error = "time not given";
		}else if(msg.getMessage() == null) {
			success = false;
			error = "message not given";
		}else {
			User user = msg.getUser();
			if(user.getId() != null)
				user =  ((HtmlUserListReponce) userService.getUserById(user.getId())).getUsers().iterator().next();
			else
				user =  ((HtmlUserListReponce) userService.getUserByUserName(user.getUserName())).getUsers().iterator().next();
			User to =  ((HtmlUserListReponce) userService.getUserById(toId)).getUsers().iterator().next();
			
			msg.setTo(to);
			msg.setUser(user);
			msgRepo.save(msg);
		}
		
		return new  HtmlErrorResponce(success, error);
	}

}
