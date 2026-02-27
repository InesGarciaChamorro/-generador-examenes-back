package com.ProyectoPracticas.demo.domain.dtos.roles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/* * DTO para la eliminación de un rol existente.
 */
public class RolDeleteDTO {
	
	/* * Id del rol.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotNull(message = "El id del rol no puede ser nulo")
	private Long idRol;
	
	/* * Nombre del rol.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El nombre del rol es obligatorio")
	private String nombreRol;
	
	
	/* * Getters y setters para los campos del DTO.
	 */
	
	/* * Método para obtener el id del rol.
	 */
	public Long getIdRol() {
		return idRol;
	}
	
	/* * Método para establecer el id del rol.
	 */
	public void setIdRol(Long idRol) {
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
}
