package co.nectar.block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.nectar.HtmlResponce.HtmlResponce;
@CrossOrigin
@RestController
@RequestMapping(path="/ben")
public class BlockController {
	@Autowired
	BlockService blockService;
	
	/**
	 * blocks userId
	 * @param userId user id to block
	 * @return html responce denoting success
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/block/{userId}")
	public HtmlResponce blockUser(@PathVariable Integer userId) {
		return blockService.blockUser(userId);
	}
	/**
	 * unblocks user id 
	 * @param userId user id to unblock
	 * @return html responce denoting success
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/block/{userId}")
	public HtmlResponce unblockUser(@PathVariable Integer userId) {
		return blockService.unblockUser(userId);
	}
	
	
}
