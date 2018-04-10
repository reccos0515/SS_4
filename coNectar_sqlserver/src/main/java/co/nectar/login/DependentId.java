package co.nectar.login;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable

/**
 * dependent id is throwing a null pointer when setting from postman
 * could be caused by login's getters and setters
 * @author basimon
 *
 */
public class DependentId implements Serializable {
	private String userName;
	private Integer id;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
