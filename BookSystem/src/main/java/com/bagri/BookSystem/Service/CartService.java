package com.bagri.BookSystem.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagri.BookSystem.DAO.BookDAO;
import com.bagri.BookSystem.DAO.IssueDAO;
import com.bagri.BookSystem.DAO.IssuedBookDAO;
import com.bagri.BookSystem.DAO.ReserveBookDAO;
import com.bagri.BookSystem.Entity.Book;
import com.bagri.BookSystem.Entity.Cart;
import com.bagri.BookSystem.Entity.Issue;
import com.bagri.BookSystem.Entity.IssuedBook;
import com.bagri.BookSystem.Entity.ReserveBook;
import com.bagri.BookSystem.Model.UserModel;

@Service("cartService")
public class CartService {

	@Autowired
	private IssueDAO cartLineDAO;

	@Autowired
	private ReserveBookDAO reserveBookDAO;
	@Autowired
	private IssuedBookDAO issuedBookDAO;
	

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private HttpSession session;

	public List<Issue> getCartLines() {

		return cartLineDAO.list(getCart().getId());

	}
	public List<ReserveBook> getReserveBook() {

		return reserveBookDAO.getIssuedBookList(getCart().getUser().getId());

	}
	public List<IssuedBook> getIssuedBook() {
		return issuedBookDAO.getIssuedBookListByUser(getCart().getUser().getId());
	}
	/* to update the cart count */
	public String manageCartLine(int cartLineId, int count) {

		Issue cartLine = cartLineDAO.get(cartLineId);
		int oldTotal = cartLine.getTotal();

		Book book = cartLine.getBook();

		// check if that much quantity is available or not
		if (book.getQuantity() < count) {
			return "result=unavailable";
		}

		// update the cart line
		cartLine.setBookCount(count);
		cartLine.setTotal(1* count);
		cartLineDAO.update(cartLine);

		// update the cart
		Cart cart = this.getCart();
		cart.setTotal(oldTotal + cartLine.getBookCount());
		cartLineDAO.updateCart(cart);

		return "result=updated";
	}

	public String addCartLine(int bookId) {
		Cart cart = this.getCart();
		String response = null;
		Issue cartLine = cartLineDAO.getByCartAndBook(cart.getId(), bookId);
		if (cartLine == null) {
			// add a new cartLine if a new book is getting added
			cartLine = new Issue();
			Book book = bookDAO.get(bookId);
			// transfer the book details to cartLine
			cartLine.setCartId(cart.getId());
			cartLine.setBook(book);
			cartLine.setBookCount(1);
			cartLine.setTotal(1);

			// insert a new cartLine
			cartLineDAO.add(cartLine);

			// update the cart
			cart.setTotal(cart.getTotal() + cartLine.getBookCount());
			cartLineDAO.updateCart(cart);

			response = "result=added";
		} else {
			// check if the cartLine has been already reached to maximum count
			if (cartLine.getBookCount()<1) {
				// call the manageCartLine method to increase the count
				response = this.manageCartLine(cartLine.getId(), cartLine.getBookCount() + 1);
			} else {
				response = "result=maximum";
			}
		}
		return response;
	}

	private Cart getCart() {
		return ((UserModel) session.getAttribute("userModel")).getCart();
	}

	public String removeCartLine(int cartLineId) {

		Issue cartLine = cartLineDAO.get(cartLineId);
		// deduct the cart
		// update the cart
		Cart cart = this.getCart();
		cart.setTotal(cart.getTotal() - cartLine.getBookCount());
		cart.setIssue_item((cart.getIssue_item() - 1));
		cartLineDAO.updateCart(cart);

		// remove the cartLine
		cartLineDAO.remove(cartLine);

		return "result=deleted";
	}

	public String validateCartLine() {
		Cart cart = this.getCart();
		List<Issue> cartLines = cartLineDAO.list(cart.getId());
		int lineCount = 0;
		int total = 0;
		String response = "result=success";
		boolean changed = false;
		Book book = null;
		for (Issue cartLine : cartLines) {
			book = cartLine.getBook();
			changed = false;

			// check if the book is active or not
			// if it is not active make the availability of cartLine as false
			if ((!book.isActive() && book.getQuantity() == 0) && cartLine.isAvailable()) {
				cartLine.setAvailable(false);
				changed = true;
				System.out.println("this is done   "+changed);
			}

			// check if the cartLine is not available
			// check whether the book is active and has at least one quantity available
			if ((book.isActive() && book.getQuantity() > 0) && !(cartLine.isAvailable())) {
				cartLine.setAvailable(true);
				changed = true;
			}

			// check if that much quantity of book is available or not
			if (cartLine.getBookCount() > book.getQuantity()) {
				cartLine.setBookCount(book.getQuantity());
				changed = true;

			}

			// changes has happened
			if (changed) {
				// update the cartLine
				cartLineDAO.update(cartLine);
				// set the result as modified
				response = "result=modified";
			}
			total += cartLine.getBookCount();
			lineCount++;
		}

		cart.setIssue_item(lineCount++);
		cart.setTotal(total);
		cartLineDAO.updateCart(cart);

		return response;
	}
	
}
