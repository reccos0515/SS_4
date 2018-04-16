package co.nectar.message;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.nectar.user.User;

public interface ConversationRepository extends CrudRepository<Conversation,Integer>{
	List<Message> findByUserTo(User user);
	Optional<Conversation> findByUserToAndUserFrom(User userTo, User userFrom);
}
