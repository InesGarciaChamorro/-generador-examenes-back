package com.ProyectoPracticas.demo.usuarios.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.ProyectoPracticas.demo.usuarios.entidades.RolEntity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UsuarioDTO", description = "Datos para crear o actualizar un usuario")
public class UsuarioDTO {

	@Schema(description = "Nombre completo del alumno", example = "Inés")
    @NotNull(message = "Nombre requerido")
    @NotBlank(message = "Nombre no puede estar vacío")
    private String nombre_usuario;
    
    
    @Schema(description = "Apellidos completos del alumno", example = "García Chamorro")
    @NotNull(message = "Apellidos requeridos")
    @NotBlank(message = "Apellidos no pueden estar vacío")
    private String apellido_usuario;
	
    
    @Schema(description = "Correo electrónico (debe ser gmail.com)", example = "usuario@gmail.com")
    @NotNull(message = "Correo requerido")
    @NotBlank(message = "Correo no vacío")
    @Email(message = "Formato inválido")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Debe ser un correo gmail.com")
    private String correo_usuario;
    
    
    @Schema(description = "Contraseña del usuario", example = "Password123")
    @NotNull(message = "Contraseña requerida")
    @NotBlank(message = "Contraseña no puede estar vacía")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número")
    private String contrasenha_usuario;
    
    public UsuarioDTO() {}


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

}
