package com.bagri.MemberVeiw.Exception;

import java.io.Serializable;

public class BookNotFoundException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public BookNotFoundException() {
		this("Book is not available!");
	}
	
	public BookNotFoundException(String message) {
		this.message = System.currentTimeMillis() + ": " + message;
	}

	public String getMessage() {
		return message;
	}
}
