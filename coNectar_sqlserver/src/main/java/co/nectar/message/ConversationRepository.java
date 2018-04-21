package co.nectar.message;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.nectar.user.User;


public interface ConversationRepository extends CrudRepository<Conversation,Integer>{
	
	/**
	 * find a conversation between users
	 * @param userTo user recieving messages
	 * @param userFrom user sending messages
	 * @return optional containing conversation
	 */
	Optional<Conversation> findByUserToAndUserFrom(User userTo, User userFrom);
}
