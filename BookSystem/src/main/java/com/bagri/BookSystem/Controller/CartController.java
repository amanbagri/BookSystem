package com.bagri.BookSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bagri.BookSystem.Service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {


	@Autowired
	private CartService cartService;

	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Shopping Cart");
		mv.addObject("userClickShowCart", true);

		if (result != null) {
			switch (result) {
			case "added":
				mv.addObject("message", "Book has been successfully added inside cart!");
				cartService.validateCartLine();
				break;
			case "unavailable":
				mv.addObject("message", "Book quantity is not available!");
				break;
			case "updated":
				mv.addObject("message", "Cart has been updated successfully!");
				cartService.validateCartLine();
				break;
			case "modified":
				mv.addObject("message", "One or more items inside cart has been modified!");
				break;
			case "maximum":
				mv.addObject("message", "Maximum limit for the item has been reached!");
				break;
			case "deleted":
				mv.addObject("message", "CartLine has been successfully removed!");
				break;

			}
		} else {
			String response = cartService.validateCartLine();
			if (response.equals("result=modified")) {
				mv.addObject("message", "One or more items inside cart has been modified!");
			}
		}

		mv.addObject("cartLines", cartService.getCartLines());
		return mv;

	}

	@RequestMapping("/{cartLineId}/update")
	public String udpateCartLine(@PathVariable int cartLineId, @RequestParam int count) {
		String response = cartService.manageCartLine(cartLineId, count);
		return "redirect:/cart/show?" + response;
	}

	@RequestMapping("/add/{bookId}/book")
	public String addCartLine(@PathVariable int bookId) {
		String response = cartService.addCartLine(bookId);
		return "redirect:/cart/show?" + response;
	}

	@RequestMapping("/{cartLineId}/remove")
	public String removeCartLine(@PathVariable int cartLineId) {
		String response = cartService.removeCartLine(cartLineId);
		return "redirect:/cart/show?" + response;
	}

	/*
	 * after validating it redirect to checkout if result received is success
	 * proceed to checkout else display the message to the user about the changes in
	 * cart page
	 */

	@RequestMapping("/validate")
	public String validateCart() {
		String response = cartService.validateCartLine();
		if (!response.equals("result=success")) {
			return "redirect:/cart/show?" + response;
		} else {
			return "redirect:/cart/checkout";
		}
	}
	
	@RequestMapping("/reserve/books")
	public ModelAndView showAllBooks() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Books");

		// passing the list of categories
		//mv.addObject("IssusedBook", issuedBookItemDAO.getIssuedBookList(0));

		mv.addObject("userClickMyIssuedBooks", true);
		mv.addObject("issuedBook", cartService.getReserveBook());

		//mv.addObject("issuedBookItem", cartService.getIssuedBookItem());
		return mv;
	}
	@RequestMapping("/issued/books")
	public ModelAndView showAllIssuedBooks() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Books");

		// passing the list of categories
		//mv.addObject("IssusedBook", issuedBookItemDAO.getIssuedBookList(0));

		mv.addObject("userClickMyIssuedBooks", true);
		mv.addObject("issuedBook", cartService.getIssuedBook());

		//mv.addObject("issuedBookItem", cartService.getIssuedBookItem());
		return mv;
	}

}
