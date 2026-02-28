package com.ProyectoPracticas.demo.presentation.exceptions;

/* * Excepción personalizada para indicar que se ha intentado crear un recurso duplicado.
 * Se lanza cuando se detecta que un recurso con los mismos atributos ya existe en la base de datos.
 */
public class DuplicateException extends RuntimeException {

    /* * Constructor sin argumentos para la excepción.
     */
    public DuplicateException() {
        super();
    }

    /* * Constructor con mensaje personalizado para la excepción.
     * @param message El mensaje de error que describe la causa de la excepción.
     */
    public DuplicateException(String message) {
        super(message);
    }

    /* * Constructor con mensaje personalizado y causa para la excepción.
     * @param message El mensaje de error que describe la causa de la excepción.
     * @param cause La causa original de la excepción (otra excepción).
     */
    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
