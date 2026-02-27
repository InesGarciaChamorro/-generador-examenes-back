package com.ProyectoPracticas.demo.domain.dtos.usuariosRoles;

import jakarta.validation.constraints.NotBlank;

/* * DTO para la creación de una relación entre un usuario y un rol.
 * Este DTO se utiliza para recibir los datos necesarios para asignar un rol a un usuario específico.
 * Actualmente, esta clase está vacía, pero se puede expandir en el futuro para incluir campos como el ID del usuario y el ID del rol.
 */
public class UsuarioRolCreateDTO {
	
	/* * El ID del usuario al que se le asignará el rol.
	 * Este campo es obligatorio y no puede estar vacío.
	 */
	@NotBlank(message = "El ID del usuario no puede estar vacío")
	private Long idRol;
	
	/* * Los setters y getters para el campo idRol.
	 * Estos métodos permiten establecer y obtener el valor del ID del rol.
	 */
	
	/* * Getter para el ID del rol.
	 * @return El ID del rol asignado al usuario.
	 */
	public Long getIdRol() {
		return idRol;
	}
	
	/* * Setter para el ID del rol.
	 * @param idRol El ID del rol que se asignará al usuario.
	 */
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	
}
