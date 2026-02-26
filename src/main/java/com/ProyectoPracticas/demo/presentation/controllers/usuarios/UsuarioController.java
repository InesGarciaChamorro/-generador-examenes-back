package com.ProyectoPracticas.demo.presentation.controllers.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioCreateDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioDeleteDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioDetailDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioListDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioUpdateDTO;
import com.ProyectoPracticas.demo.domain.entities.roles.RolEntity;
import com.ProyectoPracticas.demo.domain.entities.usuarios.UsuarioEntity;
import com.ProyectoPracticas.demo.presentation.services.usuarios.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
/* * Controlador REST para la gestión de usuarios en el sistema.
 * Proporciona endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los usuarios registrados.
 * Utiliza el servicio UsuarioService para manejar la lógica de negocio relacionada con los usuarios.
 * Incluye anotaciones de Swagger para documentar la API y facilitar su uso por parte de los desarrolladores.
 */

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones CRUD sobre usuarios")
public class UsuarioController {

	/* * Servicio para manejar la lógica de negocio relacionada con los usuarios.
	 * Se inyecta automáticamente por Spring utilizando la anotación @Autowired.
	 */
	@Autowired
	private UsuarioService service;

	/* * Endpoint para listar todos los usuarios registrados en el sistema.
	 * Devuelve una lista de objetos UsuarioListDTO que contienen la información básica de cada usuario.
	 * Utiliza la anotación @GetMapping para mapear las solicitudes HTTP GET a este método.
	 * Incluye anotaciones de Swagger para documentar la operación y las posibles respuestas.
	 */
	// Devuelve todos los usuarios
	@GetMapping
	@Operation(summary = "Listar usuarios", description = "Devuelve la lista completa de usuarios registrados en el sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioListDTO.class)))) })
	public List<UsuarioListDTO> listar() {
		return service.listar();
	}

	
	/* * Endpoint para obtener los detalles de un usuario específico por su ID.
	 * Devuelve un objeto UsuarioDetailDTO que contiene la información detallada del usuario solicitado.
	 * Utiliza la anotación @GetMapping con un path variable para mapear las solicitudes HTTP GET a este método.
	 * Incluye anotaciones de Swagger para documentar la operación, el parámetro de entrada y las posibles respuestas.
	 */
	// Devuelve usuario por id

	@GetMapping("/{id}")
	@Operation(summary = "Obtener usuario por ID")
	@ApiResponses(value = {
	        @ApiResponse(
	                responseCode = "200",
	                description = "Usuario encontrado",
	                content = @Content(schema = @Schema(implementation = UsuarioDetailDTO.class))
	        ),
	        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	})
	public ResponseEntity<UsuarioDetailDTO> obtenerPorId(
	        @Parameter(description = "ID del usuario a obtener", example = "1", required = true)
	        @PathVariable Long id) {
	    return ResponseEntity.ok(service.obtenerPorId(id));
	}



	/* * Endpoint para crear un nuevo usuario en el sistema.
	 * Recibe un objeto UsuarioCreateDTO en el cuerpo de la solicitud, que contiene la información necesaria para crear el usuario.
	 * Devuelve un objeto UsuarioDetailDTO con los detalles del usuario creado.
	 * Utiliza la anotación @PostMapping para mapear las solicitudes HTTP POST a este método.
	 * Incluye anotaciones de Swagger para documentar la operación y las posibles respuestas.
	 */
	// Crea usuario
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Crear usuario")
	@ApiResponses(value = {
	        @ApiResponse(
	                responseCode = "201",
	                description = "Usuario creado",
	                content = @Content(schema = @Schema(implementation = UsuarioDetailDTO.class))
	        ),
	        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
	})
	public UsuarioDetailDTO crear(@Valid @RequestBody UsuarioCreateDTO dto) {
		return service.crear(dto);
	}

	
	/* * Endpoint para actualizar la información de un usuario existente.
	 * Recibe el ID del usuario a actualizar como path variable y un objeto UsuarioUpdateDTO en el cuerpo de la solicitud con los datos a modificar.
	 * Devuelve un objeto UsuarioDetailDTO con los detalles del usuario actualizado.
	 * Utiliza la anotación @PutMapping para mapear las solicitudes HTTP PUT a este método.
	 * Incluye anotaciones de Swagger para documentar la operación, los parámetros de entrada y las posibles respuestas.
	 */
	// Actualiza usuario
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar usuario", description = "Solo el admin puede cambiar el rol de un usuario. Un usuario o profesor pueden cambiar su contraseña")
	@ApiResponses(value = {
	        @ApiResponse(
	                responseCode = "200",
	                description = "Usuario actualizado",
	                content = @Content(schema = @Schema(implementation = UsuarioDetailDTO.class))
	        ),
	        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
	        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	})
	public ResponseEntity<UsuarioDetailDTO> actualizar(
			@PathVariable Long id,
			@RequestBody(required = false) UsuarioUpdateDTO dto,
			@RequestHeader(name = "Rol", required = false, defaultValue = "USUARIO") String rol) {
				if (dto.getIdUsuario() != null && !dto.getIdUsuario().equals(id)) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
				UsuarioDetailDTO actualizado = service.actualizar(id, dto, rol.toUpperCase());
				return ResponseEntity.ok(actualizado);
			}

	
	
	/* * Endpoint para eliminar un usuario del sistema (borrado lógico).*/
	// Elimina usuario (borrado lógico)
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar usuario", description = "Elimina un usuario (Borrado lógico)")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Usuario eliminado"),
	        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	})

	public ResponseEntity<UsuarioDeleteDTO> deleteUsuario(
	        @Parameter(description = "ID del usuario a eliminar", example = "1", required = true)
	        @PathVariable Long id) {
	    UsuarioDeleteDTO dto = service.eliminar(id);
	    return ResponseEntity.ok(dto);
	}

}
