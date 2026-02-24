package com.ProyectoPracticas.demo.usuarios.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioDTO;
import com.ProyectoPracticas.demo.usuarios.entidades.*;
import com.ProyectoPracticas.demo.usuarios.repositorios.*;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones CRUD sobre usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repo;

    // Devuelve todos los usuarios
    @GetMapping
    @Operation(summary = "Listar usuarios", description = "Devuelve la lista completa de usuarios registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UsuarioEntity.class)),
                            examples = @ExampleObject(value = "[{\"id\":1,\"nombre\":\"Inés\",\"apellido\":\"García Chamorro\", \"correo\":\"usuario@gmail.com\", \"contrasena\":\"12345\"}]")))
    })
    public List<UsuarioEntity> listar() {
        return repo.findAll();
    }

    // Devuelve usuario por id
    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Retorna un usuario específico según su identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                    content = @Content(mediaType = "application/json", 
                            schema = @Schema(implementation = UsuarioEntity.class),
                            examples = @ExampleObject(value = "{\"id\":1,\"nombre\":\"Inés\",\"apellido\":\"García Chamorro\", \"correo\":\"usuario@gmail.com\", \"contrasena\":\"12345\"}]\"}"))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":404,\"message\":\"Usuario no encontrado\"}")))
    })
    public Optional<UsuarioEntity> getIdUsuario(
            @Parameter(description = "ID del usuario a buscar", example = "1", required = true) 
            @PathVariable Long id) {
        return repo.findById(id);
    }

    // Crea usuario
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear usuario", description = "Registra un nuevo usuario en el sistema con los datos proporcionados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
                    content = @Content(mediaType = "application/json", 
                            schema = @Schema(implementation = UsuarioEntity.class),
                            examples = @ExampleObject(value = "{\"id\":1,\"nombre\":\"Inés\",\"apellido\":\"García Chamorro\", \"correo\":\"usuario@gmail.com\", \"contrasena\":\"12345\"}]\"}"))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"correo\":\"Correo requerido\",\"nombre\":\"Nombre requerido\"}")))
    })
    public ResponseEntity<UsuarioEntity> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del usuario a crear", 
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class),		
                            examples = @ExampleObject(value = "{\"id\":1,\"nombre\":\"Inés\",\"apellido\":\"García Chamorro\", \"correo\":\"usuario@gmail.com\", \"contrasena\":\"12345\"}")
                    )
            )
            @Valid @RequestBody UsuarioDTO dto) {
    	UsuarioEntity a = new UsuarioEntity();
        a.setNombre(dto.getNombre());
        a.setCorreo(dto.getCorreo());
        UsuarioEntity saved = repo.save(a);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Actualiza usuario
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Modifica los datos de un usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
                    content = @Content(mediaType = "application/json", 
                            schema = @Schema(implementation = UsuarioEntity.class),
                            examples = @ExampleObject(value = "{\"id\":1,\"nombre\":\"Inés\",\"apellido\":\"García Chamorro\", \"correo\":\"nuevo@gmail.com\", \"contrasena\":\"12345\"}"))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":404,\"message\":\"Usuario no encontrado\"}"))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"correo\":\"Formato inválido\"}")))
    })
    public ResponseEntity<UsuarioEntity> actualizar(
            @Parameter(description = "ID del usuario a actualizar", example = "1", required = true) 
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados del usuario", 
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class),
                            examples = @ExampleObject(value = "{\"nombre\":\"Nuevo Nombre\",\"correo\":\"nuevo@gmail.com\"}")
                    )
            )
            @Valid @RequestBody UsuarioDTO dto) {
        return repo.findById(id).map(existing -> {
            existing.setNombre(dto.getNombre());
            existing.setCorreo(dto.getCorreo());
            UsuarioEntity saved = repo.save(existing);
            return ResponseEntity.ok(saved);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Elimina usuario
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente", 
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":404,\"message\":\"Usuario no encontrado\"}")))
    })
    public ResponseEntity<Void> deleteUsuario(
            @Parameter(description = "ID del usuario a eliminar", example = "1", required = true) 
            @PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
