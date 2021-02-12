package com.bagri.BookSystem.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bagri.BookSystem.Entity.Cart;
import com.bagri.BookSystem.Entity.Issue;
import com.bagri.BookSystem.Entity.ReserveBook;


@Repository("issueDAO")
@Transactional
public class IssueDAOImpl implements IssueDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Issue> list(int cartId) {
		String query = "FROM Issue WHERE cartId = :cartId";
		return sessionFactory.getCurrentSession()
								.createQuery(query, Issue.class)
									.setParameter("cartId", cartId)
										.getResultList();		
	}

	@Override
	public Issue get(int id) {
		return sessionFactory.getCurrentSession().get(Issue.class, Integer.valueOf(id));
	}

	@Override
	public boolean add(Issue cartLine) {
		try {
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Issue cartLine) {
		try {
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean remove(Issue cartLine) {
		try {			
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		}catch(Exception ex) {
			return false;
		}		
	}

	@Override
	public Issue getByCartAndBook(int cartId, int bookId) {
		String query = "FROM Issue WHERE cartId = :cartId AND book.id = :bookId";
		try {
			
			return sessionFactory.getCurrentSession()
									.createQuery(query,Issue.class)
										.setParameter("cartId", cartId)
										.setParameter("bookId", bookId)
											.getSingleResult();
			
		}catch(Exception ex) {
			return null;	
		}
		
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {			
			sessionFactory.getCurrentSession().update(cart);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public List<Issue> listAvailable(int cartId) {
		String query = "FROM  Issue WHERE cartId = :cartId AND available = :available";
		return sessionFactory.getCurrentSession()
								.createQuery(query, Issue.class)
									.setParameter("cartId", cartId)
									.setParameter("available", true)
										.getResultList();
	}

	@Override
	public boolean addOrderDetail(ReserveBook orderDetail) {
		try {			
			sessionFactory.getCurrentSession().persist(orderDetail);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

}
