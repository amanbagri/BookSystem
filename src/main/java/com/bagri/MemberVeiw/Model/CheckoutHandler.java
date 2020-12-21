package com.bagri.MemberVeiw.Model;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bagri.MemberVeiw.DAO.BookDAO;
import com.bagri.MemberVeiw.DAO.IssueDAO;
import com.bagri.MemberVeiw.DAO.UserDAO;
import com.bagri.MemberVeiw.Entity.Book;
import com.bagri.MemberVeiw.Entity.Cart;
import com.bagri.MemberVeiw.Entity.Issue;
import com.bagri.MemberVeiw.Entity.IssuedBook;
import com.bagri.MemberVeiw.Entity.IssuedBookItem;
import com.bagri.MemberVeiw.Entity.User;


@Component
public class CheckoutHandler {

	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private IssueDAO issueDAO;
	
	@Autowired
	private HttpSession session;
	
	
	public CheckoutModel init(String name) throws Exception{

		User user = userDAO.getByEmail(name);
		CheckoutModel checkoutModel = null;	

		if(user!=null) {
			checkoutModel = new CheckoutModel();
			checkoutModel.setUser(user);
			checkoutModel.setCart(user.getCart());
			
			double checkoutTotal = 0.0;
			List<Issue> cartLines = issueDAO.listAvailable(user.getCart().getId());

			if(cartLines.size() == 0 ) {
				throw new Exception("There are no books available for checkout now!");
			}
			
			
			
			checkoutModel.setCartLines(cartLines);
			checkoutModel.setCheckoutTotal(checkoutTotal);			
		}			
		
		return checkoutModel;
	}
	
	
	
			
	
	

	public String saveOrder(CheckoutModel checkoutModel) {
		String transitionValue = "success";	
		
		// create a new order object
		IssuedBook orderDetail = new IssuedBook();
				
		// attach the user 
		orderDetail.setUser(checkoutModel.getUser());
				
		// attach the shipping address
		
		// fetch the billing address

		List<Issue> cartLines = checkoutModel.getCartLines();
		IssuedBookItem orderItem = null;
		
		@SuppressWarnings("unused")
		int orderTotal = 0;
		int orderCount = 0;
		Book book = null;
		
		for(Issue cartLine : cartLines) {
			
			orderItem = new IssuedBookItem();
			
			orderItem.setBook(cartLine.getBook());
			orderItem.setBookCount(cartLine.getBookCount());
			
			orderItem.setOrderDetail(orderDetail);
			orderDetail.getOrderItems().add(orderItem);
			
			orderTotal += orderItem.getTotal();
			orderCount++;
			
			// update the book
			// reduce the quantity of book
			book = cartLine.getBook();
			book.setQuantity(book.getQuantity() - cartLine.getBookCount());
			book.setIssued((book.getIssued() + cartLine.getBookCount()));
			bookDAO.update(book);
			
			// delete the cartLine
			issueDAO.remove(cartLine);
			

			
		}
		
		orderDetail.setOrderCount(orderCount);
		orderDetail.setOrderDate((new Date()));
		
		// save the order
		issueDAO.addOrderDetail(orderDetail);

		// set it to the orderDetails of checkoutModel
		checkoutModel.setOrderDetail(orderDetail);

		
		// update the cart
		Cart cart = checkoutModel.getCart();
		cart.setIssue_item((cart.getIssue_item() - orderCount));
		issueDAO.updateCart(cart);
		
		// update the cart if its in the session
		UserModel userModel = (UserModel) session.getAttribute("userModel");
		if(userModel!=null) {
			userModel.setCart(cart);
		}
		
				
		return transitionValue;		
	}

	
	public IssuedBook getOrderDetail(CheckoutModel checkoutModel) {
		return checkoutModel.getOrderDetail();
	}
	
	
	
}



