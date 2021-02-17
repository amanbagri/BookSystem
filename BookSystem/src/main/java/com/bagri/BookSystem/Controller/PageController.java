package com.bagri.BookSystem.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bagri.BookSystem.DAO.BookDAO;
import com.bagri.BookSystem.DAO.CategoryDAO;
import com.bagri.BookSystem.Entity.Book;
import com.bagri.BookSystem.Entity.Category;
import com.bagri.BookSystem.Exception.BookNotFoundException;

@Controller
public class PageController {


	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private BookDAO bookDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("userClickOnHome", true);
		return mv;

	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickOnAbout", true);
		return mv;

	}

	@RequestMapping(value = "/show/all/books")
	public ModelAndView showAllBooks() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Books");

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClickAllBooks", true);
		return mv;
	}

	@RequestMapping(value = "/show/category/{id}/books")
	public ModelAndView showCategoryBooks(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");

		// categoryDAO to fetch a single category
		Category category = null;

		category = categoryDAO.get(id);

		mv.addObject("title", category.getName());

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		// passing the single category object
		mv.addObject("category", category);

		mv.addObject("userClickCategoryBooks", true);
		return mv;
	}

	// Viewing a single book

	@RequestMapping(value = "/show/{id}/book")
	public ModelAndView showSingleBook(@PathVariable int id) throws BookNotFoundException {

		ModelAndView mv = new ModelAndView("page");

		Book book = bookDAO.get(id);

		if (book == null)
			throw new BookNotFoundException();

		// update the view count
		// book.setVeiw(book.getVeiw() + 1);
		// bookDAO.update(book); //---------------------------

		mv.addObject("title", book.getName());
		mv.addObject("book", book);

		mv.addObject("userClickShowBook", true);

		return mv;

	}

	@RequestMapping(value = "/membership")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");


		return mv;
	}

	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam(name="error", required = false)	String error,
			@RequestParam(name="logout", required = false) String logout) {
		ModelAndView mv= new ModelAndView("login");
		mv.addObject("title", "Login");
		mv.addObject("userClickLogin", true);
		if(error!=null) {
			mv.addObject("message", "Username and Password is invalid!");
		}
		if(logout!=null) {
			mv.addObject("logout", "You have logged out successfully!");
		}
		return mv;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// Invalidates HTTP Session, then unbinds any objects bound to it.
	    // Removes the authentication from securitycontext 		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		
		return "redirect:/login?logout";
	}	
	
	
	@RequestMapping(value="/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");		
		mv.addObject("errorTitle", "Aha! Caught You.");		
		mv.addObject("errorDescription", "You are not authorized to view this page!");		
		mv.addObject("title", "403 Access Denied");		
		return mv;
	}	
		
	

}
