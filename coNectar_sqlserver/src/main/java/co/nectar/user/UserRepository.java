package co.nectar.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

	boolean existsByUserName(String userName);
	User findByUserName(String userName);
}
