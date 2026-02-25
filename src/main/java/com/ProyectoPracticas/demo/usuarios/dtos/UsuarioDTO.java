package com.ProyectoPracticas.demo.usuarios.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.ProyectoPracticas.demo.usuarios.entidades.RolEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UsuarioDTO", description = "Datos para crear o actualizar un usuario")
public class UsuarioDTO {

	@Schema(description = "Nombre completo del alumno", example = "Inés")
    @NotNull(message = "Nombre requerido")
    @NotBlank(message = "Nombre no puede estar vacío")
	@JsonProperty("nombre_usuario")
    private String nombreUsuario;
    
    
    @Schema(description = "Apellidos completos del alumno", example = "García Chamorro")
    @NotNull(message = "Apellidos requeridos")
    @NotBlank(message = "Apellidos no pueden estar vacío")
    @JsonProperty("apellido_usuario")
    private String apellidoUsuario;
	
    
    @Schema(description = "Correo electrónico (debe ser gmail.com)", example = "usuario@gmail.com")
    @NotNull(message = "Correo requerido")
    @NotBlank(message = "Correo no vacío")
    @Email(message = "Formato inválido")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Debe ser un correo gmail.com")
    @JsonProperty("correo_usuario")
    private String correoUsuario;
    
    
    @Schema(description = "Contraseña del usuario", example = "Password123")
    @NotNull(message = "Contraseña requerida")
    @NotBlank(message = "Contraseña no puede estar vacía")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número")
    @JsonProperty("contrasenha_usuario")
    private String contrasenhaUsuario;
    
    public UsuarioDTO() {}


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
