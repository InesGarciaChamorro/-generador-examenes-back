package com.ProyectoPracticas.demo.usuarios.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/* * DTO para la representación detallada de un usuario.
 */

public class UsuarioDetailDTO {
	
	/* * Id único del usuario.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El id de usuario es obligatorio")
	private String idUsuario;
	
	/* * Nombre del usuario.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El nombre es obligatorio")
	private String nombreUsuario;
	
	/* * Apellidos del usuario.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "Los apellidos son obligatorios")
	private String apellidoUsuario;
	
	/* * Correo electrónico del usuario.
	 * Debe ser un valor no nulo, no vacío y con formato de correo electrónico válido.
	 */
	@Email(message = "El correo electrónico no es válido")
	@NotBlank(message = "El correo electrónico es obligatorio")
	private String correoUsuario;
	
	/* * Estado activo del usuario.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El estado activo es obligatorio")
	private Integer activo;
	
	
	/* * Getters y setters para los campos del DTO.
	 */
	/* * Método para obtener el id del usuario.
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	
	/* * Método para establecer el id del usuario.
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/* * Método para obtener el nombre del usuario.
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	/* * Método para establecer el nombre del usuario.
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	/* * Método para obtener los apellidos del usuario.
	 */
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}
	
	/* * Método para establecer los apellidos del usuario.
	 */
	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}
	
	/* * Método para obtener el correo electrónico del usuario.
	 */
	public String getCorreoUsuario() {
		return correoUsuario;
	}
	
	/* * Método para establecer el correo electrónico del usuario.
	 */
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}
	
	/* * Método para obtener el estado activo del usuario.
	 */
	public Integer getActivo() {
		return activo;
	}
	
	/* * Método para establecer el estado activo del usuario.
	 */
	public void setActivo(Integer activo) {
		this.activo = activo;
	}

}
