package com.bagri.BookSystem.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bagri.BookSystem.DAO.UserDAO;
import com.bagri.BookSystem.Entity.Cart;
import com.bagri.BookSystem.Entity.User;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDAO;

	 @Autowired
	 private PasswordEncoder passwordEncoder;

	public RegisterModel init() {
		return new RegisterModel();
	}
	public String validateUser(User user, MessageContext error) {
		  String transitionValue = "success";
		   if(!user.getPassword().equals(user.getConfirmPassword())) {
		    error.addMessage(new MessageBuilder().error().source(
		      "confirmPassword").defaultText("Password does not match confirm password!").build());
		    transitionValue = "failure";    
		   }  
		   if(userDAO.getByEmail(user.getEmail())!=null) {
		    error.addMessage(new MessageBuilder().error().source(
		      "email").defaultText("Email address is already taken!").build());
		    transitionValue = "failure";
		   }
		  return transitionValue;
		 }

	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}

	public String saveAll(RegisterModel registerModel) {
		String transitionValue = "success";
		User user = registerModel.getUser();
		if (user.getRole().equals("USER")) {
			// create a new cart
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}

		  // encode the password
		  user.setPassword(passwordEncoder.encode(user.getPassword()));

		// save the user
		userDAO.addUser(user);
		return transitionValue;
	}
}
