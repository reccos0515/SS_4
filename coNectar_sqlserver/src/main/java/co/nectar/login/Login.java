package co.nectar.login;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import co.nectar.user.User;

@Entity
public class Login {

	//variables
	@Id
	Integer id;
	
	@OneToOne
	User user;
	String password;
	
	
}
