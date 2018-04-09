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
public class MessageController {
	@Autowired
	MessageService msgService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/users/{toId}/messages")
	public HtmlResponce getMessages(@PathVariable Integer toId) {
		return msgService.getMessages(toId);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{toId}/messages")
	public HtmlResponce deleteMessages(@PathVariable Integer toId) {
		return msgService.deleteMessages(toId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/users/{toId}/messages")
	public HtmlResponce addMessages(@PathVariable Integer toId, @RequestBody Message message) {
		return msgService.addMessages(toId,message);
	}
}
