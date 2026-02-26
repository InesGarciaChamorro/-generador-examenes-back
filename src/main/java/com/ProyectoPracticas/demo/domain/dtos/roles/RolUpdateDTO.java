package com.ProyectoPracticas.demo.domain.dtos.roles;

import jakarta.validation.constraints.NotBlank;

/* * DTO para la actualización de un rol existente.
 */
public class RolUpdateDTO {
	/* * El nombre del rol a actualizar.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El nombre del rol es obligatorio")
	private String nombreRol;
	
	/* * El estado activo del rol.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El estado activo es obligatorio")
	private Integer activo;
	
	
	/* * Getters y setters para los campos del DTO.
	 */
	/* * Método para obtener el nombre del rol.
	 */
	public String getNombreRol() {
		return nombreRol;
	}
	
	/* * Método para establecer el nombre del rol.
	 */
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	
	/* * Método para obtener el estado activo del rol.
	 */
	public Integer getActivo() {
		return activo;
	}
	
	/* * Método para establecer el estado activo del rol.
	 */
	public void setActivo(Integer activo) {
		this.activo = activo;
	}
}
