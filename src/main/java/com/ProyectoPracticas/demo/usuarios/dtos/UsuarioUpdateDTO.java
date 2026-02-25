package com.ProyectoPracticas.demo.usuarios.dtos;

public class UsuarioUpdateDTO {
	
	private String nombreUsuario;
	private String apellidoUsuario;
	private String correoUsuario;
	private String contrasenhaUsuario;
	private Integer activo;
	
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
	
	public Integer getActivo() {
		return activo;
	}
	
	public void setActivo(Integer activo) {
		this.activo = activo;
	}
}
