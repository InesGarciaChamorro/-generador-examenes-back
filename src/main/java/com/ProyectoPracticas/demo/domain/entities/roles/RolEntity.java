package com.ProyectoPracticas.demo.domain.entities.roles;

import java.util.*;

import com.ProyectoPracticas.demo.domain.entities.usuarios.UsuarioEntity;

import jakarta.persistence.*;

/**
 * Entidad que representa un rol en el sistema.
 * Un rol define un conjunto de permisos que pueden ser asignados a los usuarios.
 * Cada rol tiene un nombre único y un estado activo/inactivo.
 * Además, un rol puede estar asociado a múltiples usuarios a través de una relación ManyToMany.
 */

@Entity
@Table (name = "roles")
public class RolEntity{
	
	/* * Identificador único del rol.
	 * Es generado automáticamente por la base de datos.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Long idRol;
	
	/* * Nombre del rol.
	 * Es un valor no nulo, único y no vacío que identifica el rol.
	 */
	@Column(name = "nombre_rol", nullable = false, unique = true)
	private String nombreRol;
	
	/* * Estado del rol.
	 * Es un valor entero que indica si el rol está activo (1) o inactivo (0).
	 * Por defecto, el rol se crea como activo (1).
	 */
	@Column(name = "activo", nullable = false)
	private int activo = 1;
	
	/* * Conjunto de usuarios asociados a este rol.
	 * Es una relación ManyToMany con la entidad UsuarioEntity.
	 * El mapeo se realiza a través del atributo "conjuntoRoles" en la entidad UsuarioEntity.
	 */
	@ManyToMany(mappedBy = "conjuntoRoles")
	private Set<UsuarioEntity> usuarios = new HashSet<>();
	
	
	/* * Getters y setters para los campos de la entidad.
	 */
	/* * Método para obtener el identificador del rol.
	 */
	public Long getIdRol() {
		return idRol;
	}
	
	/* * Método para establecer el identificador del rol.
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
	
	/* * Método para obtener el estado del rol.
	 */
	public int getActivo() {
		return activo;
	}
	
	/* * Método para establecer el estado del rol.
	 */
	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	/* * Método para obtener el conjunto de usuarios asociados a este rol.
	 */
	public Set<UsuarioEntity> getUsuarios() {
		return usuarios;
	}
	
	/* * Método para establecer el conjunto de usuarios asociados a este rol.
	 */
	public void setUsuarios(Set<UsuarioEntity> usuarios) {
		this.usuarios = usuarios;
	}
}