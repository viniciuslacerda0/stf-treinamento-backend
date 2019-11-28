package com.money.handler;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.money.exception.CategoriaNaoEncontradaException;
import com.money.exception.CategoriaRepetidaException;
import com.money.exception.PessoaNaoEncontradaException;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest wr){
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario,mensagemDev));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(),HttpStatus.NOT_FOUND, wr);
	}
	
	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	public ResponseEntity<Object> handleCategoriaNaoEncontradaException(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ExceptionHandler(CategoriaRepetidaException.class)
	public ResponseEntity<Object> handleCategoriaRepetidaException(){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@ExceptionHandler(PessoaNaoEncontradaException.class)
	public ResponseEntity<Object> handlePessoaNaoEncontradaException(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}	
	
	public static class Erro {
		private String mensagemUsuario;
		private String mensagemDev;
		
		public Erro(String mensagemUsuario, String mensagemDev) {
			this.setMensagemUsuario(mensagemUsuario);
			this.setMensagemDev(mensagemDev);
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}

		public String getMensagemDev() {
			return mensagemDev;
		}

		public void setMensagemDev(String mensagemDev) {
			this.mensagemDev = mensagemDev;
		}
		
	}
}
