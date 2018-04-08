package co.nectar.message;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.nectar.user.User;

public interface MessageRepository extends CrudRepository<Message,Integer>{
	List<Message> findByUserTo(User user);
}
