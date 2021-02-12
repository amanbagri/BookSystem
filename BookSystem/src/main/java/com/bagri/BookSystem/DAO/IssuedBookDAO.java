package com.bagri.BookSystem.DAO;

import java.util.List;

import com.bagri.BookSystem.Entity.IssuedBook;
import com.bagri.BookSystem.Entity.ReserveBook;

public interface IssuedBookDAO {
	public List<IssuedBook> getIssuedBookList();
	public List<IssuedBook> getIssuedBookListByUser(int userId);

	public IssuedBook getIssuedBookId(int theId);

	public void save(IssuedBook theCustomer);

	public void deleteIssuedBook(int theId);

	public List<IssuedBook> searchIssuedBookByUser(int userid);

	public List<ReserveBook> searchReserveBookByUser(int userid);

}
