package com.ProyectoPracticas.demo.usuarios.entidades;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    
    @JsonIgnore
    private String contrasena;

    @Enumerated(EnumType.STRING)
    private RolEntity rol;
    
    
    private boolean activo;

    
    // Getters y setters
    public UsuarioEntity() {
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    }

    
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public RolEntity getRol() {
		return rol;
	}
    
    public void setRol(RolEntity rol) {
    	this.rol = rol;
    }
    
    public boolean isActivo() {
		return activo;
	}
    
    public void setActivo(boolean activo) {
		this.activo = activo;
	}	
}
