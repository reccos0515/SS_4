package co.nectar.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.nectar.HtmlResponce.HtmlResponce;

@CrossOrigin
@RestController
@RequestMapping(path="/ben")
public class ConversationController {
	@Autowired
	ConversationService msgService;
	/**
	 * get messages between two users
	 * @param toId user messages send to
	 * @param fromId user messages sent from
	 * @return HtmlReponse denoting success
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/messages/to/{toId}/from/{fromId}")
	public HtmlResponce getConversation(@PathVariable Integer toId, @PathVariable Integer fromId) {
		return msgService.getMessagesBetween(toId,fromId);
	}
	
	/**
	 * Deletes conversation between two users. Note: only deletes one users conversation.
	 * @param toId user deleting messages
	 * @param fromId user which toId wishes to delete messages from
	 * @return HtmlReponse denoting success
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/messages/to/{toId}/from/{fromId}")
	public HtmlResponce deleteConversation(@PathVariable Integer toId, @PathVariable Integer fromId) {
		return msgService.deleteConversation(toId,fromId);
	}
	/**
	 * Add message between toId and fromId. creates new conversation if does not exist 
	 * @param toId user to send message to
	 * @param fromId user to send message from
	 * @param message message to send
	 * @return HtmlReponse denoting success
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/messages/to/{toId}/from/{fromId}")
	public HtmlResponce addMessage(@PathVariable Integer toId, @PathVariable Integer fromId, @RequestBody Message message) {
		return msgService.addMessages(toId,fromId,message);
	}
}
