package com.ProyectoPracticas.demo.domain.dtos.roles;

import jakarta.validation.constraints.NotBlank;

/* * DTO para la creación de un nuevo rol.
 */
public class RolCreateDTO {
	/* * Nombre del rol.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El nombre del rol es obligatorio")
	private String nombreRol;
	
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
}
