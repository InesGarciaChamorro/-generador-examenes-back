package com.ProyectoPracticas.demo.domain.dtos.usuariosRoles;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para eliminar una relación entre un usuario y un rol.
 */
public class UsuarioRolDeleteDTO {
	
	/** El id del usuario al que se le asignó el rol. */
	@NotBlank(message = "El id del usuario no puede estar vacío")
	private Long idUsuario;
	
	/** El id del rol que se asignó al usuario. */
	@NotBlank(message = "El id del rol no puede estar vacío")
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
