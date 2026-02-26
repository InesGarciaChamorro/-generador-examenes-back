package com.ProyectoPracticas.demo.domain.dtos.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/* * DTO para la creación de un nuevo usuario.
 */

public class UsuarioCreateDTO {
	
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
	
	
	/* * Contraseña del usuario.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "La contraseña es obligatoria")
	private String contrasenhaUsuario;
	
	/* * Getters y setters para los campos del DTO.
 */
	
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
	
	/* * Método para obtener la contraseña del usuario.
	 */
	public String getContrasenhaUsuario() {
		return contrasenhaUsuario;
	}
	
	/* * Método para establecer la contraseña del usuario.
	 */
	public void setContrasenhaUsuario(String contrasenhaUsuario) {
		this.contrasenhaUsuario = contrasenhaUsuario;
	}
	
	
}
