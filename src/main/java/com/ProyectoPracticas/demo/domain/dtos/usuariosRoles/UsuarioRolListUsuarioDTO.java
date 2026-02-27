package com.ProyectoPracticas.demo.domain.dtos.usuariosRoles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * DTO para mostrar el nombre de usuario y su estado activo en la lista de usuarios con roles.
 * Este DTO se utiliza para mostrar información básica del usuario en la lista de usuarios con roles.
 */
public class UsuarioRolListUsuarioDTO {
	
	/**
	 * El nombre de usuario del usuario.
	 */
	@NotBlank(message = "El nombre de usuario no puede estar vacío")
	private String nombreUsuario;
	
	/**
	 * El estado activo del usuario.
	 */
	@NotNull(message = "El estado activo no puede ser nulo")
	private int activo;
	
	
	
	/* *Getters y setters */
	
	/**
	 * Obtiene el estado activo del usuario.
	 * @return El estado activo del usuario.
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	
	/**
	 * Establece el estado activo del usuario.
	 * @param nombreUsuario El estado activo del usuario a establecer.
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	/**
	 * Obtiene el estado activo del usuario.
	 * @return El estado activo del usuario.
	 */
	public int getActivo() {
		return activo;
	}
	
	/**
	 * Establece el estado activo del usuario.
	 * @param activo El estado activo del usuario a establecer.
	 */
	public void setActivo(int activo) {
		this.activo = activo;
	}
	
}
