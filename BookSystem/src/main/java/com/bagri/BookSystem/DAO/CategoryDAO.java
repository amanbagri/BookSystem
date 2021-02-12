package com.bagri.BookSystem.DAO;

import java.util.List;

import com.bagri.BookSystem.Entity.Category;


public interface CategoryDAO {

	List<Category> list();

	Category get(int id);
	
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
 }
