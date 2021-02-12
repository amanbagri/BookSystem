package com.bagri.BookSystem.DAO;

import java.util.List;

import com.bagri.BookSystem.Entity.Book;


public interface BookDAO {
	Book get(int bookId);
	List<Book> list();	
	boolean add(Book book);
	boolean update(Book book);
	boolean delete(Book book);
	
	// business methods
		List<Book> listActiveBooks();	
		List<Book> listActiveBooksByCategory(int categoryId);
		List<Book> getLatestActiveBooks(int count);
		List<Book> getBooksByParam(String string, int i);

}
