package co.nectar.block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.nectar.HtmlResponce.HtmlError;
import co.nectar.HtmlResponce.HtmlResponce;
import co.nectar.user.UserService;

@Service
public class BlockService {
	@Autowired
	UserService userService;
	
	/**
	 * blocks userId
	 * @param userId user id to block
	 * @return html responce denoting success
	 */
	public HtmlResponce blockUser(Integer userId) {
		if(userService.blockUser(userId))
			return new HtmlError(true,"");
		else
			return new HtmlError(false,"userId not valid");
	}

	/**
	 * unblocks user id 
	 * @param userId user id to unblock
	 * @return html responce denoting success
	 */
	public HtmlResponce unblockUser(Integer userId) {
		if(userService.unblockUser(userId))
			return new HtmlError(true,"");
		else
			return new HtmlError(false,"userId not valid");
	}

}
