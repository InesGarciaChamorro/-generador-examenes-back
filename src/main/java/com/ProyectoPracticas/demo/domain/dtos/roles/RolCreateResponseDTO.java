package com.ProyectoPracticas.demo.domain.dtos.roles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/* * DTO para la respuesta de la creación de un nuevo rol.
 * Actualmente no contiene campos, pero se puede ampliar en el futuro
 * para incluir información relevante sobre el rol creado.
 */
public class RolCreateResponseDTO {
	
	/* * ID del rol creado.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotNull(message = "El ID del rol no puede ser nulo")
	private Long idRol;
	
	
	/* * Nombre del rol creado.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El nombre del rol es obligatorio")
	private String nombreRol;
	
	
	/* * Getters y setters para los campos del DTO.
	 */
	/* * Método para obtener el ID del rol creado.
	 */
	public Long getIdRol() {
		return idRol;
	}
	
	/* * Método para establecer el ID del rol creado.
	 */
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	
	/* * Método para obtener el nombre del rol creado.
	 */
	public String getNombreRol() {
		return nombreRol;
	}
	
	/* * Método para establecer el nombre del rol creado.
	 */
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
}
