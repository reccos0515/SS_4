package co.nectar.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.nectar.HtmlResponce.HtmlError;
import co.nectar.HtmlResponce.HtmlResponce;
import co.nectar.HtmlResponce.HtmlMsgList;
import co.nectar.HtmlResponce.HtmlUserList;
import co.nectar.user.User;
import co.nectar.user.UserService;

@Service
public class MessageService {
	@Autowired
	private MessageRepository msgRepo;
	
	@Autowired
	private UserService userService;
	
	public HtmlResponce getMessages(Integer toId) {
		boolean success = true;
		String error = "";
		HtmlResponce htmlmsg;
		User toUser;
		//check if toId is valid
		htmlmsg = userService.getUserById(toId);
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "userTo is not valid user: "+error);
		}
			
		//get user object of toId 
		toUser = ((HtmlUserList) htmlmsg).getUsers().iterator().next();
		
		
		List<Message> msgs = new ArrayList<>();
		msgs = msgRepo.findByUserTo(toUser);
		if(msgs.size() == 0)
			return new HtmlError(false,"no messages");
		
		return new HtmlMsgList(success, msgs);
			
		
	}

	public HtmlResponce deleteMessages(Integer toId) {
		HtmlResponce htmlmsg = this.getMessages(toId);
		
		//remove all items returned if successful
		if(htmlmsg.isSuccess()) {
			Iterable<Message> messages = ((HtmlMsgList) htmlmsg).getMessage();
			msgRepo.deleteAll(messages);
		}
		return htmlmsg;
	}

	public HtmlResponce addMessages(Integer toId, Message msg) {
		boolean success = true;
		String error = "";
		HtmlResponce htmlmsg;
		User toUser,fromUser;
		
		//check if toId is valid
		htmlmsg = userService.getUserById(toId);
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "userTo is not valid user: "+error);
		}
			
		
		//get user object of toId and save in message
		toUser = ((HtmlUserList) htmlmsg).getUsers().iterator().next();
		msg.setUserTo(toUser);
		
		
		//check if correct user is given
		htmlmsg = userService.getUserByObject(msg.getUser());
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "user from is not valid user: "+error);
		}
		
		//get full user from object and save in message
		fromUser = ((HtmlUserList) htmlmsg).getUsers().iterator().next();
		msg.setUser(fromUser);
		
		
		if(!msg.isValid()) {
			success = false;
			error = "message fields not valid";
		} else {
			msgRepo.save(msg);
		}
		
		return new  HtmlError(success, error);
	}

}
