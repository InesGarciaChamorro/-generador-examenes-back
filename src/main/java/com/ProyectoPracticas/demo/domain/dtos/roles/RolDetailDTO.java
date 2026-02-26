package com.ProyectoPracticas.demo.domain.dtos.roles;

import jakarta.validation.constraints.NotBlank;

/* * DTO para la representación detallada de un rol.
 */
public class RolDetailDTO {
	
	/* * Id único del rol.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El id del rol es obligatorio")
	private String idRol;
	
	/* * Nombre del rol.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El nombre del rol es obligatorio")
	private String nombreRol;
	
	/* * Estado activo del rol.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El estado activo es obligatorio")
	private int activo;
	
	/* * Getters y setters para los campos del DTO.
	 */
	
	/* * Método para obtener el id del rol.
	 */
	public String getIdRol() {
		return idRol;
	}
	
	/* * Método para establecer el id del rol.
	 */
	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}
	
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
	public int getActivo() {
		return activo;
	}
	
	/* * Método para establecer el estado activo del rol.
	 */
	public void setActivo(int activo) {
		this.activo = activo;
	}
}
