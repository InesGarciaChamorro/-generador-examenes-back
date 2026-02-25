package com.ProyectoPracticas.demo.usuarios.entidades;

import java.util.*;
import jakarta.persistence.*;


@Entity
@Table (name = "roles")
public class RolEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Long idRol;
	
	@Column(name = "nombre_rol", nullable = false, unique = true)
	private String nombreRol;
	
	@Column(name = "activo", nullable = false)
	private int activo = 1;
	
	@ManyToMany(mappedBy = "conjuntoRoles")
	private Set<UsuarioEntity> usuarios = new HashSet<>();
	
	
	
	
	
	public Long getIdRol() {
		return idRol;
	}
	
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	
	public String getNombreRol() {
		return nombreRol;
	}
	
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	
	public int getActivo() {
		return activo;
	}
	
	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	public Set<UsuarioEntity> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(Set<UsuarioEntity> usuarios) {
		this.usuarios = usuarios;
	}
}