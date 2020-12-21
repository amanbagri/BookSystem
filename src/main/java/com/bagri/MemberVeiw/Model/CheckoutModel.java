package com.bagri.MemberVeiw.Model;


import java.io.Serializable;
import java.util.List;


import com.bagri.MemberVeiw.Entity.Cart;
import com.bagri.MemberVeiw.Entity.Issue;
import com.bagri.MemberVeiw.Entity.IssuedBook;
import com.bagri.MemberVeiw.Entity.User;


public class CheckoutModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	private Cart cart;
	private List<Issue> cartLines;
	private IssuedBook orderDetail;
	private double checkoutTotal;

	public IssuedBook getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(IssuedBook orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public double getCheckoutTotal() {
		return checkoutTotal;
	}

	public void setCheckoutTotal(double checkoutTotal) {
		this.checkoutTotal = checkoutTotal;
	}

	public List<Issue> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<Issue> cartLines) {
		this.cartLines = cartLines;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
