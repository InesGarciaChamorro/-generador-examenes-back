package com.ProyectoPracticas.demo.domain.dtos.usuariosRoles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO de respuesta para la creación de una relación entre un usuario y un rol.
 */
public class UsuarioRolCreateResponseDTO {
	
	/** El id del usuario al que se le asignó el rol. */
	@NotNull(message = "El id del usuario no puede ser nulo")
	private Long idUsuario;
	
	/** El id del rol que se asignó al usuario. */
	@NotNull(message = "El id del rol no puede ser nulo")
	private Long idRol;
	
	
	/* *Los getters y setters para los campos de la clase. * */
	
	/* Getter para el id del rol. * */
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	/* Setter para el id del rol. * */
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/* Getter para el id del rol. * */
	public Long getIdRol() {
		return idRol;
	}
	
	/* Setter para el id del rol. * */
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	
}
