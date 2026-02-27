package com.ProyectoPracticas.demo.domain.dtos.usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/* * DTO para la eliminación de un usuario existente.
 */
public class UsuarioDeleteDTO {
	
	/* *Id del usuario.
	 * Debe ser valor nulo y no vacío
	 */
	@NotNull(message = "El id de usuario no puede ser nulo")
	private Long idUsuario;
	
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
	
	
	/* * Getters y setters para los campos del DTO.
	 */
	/* * Método para obtener el id del usuario.
	 */
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	/* * Método para establecer el id del usuario.
	 */
	public void setIdUsuario(Long idUsuario) {
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
}
