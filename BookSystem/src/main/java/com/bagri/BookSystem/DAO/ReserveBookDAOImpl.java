package com.bagri.BookSystem.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bagri.BookSystem.Entity.ReserveBook;


@Repository("reserveBookDAO")
@Transactional
public class ReserveBookDAOImpl implements ReserveBookDAO {
   

	@Autowired
	private SessionFactory sessionFactory;
	
	

	@Override
	public List<ReserveBook> getIssuedBookList(int id) {
		String query = "FROM ReserveBook WHERE user_id = :id";
		return sessionFactory.getCurrentSession()
								.createQuery(query, ReserveBook.class)
									.setParameter("id", id)
										.getResultList();		
	}



	/*
	 * @Override public List<IssuedBookItem> getIssuedBookItemList(int orderDetail)
	 * { String query = "FROM IssuedBookItem WHERE orderDetail = :orderDetail";
	 * return sessionFactory.getCurrentSession() .createQuery(query,
	 * IssuedBookItem.class) .setParameter("orderDetail", orderDetail)
	 * .getResultList(); }
	 */


	@Override
	public boolean update(ReserveBook reserveBook) {
		try {
			sessionFactory.getCurrentSession().update(reserveBook);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}



	@Override
	public boolean remove(ReserveBook reserveBook) {
		try {			
			sessionFactory.getCurrentSession().delete(reserveBook);
			return true;
		}catch(Exception ex) {
			return false;
		}		
	}



	@Override
	public ReserveBook getReserveBookId(int theId) {
		try {			
			return sessionFactory
					.getCurrentSession()
						.get(ReserveBook.class,Integer.valueOf(theId));			
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}
		return null;
	}






	@SuppressWarnings("rawtypes")
	@Override
	public void deleteReserveBook(int theId) {
		// delete object with primary key
		Query theQuery = 
				 sessionFactory.getCurrentSession().createQuery("delete from ReserveBook where id=:isuuedBookid");
		theQuery.setParameter("isuuedBookid", theId);
		
		theQuery.executeUpdate();	
		
		
	}

}
