package com.first.demo.errorhandler;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.first.demo.error.Errormsg;
import com.first.demo.exception.CustomerNotFound;

@RestControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(value = { CustomerNotFound.class })
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  public Errormsg handleResourceNotFoundException(CustomerNotFound ex) {
	    return new Errormsg(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	  }
	 
	 @ExceptionHandler(value = {DataAccessResourceFailureException.class})
	 @ResponseStatus(code = HttpStatus.NOT_FOUND)
	 public Errormsg handleDataAccessResourceFailureException(DataAccessResourceFailureException ex)
	 {
		return new Errormsg(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		 
	 }
}
