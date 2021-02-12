package com.bagri.BookSystem.Controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bagri.BookSystem.DAO.IssuedBookDAO;
import com.bagri.BookSystem.DAO.ReserveBookDAO;
import com.bagri.BookSystem.Entity.IssuedBook;
import com.bagri.BookSystem.Entity.ReserveBook;

@Controller
@RequestMapping("/issuedbook")
public class IssuedBookController {
	@Autowired
	private IssuedBookDAO issuedBookDAO;

	@Autowired
	private ReserveBookDAO reserveBookDAO;

	
	@RequestMapping("/list")
	public ModelAndView showAllIssuedBooks() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All IssuedBooks");
		mv.addObject("userClickIssuedBooks", true);
		mv.addObject("issuedBook", issuedBookDAO.getIssuedBookList());
		return mv;
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		IssuedBook theCustomer = new IssuedBook();
		
		theModel.addAttribute("issuedBook", theCustomer);
		
		return "IssueBook-Form";
	}
	@PostMapping("/save")
	public String saveCustomer(@ModelAttribute("customer") IssuedBook theCustomer) {
		
		// save the customer using our service
		issuedBookDAO.save(theCustomer);	
		
		return "redirect:/issuedbook/list";
	}
	@RequestMapping("/showFormForIssueReserveBookByUser")
	public String showFormForIssueReserveBookByUser(@RequestParam("issuedBookid") int theId,
									Model theModel) {
		 ModelAndView mv = new ModelAndView("page");
			mv.addObject("title", "ReIssueBook");
			mv.addObject("userClickReIssueBook", true);
			ReserveBook reserveBook = reserveBookDAO.getReserveBookId(theId);
			IssuedBook issuedBook = new IssuedBook();
			issuedBook.setBook(reserveBook.getBook());
			issuedBook.setOrderCount(reserveBook.getOrderCount());
			issuedBook.setOrderDate(new Date());
			issuedBook.setUser(reserveBook.getUser());
			reserveBookDAO.deleteReserveBook(theId);
			issuedBookDAO.save(issuedBook);
			return "redirect:/issuedbook/list";
	}
	
	@RequestMapping("/showFormForReIssueBook")
	public ModelAndView showFormForReIssueBook(@RequestParam("issuedBookid") int theId,
									Model theModel) {
		
		/*
		 * // get the customer from our service IssuedBook theCustomer =
		 * issuedBookDAO.getIssuedBookId(theId); // set customer as a model attribute to
		 * pre-populate the form theModel.addAttribute("issuedBook", theCustomer);
		 * 
		 * // send over to our form return "IssueBook-Form";
		 */
		 ModelAndView mv = new ModelAndView("page");
			mv.addObject("title", "ReIssueBook");
			mv.addObject("userClickReIssueBook", true);
			mv.addObject("issuedBook", issuedBookDAO.getIssuedBookId(theId));
			return mv;
	}
	@GetMapping("/returnIssuedBook")
	public String deleteCustomer(@RequestParam("issuedBookid") int theId) {
		
		// delete the customer
		issuedBookDAO.deleteIssuedBook(theId);
		return "redirect:/issuedbook/list";
	}
	@GetMapping("/search")
    public ModelAndView searchIssuedBookByUser(@RequestParam("userid") int userid,
                                    Model theModel) {

		/*
		 * // search customers from the service List<IssuedBook> theCustomers =
		 * issuedBookDAO.searchIssuedBookByUser(userid);
		 * 
		 * // add the customers to the model theModel.addAttribute("issuedBook",
		 * theCustomers);
		 * 
		 * return "IssuedBookByUser";
		 */ 
        ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "IssuedBooksByUser");
		mv.addObject("userClickIssuedBookByUser", true);
		mv.addObject("issuedBook", issuedBookDAO.searchIssuedBookByUser(userid));
		return mv;
    }
	@GetMapping("/searchReserveBookByUser")
    public ModelAndView searchReserveBookByUser(@RequestParam("userid") int userid,
                                    Model theModel) {

        ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "IssuedBooksByUser");
		mv.addObject("userClickReserveBookByUser", true);
		mv.addObject("issuedBook", issuedBookDAO.searchReserveBookByUser(userid));
		return mv;
    }
}
