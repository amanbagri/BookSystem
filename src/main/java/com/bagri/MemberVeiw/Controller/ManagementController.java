package com.bagri.MemberVeiw.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bagri.MemberVeiw.DAO.BookDAO;
import com.bagri.MemberVeiw.DAO.CategoryDAO;
import com.bagri.MemberVeiw.Entity.Book;
import com.bagri.MemberVeiw.Entity.Category;
import com.bagri.MemberVeiw.Validator.BookValidator;
import com.bagri.MemberVeiw.util.FileUtil;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping("/book")
	public ModelAndView manageBook(@RequestParam(name = "success", required = false) String success) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Book Management");
		mv.addObject("userClickManageBook", true);

		Book nBook = new Book();

		// assuming that the user is ADMIN
		// later we will fixed it based on user is SUPPLIER or ADMIN
		nBook.setLibrary_id(1);
		nBook.setActive(true);

		mv.addObject("book", nBook);

		if (success != null) {
			if (success.equals("book")) {
				mv.addObject("message", "Book submitted successfully!");
			} else if (success.equals("category")) {
				mv.addObject("message", "Category submitted successfully!");
			}
		}

		return mv;

	}

	@RequestMapping("/{id}/book")
	public ModelAndView manageBookEdit(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Book Management");
		mv.addObject("userClickManageBook", true);

		// Book nBook = new Book();
		mv.addObject("book", bookDAO.get(id));

		return mv;

	}

	@RequestMapping(value = "/book", method=RequestMethod.POST) 
	public String	managePostBook(@Valid @ModelAttribute("book") Book mBook,
			          BindingResult results, Model model, HttpServletRequest request) {
	
	// mandatory file upload check 
		if(mBook.getId() == 0) { new	BookValidator().validate(mBook, results); } 
	   else { 
		// edit check only when the file has been selected
	     if(!mBook.getFile().getOriginalFilename().equals("")) 
	             { 		new BookValidator().validate(mBook, results); 
	          } 
	       }
	
	   if(results.hasErrors()) { 
		   model.addAttribute("message","Validation fails for adding the Book!");
	     model.addAttribute("userClickManageBook",true); 
	     return "page"; 
	     }
	
	
	     if(mBook.getId() == 0 ) {
	    	 bookDAO.add(mBook); 
	    	 } 
	     else {
	          bookDAO.update(mBook); 
	          }
	
	        //upload the file 
	     if(!mBook.getFile().getOriginalFilename().equals("") ){
	          FileUtil.uploadFile(request, mBook.getFile(), mBook.getCode()); }

	return"redirect:/manage/book?success=book";

	}

	@RequestMapping(value = "/book/{id}/activation", method=RequestMethod.GET)
	@ResponseBody
	public String managePostBookActivation(@PathVariable int id) {		
		Book book = bookDAO.get(id);
		boolean isActive = book.isActive();
		book.setActive(!isActive);
		bookDAO.update(book);		
		return (isActive)? "Book Dectivated Successfully!": "Book Activated Successfully";
	}

	@RequestMapping(value = "/category", method=RequestMethod.POST)
	public String managePostCategory(@ModelAttribute("category") Category mCategory, HttpServletRequest request) {					
		categoryDAO.add(mCategory);		
		return "redirect:" + request.getHeader("Referer") + "?success=category";
	}

	@ModelAttribute("categories") 
	public List<Category> modelCategories() {
		return categoryDAO.list();
	}

	@ModelAttribute("category")
	public Category modelCategory() {
		return new Category();
	}

}
