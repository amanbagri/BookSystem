package com.bagri.BookSystem.Model;


import java.io.Serializable;

import com.bagri.BookSystem.Entity.User;

public class RegisterModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
		
}
