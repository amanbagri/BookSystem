package com.bagri.BookSystem.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.query.Query;

import com.bagri.BookSystem.Entity.IssuedBook;
import com.bagri.BookSystem.Entity.ReserveBook;

@Repository("issuedBookDAO")
@Transactional
public class IssuedBookDAOImpl implements IssuedBookDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<IssuedBook> getIssuedBookList() {
		return sessionFactory.getCurrentSession().createQuery("FROM IssuedBook", IssuedBook.class).getResultList();
	}

	@Override
	public IssuedBook getIssuedBookId(int theId) {
		try {			
			return sessionFactory
					.getCurrentSession()
						.get(IssuedBook.class,Integer.valueOf(theId));			
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}
		return null;
	}

	@Override
	public void save(IssuedBook theCustomer) {
		sessionFactory.getCurrentSession().saveOrUpdate(theCustomer);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteIssuedBook(int theId) {
       
		
		// delete object with primary key
		Query theQuery = 
				 sessionFactory.getCurrentSession().createQuery("delete from IssuedBook where id=:isuuedBookid");
		theQuery.setParameter("isuuedBookid", theId);
		
		theQuery.executeUpdate();	
		
	}

	@Override
	public List<IssuedBook> searchIssuedBookByUser(int userid) {
        
		Query<IssuedBook> theQuery = null;
        if (userid != 0) {
            theQuery = sessionFactory.getCurrentSession().createQuery("from IssuedBook where user_id=:userid", IssuedBook.class);
            theQuery.setParameter("userid",userid);

        }
        else {
            theQuery = sessionFactory.getCurrentSession().createQuery("from IsuuedBook", IssuedBook.class);            
        }
        
        // execute query and get result list
        List<IssuedBook> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
	}

	@Override
	public List<ReserveBook> searchReserveBookByUser(int userid) {
		Query<ReserveBook> theQuery = null;
        if (userid != 0) {
            theQuery = sessionFactory.getCurrentSession().createQuery("from ReserveBook where user_id=:userid", ReserveBook.class);
            theQuery.setParameter("userid",userid);

        }
        else {
            theQuery = sessionFactory.getCurrentSession().createQuery("from ReserveBook", ReserveBook.class);            
        }
        
        // execute query and get result list
        List<ReserveBook> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
	}

	@Override
	public List<IssuedBook> getIssuedBookListByUser(int userId) {
		String query = "FROM IssuedBook WHERE user_id = :id";
		return sessionFactory.getCurrentSession()
								.createQuery(query, IssuedBook.class)
									.setParameter("id", userId)
										.getResultList();
	}

}
