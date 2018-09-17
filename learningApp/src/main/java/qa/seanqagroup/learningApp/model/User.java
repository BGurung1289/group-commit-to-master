package qa.seanqagroup.learningApp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import qa.seanqagroup.learningApp.model.enums.E_UserType;

@Entity
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;

	private String password;
	
	private E_UserType userType;
	
//	private Email email; //if this causes issues with database use string type instead
	@NotEmpty
	private String email;
	
	private boolean isActive;


	
	

	public User() {
	super();
	this.userId = (long) 10;
	this.firstName = "test";
	this.lastName = "test";
	this.password = "p";
	this.userType = E_UserType.LEARNER;
	this.email = "a@a.a";
	isActive=true;
}


	public User(@NotEmpty String firstName, @NotEmpty String lastName, String password, E_UserType userType,
		@NotEmpty String email) {
	super();
//	this.userId = userId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.password = password;
	this.userType = userType;
	this.email = email;
}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public E_UserType getUserType() {
		return userType;
	}


	public void setUserType(E_UserType userType) {
		this.userType = userType;
	}


	
	
	
	
	
}
