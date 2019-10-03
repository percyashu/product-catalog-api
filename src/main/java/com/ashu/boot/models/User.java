package com.ashu.boot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usertbl")
public class User {
	@Id
	private String username;
	@Column(name = "Name")
	private String name;
	@Column(name = "Password")
	private String password;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return String.format("User [username=%s, name=%s, password=%s]", username, name, password);
	}

	



}
