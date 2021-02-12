package com.bagri.BookSystem.DAO;

import com.bagri.BookSystem.Entity.User;

public interface UserDAO {

	// user related operation
	User getByEmail(String email);

	User get(int id);

	boolean addUser(User user);

///	boolean updateCart(Cart cart);

	// adding and updating a new address
	User getAddress(int addressId);

	boolean addAddress(User address);

	boolean updateAddress(User address);

}
