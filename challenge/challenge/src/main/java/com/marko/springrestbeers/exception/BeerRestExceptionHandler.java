package com.marko.springrestbeers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BeerRestExceptionHandler {

	//exception handling for beer not found
	@ExceptionHandler
	public ResponseEntity<BeerNotFoundResponse>beerHandler(BeerNotFoundException exc){
		BeerNotFoundResponse error = new BeerNotFoundResponse();
		error.setMessage(exc.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	//exception handling for any exception
	@ExceptionHandler
	public ResponseEntity<BeerNotFoundResponse>beerHandler(Exception exc){
		BeerNotFoundResponse error = new BeerNotFoundResponse();
		error.setMessage("Beer not found!");
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	
}
