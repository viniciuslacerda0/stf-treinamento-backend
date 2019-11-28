package com.money.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.money.exception.CategoriaNaoEncontradaException;
import com.money.exception.NaoExisteCategoriaException;

public class ExceptionsHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	public ResponseEntity<Object> HandleCategoriaNaoEncontradaException(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ExceptionHandler(NaoExisteCategoriaException.class)
	public ResponseEntity<Object> HandleNaoExisteCategoriaException(){
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
