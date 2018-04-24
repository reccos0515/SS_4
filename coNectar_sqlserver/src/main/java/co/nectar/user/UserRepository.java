package co.nectar.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User, Integer>{

	boolean existsByUserName(String userName);
	User findByUserName(String userName);
	Iterable<User> findAllByBlocked(boolean blocked);
}
