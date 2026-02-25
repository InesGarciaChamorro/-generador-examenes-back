package com.ProyectoPracticas.demo.usuarios.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioCreateDTO {
	@NotBlank(message = "El nombre es obligatorio")
	private String nombreUsuario;
	
	@NotBlank(message = "Los apellidos son obligatorios")
	private String apellidoUsuario;
	
	@Email(message = "El correo electrónico no es válido")
	@NotBlank(message = "El correo electrónico es obligatorio")
	private String correoUsuario;
	
	@NotBlank(message = "La contraseña es obligatoria")
	private String contrasenhaUsuario;
	
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}
	
	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}
	
	public String getCorreoUsuario() {
		return correoUsuario;
	}
	
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}
	
	public String getContrasenhaUsuario() {
		return contrasenhaUsuario;
	}
	
	public void setContrasenhaUsuario(String contrasenhaUsuario) {
		this.contrasenhaUsuario = contrasenhaUsuario;
	}
	
	
}
