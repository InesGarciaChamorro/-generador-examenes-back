package com.ProyectoPracticas.demo.presentation.exceptions;

/* * Excepción personalizada para indicar que un recurso no ha sido encontrado.
 * Se lanza cuando se intenta acceder a un recurso que no existe en la base de datos o que no está activo.
 */
public class NotFoundException extends RuntimeException {
    /* * Constructor sin argumentos para la excepción.
     */
    public NotFoundException() {
        super();
    }

    /* * Constructor con mensaje personalizado para la excepción.
     * @param message El mensaje de error que describe la causa de la excepción.
     */
    public NotFoundException(String message) {
        super(message);
    }
    
    /* * Constructor con mensaje personalizado y causa para la excepción.
     * @param message El mensaje de error que describe la causa de la excepción.
     * @param cause La causa original de la excepción (otra excepción).
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
