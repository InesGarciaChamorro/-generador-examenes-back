package com.ProyectoPracticas.demo.usuarios.entidades;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    @JsonProperty("id_usuario")
    private Long id_usuario;
    
    
    @Column(name = "nombre_usuario", nullable = false)
    @JsonProperty("nombre_usuario")
    private String nombre_usuario;
    
    
    @Column(name = "apellido_usuario", nullable = false)
    @JsonProperty("apellido_usuario")
    private String apellido_usuario;
    
    
    @Column(name = "correo_usuario", nullable = false, unique = true)
    @JsonProperty("correo_usuario")
    private String correo_usuario;
    
    @Column(name = "contrasenha_usuario", nullable = false)
    @JsonProperty(value = "contrasenha_usuario", access = JsonProperty.Access.WRITE_ONLY)
    private String contrasenha_usuario;
    
    
    @Column(name = "activo", nullable = false)
    @JsonProperty("activo")
    private boolean activo = true;
    
    
    // Getters y setters
    public UsuarioEntity() {
}

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }


    public void setNombre_usuario(String nombre_usuario) { 
    	this.nombre_usuario = nombre_usuario; 
    }

    
    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }


    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }
    
    public String getContrasenha_usuario() {
        return contrasenha_usuario;
    }

    public void setContrasenha_usuario(String contrasenha_usuario) {
        this.contrasenha_usuario = contrasenha_usuario;
    }
    
    public boolean getActivo() {
		return activo;
	}
    
    public void setActivo(boolean activo) {
		this.activo = activo;
	}	
    
    
}
