package com.ProyectoPracticas.demo.presentation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* * Controlador de excepciones global para manejar errores de manera centralizada en la aplicación.
 * Proporciona métodos para manejar diferentes tipos de excepciones y devolver respuestas HTTP adecuadas.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /* * Maneja las excepciones de tipo NotFoundException, devolviendo un estado HTTP 404 con el mensaje de error.
     * @param ex La excepción NotFoundException que se ha lanzado.
     * @return ResponseEntity con el estado HTTP 404 y el mensaje de error.
     */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFound(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

    /* * Maneja las excepciones de tipo DuplicateException, devolviendo un estado HTTP 409 con el mensaje de error.
     * @param ex La excepción DuplicateException que se ha lanzado.
     * @return ResponseEntity con el estado HTTP 409 y el mensaje de error.
     */
	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<String> handleDuplicate(DuplicateException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}

    /* * Maneja cualquier otra excepción no específica, devolviendo un estado HTTP 500 con el mensaje de error.
     * @param ex La excepción genérica que se ha lanzado.
     * @return ResponseEntity con el estado HTTP 500 y el mensaje de error.
     */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneric(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}

}
