package com.bagri.BookSystem.DAO;

import java.util.List;

import com.bagri.BookSystem.Entity.ReserveBook;


public interface ReserveBookDAO {
	
	public List<ReserveBook> getIssuedBookList(int issuedBookId);
	//public List<IssuedBookItem> getIssuedBookItemList(int issuedBookId);
	//public Issue get(int id);	
	//public boolean add(Issue cartLine);
	 public boolean update(ReserveBook reserveBook);
	 public boolean remove(ReserveBook reserveBook);
	 
	 public ReserveBook getReserveBookId(int theId);

		public void deleteReserveBook(int theId);

}
