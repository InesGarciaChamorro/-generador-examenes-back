package com.ProyectoPracticas.demo.domain.dtos.usuarios;
/* * DTO para la respuesta de la creación de un nuevo usuario.
 */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioCreateResponseDTO {
	
	/* * ID del usuario creado.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotNull(message = "El ID del usuario no puede ser nulo")
	private Long idUsuario;
	
	/* * Nombre del usuario creado.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El nombre del usuario es obligatorio")
	private String nombreUsuario;
	
	/* * Apellidos del usuario creado.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "Los apellidos del usuario son obligatorios")
	private String apellidoUsuario;
	
	/* * Correo electrónico del usuario creado.
	 * Debe ser un valor no nulo y no vacío.
	 */
	@NotBlank(message = "El correo electrónico del usuario es obligatorio")
	private String correoUsuario;	
	
	
	/* * Getters y setters para los campos del DTO.
	 */
	
	/* * Método para obtener el ID del usuario creado.
	 */
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	/* * Método para establecer el ID del usuario creado.
	 */
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/* * Método para obtener el nombre del usuario creado.
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	/* * Método para establecer el nombre del usuario creado.
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	/* * Método para obtener los apellidos del usuario creado.
	 */
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}
	
	/* * Método para establecer los apellidos del usuario creado.
	 */
	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}
	
	/* * Método para obtener el correo electrónico del usuario creado.
	 */
	public String getCorreoUsuario() {
		return correoUsuario;
	}
	
	/* * Método para establecer el correo electrónico del usuario creado.
	 */
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}
}
