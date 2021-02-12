package com.bagri.BookSystem.Model;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bagri.BookSystem.DAO.BookDAO;
import com.bagri.BookSystem.DAO.IssueDAO;
import com.bagri.BookSystem.DAO.ReserveBookDAO;
import com.bagri.BookSystem.DAO.UserDAO;
import com.bagri.BookSystem.Entity.Book;
import com.bagri.BookSystem.Entity.Cart;
import com.bagri.BookSystem.Entity.Issue;
import com.bagri.BookSystem.Entity.ReserveBook;
import com.bagri.BookSystem.Entity.User;


@Component
public class CheckoutHandler {

	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private IssueDAO issueDAO;
	@Autowired
	private ReserveBookDAO reserveBookDAO;
	
	
	@Autowired
	private HttpSession session;
	
	
	public CheckoutModel init(String name) throws Exception{

		User user = userDAO.getByEmail(name);
		CheckoutModel checkoutModel = null;	

		if(user!=null) {
			checkoutModel = new CheckoutModel();
			checkoutModel.setUser(user);
			checkoutModel.setCart(user.getCart());
			int checkoutTotal = 0;
			List<Issue> cartIssue = issueDAO.listAvailable(user.getCart().getId());
			List<ReserveBook> ReserveBooks = reserveBookDAO.getIssuedBookList(user.getCart().getId());
            	
			if(cartIssue.size() == 0 ) {
				throw new Exception("There are no books available for checkout now!");
			}else if(ReserveBooks.size()+cartIssue.size()>5) {
				throw new Exception("There are more than 5 book issued!   curently issued books are : "+ReserveBooks.size());

			}
			for(Issue cartLine: cartIssue) {
				checkoutTotal += cartLine.getTotal();
			}
		
			
			checkoutModel.setcartIssue(cartIssue);
			checkoutModel.setCheckoutTotal(checkoutTotal);			
		}			
		
		return checkoutModel;
	}
	
	
	
			
	
	

	public String saveOrder(CheckoutModel checkoutModel) {
		String transitionValue = "success";	
		
		// create a new order object
		ReserveBook orderDetail = null;
		// attach the user 
				
		// attach the shipping address
		
		// fetch the billing address

		List<Issue> cartIssue = checkoutModel.getcartIssue();
		int orderTotal =0;
		int orderCount = 0;
		Book book = null;
		
		for(Issue currentIssue : cartIssue) {
			
			orderDetail = new ReserveBook();
			orderDetail.setUser(checkoutModel.getUser());

			orderDetail.setOrderCount(1);

			orderDetail.setOrderDate((new Date()));
			orderDetail.setBook(currentIssue.getBook());
			
			/*
			 * orderItem.setBook(currentIssue.getBook());
			 * orderItem.setBookCount(currentIssue.getBookCount());
			 * //orderItem.setTotal(10); orderItem.setOrderDate((new Date()));
			 * orderItem.setOrderDetail(orderDetail);
			 * orderDetail.getOrderItems().add(orderItem);
			 */

			orderTotal ++;
			
			// update the book
			// reduce the quantity of book
			book = currentIssue.getBook();
			book.setQuantity(book.getQuantity() - currentIssue.getBookCount());
			book.setIssued((book.getIssued() + currentIssue.getBookCount()));
			bookDAO.update(book);
			
			// delete the cartLine
			issueDAO.remove(currentIssue);
			
			issueDAO.addOrderDetail(orderDetail);
			
		}
		// save the order
		

		// set it to the orderDetails of checkoutModel
		checkoutModel.setOrderDetail(orderDetail);

		
		// update the cart
		Cart cart = checkoutModel.getCart();
		cart.setTotal(cart.getTotal()-orderTotal);
		cart.setIssue_item((cart.getIssue_item() - orderCount));
		issueDAO.updateCart(cart);
		
		// update the cart if its in the session
		UserModel userModel = (UserModel) session.getAttribute("userModel");
		if(userModel!=null) {
			userModel.setCart(cart);
		}
		
				
		return transitionValue;		
	}

	
	public ReserveBook getOrderDetail(CheckoutModel checkoutModel) {
		return checkoutModel.getOrderDetail();
	}
	
	
	
}



