package com.bagri.MemberVeiw.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bagri.MemberVeiw.Entity.Book;


@Repository("bookDAO")
@Transactional
public class BookDAOImpl implements BookDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Book get(int bookId) {
		try {			
			return sessionFactory
					.getCurrentSession()
						.get(Book.class,Integer.valueOf(bookId));			
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}
		return null;
	}

	@Override
	public List<Book> list() {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Book" , Book.class)
						.getResultList();
	}

	@Override
	public boolean add(Book book) {
		try {			
			sessionFactory
					.getCurrentSession()
						.persist(book);
			return true;
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}		
		return false;
	}

	@Override
	public boolean update(Book book) {
		try {			
			sessionFactory
					.getCurrentSession()
						.update(book);
			return true;
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}		
		return false;	
	}

	@Override
	public boolean delete(Book book) {
try {
			
			book.setActive(false);
			// call the update method
			return this.update(book);
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}		
		return false;			
	}

	@Override
	public List<Book> listActiveBooks() {
		String selectActiveBooks = "FROM Book WHERE active = :active";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveBooks, Book.class)
						.setParameter("active", true)
							.getResultList();
	}

	@Override
	public List<Book> listActiveBooksByCategory(int categoryId) {
		String selectActiveBooksByCategory = "FROM Book WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveBooksByCategory, Book.class)
						.setParameter("active", true)
						.setParameter("categoryId",categoryId)
							.getResultList();
	}

	@Override
	public List<Book> getLatestActiveBooks(int count) {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Book WHERE active = :active ORDER BY id", Book.class)
						.setParameter("active", true)
							.setFirstResult(0)
							.setMaxResults(count)
								.getResultList();					
	}

	@Override
	public List<Book> getBooksByParam(String param, int count) {
		
		String query = "FROM Book WHERE active = true ORDER BY " + param + " DESC";
		
		return sessionFactory
					.getCurrentSession()
					.createQuery(query,Book.class)
					.setFirstResult(0)
					.setMaxResults(count)
					.getResultList();
					
		
	}

}
