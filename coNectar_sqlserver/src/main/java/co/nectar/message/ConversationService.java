package co.nectar.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.nectar.HtmlResponce.HtmlError;
import co.nectar.HtmlResponce.HtmlErrorWithObj;
import co.nectar.HtmlResponce.HtmlResponce;
import co.nectar.HtmlResponce.HtmlMsgList;
import co.nectar.HtmlResponce.HtmlUserList;
import co.nectar.user.User;
import co.nectar.user.UserService;

@Service
public class ConversationService {
	@Autowired
	private ConversationRepository convoRepo;
	
	@Autowired
	private UserService userService;
	@Autowired
	private MessageRepository msgRepo;
	
	/**
	 * Add message between toId and fromId. creates new conversation if does not exist 
	 * @param toId user to send message to
	 * @param fromId user to send message from
	 * @param msg message to send
	 * @return HtmlReponse denoting success
	 */
	public HtmlResponce addMessages(Integer toId, Integer fromId, Message msg) {
		boolean success = true;
		String error = "";
		
		
		HtmlResponce htmlmsg;
		User userTo,userFrom;
		//check if toId is valid
		htmlmsg = userService.getUserById(toId);
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "id to is not valid id: " + error);
		}
		//get user object of toId and save in message
		userTo = ((HtmlUserList) htmlmsg).getUsers().iterator().next();
		msg.setUserTo(userTo);
		
		
		//check if fromId is valid
		htmlmsg = userService.getUserById(fromId);
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "id from is not valid id: " + error);
		}
		//get user object of fromId and save in message
		userFrom = ((HtmlUserList) htmlmsg).getUsers().iterator().next();
		msg.setUserFrom(userFrom);
		
		//check if message is fully filled
		if(!msg.isValid()) {
			success = false;
			error = "message fields not valid";
			return new HtmlErrorWithObj(success, error,msg);
		}
		
		
		//add message to both sides of the conversation
		this.addConvo(userTo, userFrom, msg);
//		this.addConvo(userFrom, userTo, msg);
	
		return new  HtmlError(true, "added message");
	}

	private void addConvo(User userTo, User userFrom, Message msg) {
		Optional<Conversation> opt;
		Conversation convoTo, convoFrom;
		
		//add to userTo userFrom convo
		//add conversation if not existing otherwise find old conversation
		opt = convoRepo.findByUserToAndUserFrom(userTo, userFrom);
		if(!opt.isPresent()) {
			convoTo = new Conversation(userTo, userFrom, new ArrayList<Message>());
		}else {
			convoTo = opt.get();
		}
		
		//get other convo
		opt = convoRepo.findByUserToAndUserFrom(userFrom, userTo);
		if(!opt.isPresent()) {
			convoFrom = new Conversation(userFrom, userTo, new ArrayList<Message>());
		}else {
			convoFrom = opt.get();
		}
		
		//save message and get saved message
		msg = msgRepo.save(msg);
		
		//add message
		convoTo.getMessages().add(msg);
		
		
		
		//save conversation
		convoRepo.save(convoTo);
		
//		msg.setId(0);
		msg = msgRepo.save(msg);
		convoFrom.getMessages().add(msg);
		
		convoRepo.save(convoFrom);
	}
	/**
	 * get messages between two users
	 * @param toId user messages send to
	 * @param fromId user messages sent from
	 * @return HtmlReponse denoting success
	 */
	public HtmlResponce getMessagesBetween(Integer toId, Integer fromId) {
		boolean success = true;
		String error = "";
		
		HtmlResponce htmlmsg;
		User userTo,userFrom;
		Conversation convo;
		List<Message> msgs;
		Optional<Conversation> opt;
		//check if toId is valid
		htmlmsg = userService.getUserById(toId);
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "id to is not valid id: " + error);
		}
		//get user object of toId
		userTo = ((HtmlUserList) htmlmsg).getUsers().iterator().next();
		
		
		//check if fromId is valid
		htmlmsg = userService.getUserById(fromId);
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "id from is not valid id: " + error);
		}
		//get user object of fromId
		userFrom = ((HtmlUserList) htmlmsg).getUsers().iterator().next();

		//get messages
		
		opt = convoRepo.findByUserToAndUserFrom(userTo, userFrom);
		
		//check if conversation is added
		if(!opt.isPresent())
			return new HtmlError(false,"conversation between users not found! try adding a message first");
		
		//get list of messages
		convo = opt.get();
		msgs = convo.getMessages();
		
		//check if any messages present
		if(msgs.size() == 0)
			return new HtmlError(false,"no messages");
		
		
		return new HtmlMsgList(success, msgs);
			
	}

	/**
	 * Deletes conversation between two users. Note: only deletes one users conversation.
	 * @param toId user deleting messages
	 * @param fromId user which toId wishes to delete messages from
	 * @return HtmlReponse denoting success
	 */
	public HtmlResponce deleteConversation(Integer toId, Integer fromId) {
		String error = "";		
		HtmlResponce htmlmsg;
		User userTo,userFrom;
		Optional<Conversation> opt;
		//check if toId is valid
		htmlmsg = userService.getUserById(toId);
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "id to is not valid id: " + error);
		}
		//get user object of toId
		userTo = ((HtmlUserList) htmlmsg).getUsers().iterator().next();
		
		
		//check if fromId is valid
		htmlmsg = userService.getUserById(fromId);
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "id from is not valid id: " + error);
		}
		//get user object of fromId
		userFrom = ((HtmlUserList) htmlmsg).getUsers().iterator().next();

		//get conversation		
		opt = convoRepo.findByUserToAndUserFrom(userTo, userFrom);
		
		//check if conversation is added
		if(!opt.isPresent())
			return new HtmlError(false,"conversation between users not found! can not delete");
		
		
		
		
		convoRepo.delete(opt.get());
		
		
		return new HtmlError(true, "deleted");
		
	}

}
