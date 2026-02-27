package com.ProyectoPracticas.demo.domain.dtos.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/* * DTO para la actualización de un usuario existente.
 */
public class UsuarioUpdateDTO {
	
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
	
	/* * Estado activo del usuario.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El estado activo es obligatorio")
	private Integer activo;
	
	/* * Nombre del rol del usuario.
	 * Solo lo puede usar un admin.
	 * Debe ser un valor no nulo y no vacío si el usuario es admin.
	 */
	@NotBlank(message = "El nombre del rol es obligatorio (si eres admin)")
	private String nombreRol; // Solo lo puede usar un admin
	
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
	
	/* * Método para obtener el nombre del rol del usuario.
	 */
	public String getNombreRol() {
		return nombreRol;
	}
	
	/* * Método para establecer el nombre del rol del usuario.
	 */
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	
}
