package com.bridgeit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue
	private int id; 
	@Column
	@NotEmpty(message=" please provide username ")
	
	private String username;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public User()
	{
		super();
		this.enabled=false;
	}
	@Column
	@NotEmpty(message="please provide password")
	private String password;
	
	@Column(nullable=false, unique=true)
	@NotEmpty(message="email not cannot be blank")
	@Email(message="please enter a valid email")
	private String email;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
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
	

}
