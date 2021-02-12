package com.bagri.BookSystem.Model;


import java.io.Serializable;
import java.util.List;

import com.bagri.BookSystem.Entity.Cart;
import com.bagri.BookSystem.Entity.Issue;
import com.bagri.BookSystem.Entity.ReserveBook;
import com.bagri.BookSystem.Entity.User;


public class CheckoutModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	private Cart cart;
	private List<Issue> cartIssue;
	private ReserveBook orderDetail;
	private int checkoutTotal;

	public ReserveBook getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(ReserveBook orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getCheckoutTotal() {
		return checkoutTotal;
	}

	public void setCheckoutTotal(int checkoutTotal) {
		this.checkoutTotal = checkoutTotal;
	}

	public List<Issue> getcartIssue() {
		return cartIssue;
	}

	public void setcartIssue(List<Issue> cartIssue) {
		this.cartIssue = cartIssue;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
