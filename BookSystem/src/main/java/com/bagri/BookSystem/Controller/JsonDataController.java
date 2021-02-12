package com.bagri.BookSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bagri.BookSystem.DAO.BookDAO;
import com.bagri.BookSystem.DAO.ReserveBookDAO;
import com.bagri.BookSystem.Entity.Book;
import com.bagri.BookSystem.Entity.ReserveBook;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private BookDAO bookDAO;
	

	@Autowired
	private ReserveBookDAO issuedBookDAO;
	

	@RequestMapping("/admin/all/books")
	@ResponseBody
	public List<Book> getAllBooksList() {		
		return bookDAO.list();
				
	}	
	
	
	@RequestMapping("/all/books")
	@ResponseBody
	public List<Book> getAllBooks() {
		
		return bookDAO.listActiveBooks();
				
	}
	
	@RequestMapping("/category/{id}/books")
	@ResponseBody
	public List<Book> getBooksByCategory(@PathVariable int id) {
		
		return bookDAO.listActiveBooksByCategory(id);
				
	}

	@RequestMapping("/cart/issued/books")
	@ResponseBody
	public List<ReserveBook> getAllIssuedBooks(int id) {		
		return issuedBookDAO.getIssuedBookList(id);
				
	}	
	
	
	
	@RequestMapping("/mv/books")
	@ResponseBody
	public List<Book> getMostViewedbooks() {		
		return bookDAO.getBooksByParam("veiw", 5);				
	}
		
	@RequestMapping("/mp/books")
	@ResponseBody
	public List<Book> getMostPurchasedbooks() {		
		return bookDAO.getBooksByParam("issued", 5);				
	}
	
	
	
	
}
