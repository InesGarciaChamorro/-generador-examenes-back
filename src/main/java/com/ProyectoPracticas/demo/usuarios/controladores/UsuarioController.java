package com.ProyectoPracticas.demo.usuarios.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioCreateDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioResponseDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioUpdateDTO;
import com.ProyectoPracticas.demo.usuarios.entidades.RolEntity;
import com.ProyectoPracticas.demo.usuarios.entidades.UsuarioEntity;
import com.ProyectoPracticas.demo.usuarios.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones CRUD sobre usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	// Devuelve todos los usuarios
	@GetMapping
	@Operation(summary = "Listar usuarios", description = "Devuelve la lista completa de usuarios registrados en el sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioEntity.class)))) })
	public List<UsuarioResponseDTO> listar() {
		return service.listar();
	}

	// Devuelve usuario por id
	@GetMapping("/{id}")
	@Operation(summary = "Obtener usuario por ID")
	public ResponseEntity<UsuarioResponseDTO> obtenerPorId(
			@Parameter(description = "ID del usuario a obtener", example = "1", required = true) @PathVariable Long id) {
		return ResponseEntity.ok(service.obtenerPorId(id));
	}


	// Crea usuario
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Crear usuario")
	public UsuarioResponseDTO crear(@Valid @RequestBody UsuarioCreateDTO dto) {
		return service.crear(dto);
	}

	// Actualiza usuario
	@PatchMapping("/{id}")
	@Operation(summary = "Actualizar usuario", description = "Solo el admin puede actualizar el rol de un usuario. El resto de campos pueden ser actualizados por cualquier rol.")
	public ResponseEntity<UsuarioResponseDTO> actualizar(
			@PathVariable Long id,
			@RequestBody(required = false) UsuarioUpdateDTO dto,
			@RequestHeader(name = "Rol", required = false, defaultValue = "USUARIO") String rol) {
		UsuarioResponseDTO actualizado = service.actualizar(id, dto, rol.toUpperCase());
		return ResponseEntity.ok(actualizado);
	}

	// Elimina usuario
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar usuario", description = "Elimina un usuario (Borrado lógico)")
	public ResponseEntity<Void> deleteUsuario(
			@Parameter(description = "ID del usuario a eliminar", example = "1", required = true) @PathVariable Long id) {
		try {
			service.eliminar(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
