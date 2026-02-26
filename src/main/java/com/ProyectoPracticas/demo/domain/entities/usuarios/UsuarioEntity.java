package com.ProyectoPracticas.demo.domain.entities.usuarios;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.ProyectoPracticas.demo.domain.entities.roles.RolEntity;

/**
 * Entidad que representa a un usuario en la base de datos.
 */

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
	
	/* * Identificador único del usuario.
	 * Es generado automáticamente por la base de datos y es la clave primaria de la tabla "usuarios".
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    
    /* * Nombre del usuario.
	 * Es un campo obligatorio y no puede ser nulo.
	 */
    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;
    
    /* * Apellidos del usuario. 
     * Es un campo obligatorio y no puede ser nulo.
     */
    @Column(name = "apellido_usuario", nullable = false)
    private String apellidoUsuario;
    
    /* * Correo electrónico del usuario.
	 * Es un campo obligatorio, no puede ser nulo y debe ser único en la tabla "usuarios".
	 */
    @Column(name = "correo_usuario", nullable = false, unique = true)
    private String correoUsuario;
    
    /* * Contraseña del usuario.
     * Es un campo obligatorio y no puede ser nulo.
     */
    @Column(name = "contrasenha_usuario", nullable = false)
    private String contrasenhaUsuario;
    
    /* * Indica si el usuario está activo o no.
	 * Es un campo obligatorio y no puede ser nulo. Por defecto, se establece en 1 (activo).
	 */
    @Column(name = "activo", nullable = false)
    private int activo = 1;
    
    /* * Conjunto de roles asociados al usuario.
     * Es una relación de muchos a muchos con la entidad RolEntity, utilizando una tabla intermedia llamada "usuarios_roles".
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuarios_roles",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private Set<RolEntity> conjuntoRoles = new HashSet<>();
    
       
    /* * Constructor por defecto de la entidad UsuarioEntity.
	 * Este constructor es necesario para que JPA pueda crear instancias de la entidad.
	 */
    public UsuarioEntity() {
}

    /* * Getters y setters para los campos de la entidad UsuarioEntity.
	 * Estos métodos permiten acceder y modificar los valores de los campos de la entidad.
	 */
    /* * Método para obtener el identificador único del usuario.
	 * @return El identificador único del usuario.
	 */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /* * Método para establecer el identificador único del usuario.
     * @param idUsuario El identificador único del usuario a establecer.
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /* * Método para obtener el nombre del usuario.
	 * @return El nombre del usuario.
	 */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /* * Método para establecer el nombre del usuario.
	 * @param nombreUsuario El nombre del usuario a establecer.
	 */
    public void setNombreUsuario(String nombreUsuario) { 
    	this.nombreUsuario = nombreUsuario; 
    }

    /* * Método para obtener los apellidos del usuario.
     * @return Los apellidos del usuario.
     */
    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    /* * Método para establecer los apellidos del usuario.
	 * @param apellidoUsuario Los apellidos del usuario a establecer.
	 */
    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    /* * Método para obtener el correo electrónico del usuario.
     * @return El correo electrónico del usuario.
     */
    public String getCorreoUsuario() {
        return correoUsuario;
    }

    /* * Método para establecer el correo electrónico del usuario.
     * @param correoUsuario El correo electrónico del usuario a establecer.
     */
    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }
    
    /* * Método para obtener la contraseña del usuario.
	 * @return La contraseña del usuario.
	 */
    public String getContrasenhaUsuario() {
        return contrasenhaUsuario;
    }

    /* * Método para establecer la contraseña del usuario.
     * @param contrasenhaUsuario La contraseña del usuario a establecer.
     */
    public void setContrasenhaUsuario(String contrasenhaUsuario) {
        this.contrasenhaUsuario = contrasenhaUsuario;
    }
    
    /* * Método para obtener el estado de actividad del usuario.
     * @return El estado de actividad del usuario (1 para activo, 0 para inactivo).
     */
    public int getActivo() {
		return activo;
	}
    
    /* * Método para establecer el estado de actividad del usuario.
	 * @param activo El estado de actividad del usuario a establecer (1 para activo, 0 para inactivo).
	 */
    public void setActivo(int activo) {
    	this.activo = activo;
	}	
    
    /* * Método para obtener el conjunto de roles asociados al usuario.
	 * @return El conjunto de roles asociados al usuario.
	 */
    public Set<RolEntity> getConjuntoRoles() {
		return conjuntoRoles;
	}

    /* * Método para establecer el conjunto de roles asociados al usuario.
	 * @param conjuntoRoles El conjunto de roles asociados al usuario a establecer.
	 */
	public void setConjuntoRoles(Set<RolEntity> conjuntoRoles) {
		this.conjuntoRoles = conjuntoRoles;
	}
    
}
