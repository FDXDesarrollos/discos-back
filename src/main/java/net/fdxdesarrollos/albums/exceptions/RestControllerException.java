package net.fdxdesarrollos.albums.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import net.fdxdesarrollos.albums.dto.Mensaje;

@RestControllerAdvice
public class RestControllerException {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Mensaje> handleException(Exception ex) {
		return ResponseEntity.internalServerError().body(new Mensaje(ex.getMessage()));
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Mensaje> handleCustomException(CustomException ex) {
		return ResponseEntity.status(ex.getStatus()).body(new Mensaje(ex.getMessage()));
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Mensaje> handleBadCredentialsException(BadCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje(ex.getMessage()));
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Mensaje> handleAccessDeniedException(AccessDeniedException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Mensaje(ex.getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Mensaje> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<String> mensajes = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach(err -> mensajes.add(err.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(mensajes.stream().collect(Collectors.joining(","))));
	}	
		
}
