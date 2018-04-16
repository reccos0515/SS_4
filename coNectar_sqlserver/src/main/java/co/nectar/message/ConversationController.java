package co.nectar.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.nectar.HtmlResponce.HtmlResponce;

@RestController
@RequestMapping(path="/ben")
public class ConversationController {
	@Autowired
	ConversationService msgService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/messages/to/{toId}/from/{fromId}")
	public HtmlResponce getConversation(@PathVariable Integer toId, @PathVariable Integer fromId) {
		return msgService.getMessagesBetween(toId,fromId);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/messages/to/{toId}/from/{fromId}")
	public HtmlResponce deleteConversation(@PathVariable Integer toId, @PathVariable Integer fromId) {
		return msgService.deleteConversation(toId,fromId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/messages/to/{toId}/from/{fromId}")
	public HtmlResponce addMessage(@PathVariable Integer toId, @PathVariable Integer fromId, @RequestBody Message message) {
		return msgService.addMessages(toId,fromId,message);
	}
}
