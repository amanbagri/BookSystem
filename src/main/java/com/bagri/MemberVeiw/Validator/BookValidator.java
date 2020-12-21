package com.bagri.MemberVeiw.Validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bagri.MemberVeiw.Entity.Book;

public class BookValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Book book = (Book) target;
		if(book.getFile() == null || book.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null, "Please select a file to upload!");
			return;
		}
		if(! (book.getFile().getContentType().equals("image/jpeg") || 
				book.getFile().getContentType().equals("image/png")) ||
				book.getFile().getContentType().equals("image/gif")
			 )
			{
				errors.rejectValue("file", null, "Please select an image file to upload!");
				return;	
			}

	}

}
