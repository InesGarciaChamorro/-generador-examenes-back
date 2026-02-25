package com.ProyectoPracticas.demo.usuarios.entidades;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    
    
    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;
    
    
    @Column(name = "apellido_usuario", nullable = false)
    private String apellidoUsuario;
    
    
    @Column(name = "correo_usuario", nullable = false, unique = true)
    private String correoUsuario;
    
    @Column(name = "contrasenha_usuario", nullable = false)
    private String contrasenhaUsuario;
    
    
    @Column(name = "activo", nullable = false)
    private int activo = 1;
    
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuarios_roles",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private Set<RolEntity> conjuntoRoles = new HashSet<>();
    
       
    // Getters y setters
    public UsuarioEntity() {
}

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

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
    
    public int getActivo() {
		return activo;
	}
    
    public void setActivo(int activo) {
    	this.activo = activo;
	}	
    
    
    public Set<RolEntity> getConjuntoRoles() {
		return conjuntoRoles;
	}

	public void setConjuntoRoles(Set<RolEntity> conjuntoRoles) {
		this.conjuntoRoles = conjuntoRoles;
	}
    
}
