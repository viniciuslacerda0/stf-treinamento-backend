package com.money.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.money.exception.CategoriaNaoEncontradaException;

public class ExceptionsHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	public ResponseEntity<Object> HandleCategoriaNaoEncontradaException(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
