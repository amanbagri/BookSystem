package com.bagri.BookSystem.DAO;

import java.util.List;

import com.bagri.BookSystem.Entity.Cart;
import com.bagri.BookSystem.Entity.Issue;
import com.bagri.BookSystem.Entity.ReserveBook;


public interface IssueDAO {

	public List<Issue> list(int cartId);
	public Issue get(int id);	
	public boolean add(Issue cartLine);
	public boolean update(Issue cartLine);
	public boolean remove(Issue cartLine);
	
	// fetch the CartLine based on cartId and bookId
	public Issue getByCartAndBook(int cartId, int bookId);		
		
	// updating the cart
	boolean updateCart(Cart cart);
	
	// list of available cartLine
	public List<Issue> listAvailable(int cartId);
	
	// adding order details
	boolean addOrderDetail(ReserveBook orderDetail);

	
}
