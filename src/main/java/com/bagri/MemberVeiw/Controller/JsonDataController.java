package com.bagri.MemberVeiw.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bagri.MemberVeiw.DAO.BookDAO;
import com.bagri.MemberVeiw.Entity.Book;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private BookDAO bookDAO;
	

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
	
	
	@RequestMapping("/mv/books")
	@ResponseBody
	public List<Book> getMostViewedbooks() {		
		return bookDAO.getBooksByParam("views", 5);				
	}
		
	@RequestMapping("/mp/books")
	@ResponseBody
	public List<Book> getMostPurchasedbooks() {		
		return bookDAO.getBooksByParam("purchases", 5);				
	}
	
	
	
	
}
