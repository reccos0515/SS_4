package co.nectar.message;

import java.util.ArrayList;
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
	
	@Autowired
	private UserService userService;
	
	public HtmlMessage getMessages(Integer toId) {
		boolean success = true;
		String error = "";
		
		if(!userService.userExistsById(toId)) {
			success = false;
			error = "userId not valid";
		}else {
			User user =  ((HtmlUserListReponce) userService.getUserById(toId)).getUsers().iterator().next();
			List<Message> msgs = new ArrayList<>();
			msgs.add(msgRepo.findByUserTo(toId));
			
			return new HtmlMessageListReponce(success, msgs);
			
		}
		return new  HtmlErrorResponce(success, error);
	}

	public HtmlMessage deleteMessages(Integer toId) {
		// TODO Auto-generated method stub
		return null;
	}

	public HtmlMessage addMessages(Integer toId, Message msg) {
		boolean success = true;
		String error = "";
		
		//set to field
		msg.setUserTo(toId);
		
		if(!msg.isValid()) {
			success = false;
			error = "message fields not valid";
		}else if(!userService.userExistsById(msg.getUserFrom())) {
			success = false;
			error = "toId not valid";
		} else if( !userService.userExistsById(msg.getUserFrom()) ) {
			success = false;
			error = "given user not valid";
		}else {
			msgRepo.save(msg);
		}
		
		return new  HtmlErrorResponce(success, error);
	}

}
