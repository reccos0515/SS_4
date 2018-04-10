package co.nectar.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

	boolean existsByUserName(String userName);
	Optional<User> findByUserName(String userName);
}
