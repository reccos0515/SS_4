package co.nectar.login;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.nectar.user.User;



@Entity
public class Login {

	//variables
	@EmbeddedId DependentId id;
	
	
	@OneToOne
	@MapsId("id")
	private User user;
	private String password;
	
	
	public Login() {
		super();
	}


	public Login(User user, String password) {
		super();
//		this.id = user.getId();
		this.user = user;
		this.password = password;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
//		this.id = user.getId();
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * returns whether all required fields for login are complete
	 * @param login login to be checked
	 * @return valid: boolean status if login is valid
	 */
	@JsonIgnore
	public boolean isValid() {
		boolean valid = true;
		if(this.user == null) {
			valid = false;
		}else if(this.password == null || this.password.equals("")) {
			valid = false;
		}
		
		
		return valid;
	}
	
	
}
