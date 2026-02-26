package com.ProyectoPracticas.demo.presentation.controllers.roles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ProyectoPracticas.demo.domain.dtos.roles.RolCreateDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolDetailDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolDeleteDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolListDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolUpdateDTO;
import com.ProyectoPracticas.demo.presentation.services.roles.RolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/* * Controlador REST para la gestión de roles en el sistema.
 * Proporciona endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los roles disponibles.
 * Utiliza el servicio RolService para manejar la lógica de negocio relacionada con los roles.
 * Incluye anotaciones de Swagger para documentar la API y facilitar su uso por parte de los desarrolladores.
 */

@RestController
@RequestMapping("/roles")
@Tag(name = "Roles", description = "Operaciones CRUD sobre roles")
public class RolController {

	/* * Servicio para manejar la lógica de negocio relacionada con los roles.
	 * Se inyecta automáticamente por Spring utilizando la anotación @Autowired.
	 */
    @Autowired
    private RolService service;

    /* * Endpoint para listar todos los roles registrados en el sistema.
	 * Devuelve una lista de objetos RolListDTO que contienen la información básica de cada rol.
	 * Utiliza la anotación @GetMapping para mapear las solicitudes HTTP GET a este método.
	 * Incluye anotaciones de Swagger para documentar la operación y las posibles respuestas.
	 */
    // Devuelve todos los roles
    @GetMapping
    @Operation(summary = "Listar roles", description = "Devuelve la lista completa de roles registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
            content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = RolListDTO.class))))
    })
    public List<RolListDTO> listar() {
        return service.listar();
    }

    
    /* * Endpoint para obtener un rol específico por su ID.
     * Recibe el ID del rol como parámetro en la URL y devuelve un objeto RolDetailDTO con la información detallada del rol.
     */
    // Devuelve rol por id
    @GetMapping("/{id}")
    @Operation(summary = "Obtener rol por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol encontrado",
            content = @Content(schema = @Schema(implementation = RolDetailDTO.class))),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    public ResponseEntity<RolDetailDTO> obtenerPorId(
            @Parameter(description = "ID del rol", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    
    /* * Endpoint para crear un nuevo rol en el sistema.
	 * Recibe un objeto RolCreateDTO en el cuerpo de la solicitud con la información necesaria para crear el rol.
	 * Devuelve un objeto RolDetailDTO con la información del rol creado.
	 */
    // Crear nuevo rol
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear rol")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Rol creado",
            content = @Content(schema = @Schema(implementation = RolDetailDTO.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public RolDetailDTO crear(@RequestBody RolCreateDTO dto) {
        return service.crear(dto);
    }

    /* * Endpoint para actualizar un rol existente en el sistema.
	 * Recibe el ID del rol a actualizar como parámetro en la URL y un objeto RolUpdateDTO en el cuerpo de la solicitud con la información actualizada del rol.
	 * Devuelve un objeto RolDetailDTO con la información del rol actualizado.
	 */
    // Actualizar rol existente
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar rol")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol actualizado",
            content = @Content(schema = @Schema(implementation = RolDetailDTO.class))),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    public ResponseEntity<RolDetailDTO> actualizar(
            @PathVariable Long id,
            @RequestBody RolUpdateDTO dto) {

        RolDetailDTO actualizado = service.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    /* * Endpoint para eliminar un rol del sistema mediante un borrado lógico.
     * Recibe el ID del rol a eliminar como parámetro en la URL y devuelve un objeto RolDeleteDTO con la información del rol eliminado (activo = 0).
     */
    // Eliminar rol (borrado lógico)
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar rol", description = "Borrado lógico (activo = 0)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol eliminado"),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    public ResponseEntity<RolDeleteDTO> eliminar(
            @Parameter(description = "ID del rol", example = "1")
            @PathVariable Long id) {

        RolDeleteDTO dto = service.eliminar(id);
        return ResponseEntity.ok(dto);
    }
}
