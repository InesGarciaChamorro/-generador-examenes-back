package com.ProyectoPracticas.demo.usuarios.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UsuarioDTO", description = "Datos para crear o actualizar un usuario")
public class UsuarioDTO {

    @Schema(description = "Correo electrónico (debe ser gmail.com)", example = "usuario@gmail.com")
    @NotNull(message = "Correo requerido")
    @NotBlank(message = "Correo no vacío")
    @Email(message = "Formato inválido")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Debe ser un correo gmail.com")
    private String correo;

    @Schema(description = "Nombre completo del alumno", example = "Inés")
    @NotNull(message = "Nombre requerido")
    @NotBlank(message = "Nombre no puede estar vacío")
    private String nombre;
    
    
    @Schema(description = "Apellidos completo del alumno", example = "García Chamorro")
    @NotNull(message = "Apellidos requerido")
    @NotBlank(message = "Apellidos no pueden estar vacío")
    private String apellido;

    public UsuarioDTO() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
}