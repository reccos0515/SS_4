package co.nectar.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import co.nectar.user.User;

@Entity
public class Login {

	//variables
	@Id
	@Column(name = "USER_ID")
	Integer user_id;
	@OneToOne
	@PrimaryKeyJoinColumn(name = "")
	User user;
	String password;
	
	
}
