package co.nectar.login;

import org.springframework.data.repository.CrudRepository;

import co.nectar.user.User;

public interface LoginRepository extends CrudRepository<Login,Integer>{
	boolean existsByUser(User user);
	Login findByUser(User user);
}
