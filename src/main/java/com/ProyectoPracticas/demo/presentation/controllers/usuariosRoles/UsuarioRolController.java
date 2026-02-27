package com.ProyectoPracticas.demo.presentation.controllers.usuariosRoles;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ProyectoPracticas.demo.domain.dtos.roles.RolListDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolCreateDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolCreateResponseDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolDeleteDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolListDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolListUsuarioDTO;
import com.ProyectoPracticas.demo.presentation.services.usuariosRoles.UsuarioRolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/* * Controlador REST para la gestión de relaciones usuario-rol.
 * Ofrece operaciones para asignar y retirar roles a usuarios, consultar los roles de un usuario,
 * obtener los usuarios que pertenecen a un rol concreto y listar todas las asignaciones existentes.
 * La lógica de negocio se delega al servicio UsuarioRolService.
 */
@RestController
@RequestMapping
@Tag(name = "Usuarios-Roles", description = "Operaciones sobre la relación entre usuarios y roles")
public class UsuarioRolController {

    /* * Servicio que centraliza la lógica de asignaciones usuario-rol. */
    private final UsuarioRolService service;

    /* * Constructor para la inyección del servicio de relaciones.
     * @param service Implementación de UsuarioRolService que gestiona las operaciones.
     */
    public UsuarioRolController(UsuarioRolService service) {
        this.service = service;
    }

    
    
    /* * Asigna un rol a un usuario.
     * Recibe el identificador del usuario en la ruta y el identificador del rol en el cuerpo.
     * @param idUsuario ID del usuario al que se le asignará el rol.
     * @param dto DTO con el id del rol a asignar.
     * @return Objeto con los identificadores de usuario y rol asociados.
     */
    @PostMapping("/usuarios/{idUsuario}/roles")
    @Operation(summary = "Asignar rol a usuario", description = "Crea la relación usuario-rol")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol asignado correctamente",
            content = @Content(schema = @Schema(implementation = UsuarioRolCreateResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Usuario o rol no encontrado")
    })
    public ResponseEntity<UsuarioRolCreateResponseDTO> asignarRol(
            @Parameter(description = "ID del usuario", example = "1", required = true)
            @PathVariable Long idUsuario,
            @RequestBody UsuarioRolCreateDTO dto) {

        return ResponseEntity.ok(service.asignarRol(idUsuario, dto));
    }

    
    
    /* * Quita un rol previamente asignado a un usuario.
     * Recibe ambos identificadores en la ruta.
     * @param idUsuario ID del usuario del que se desea retirar el rol.
     * @param idRol ID del rol que se eliminará del usuario.
     * @return Objeto con los identificadores que formaban la asignación eliminada.
     */
    @DeleteMapping("/usuarios/{idUsuario}/roles/{idRol}")
    @Operation(summary = "Quitar rol a un usuario", description = "Elimina la relación usuario-rol")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol eliminado del usuario",
            content = @Content(schema = @Schema(implementation = UsuarioRolDeleteDTO.class))),
        @ApiResponse(responseCode = "404", description = "Usuario o rol no encontrado")
    })
    public ResponseEntity<UsuarioRolDeleteDTO> quitarRol(
            @Parameter(description = "ID del usuario", example = "1", required = true)
            @PathVariable Long idUsuario,
            @Parameter(description = "ID del rol", example = "2", required = true)
            @PathVariable Long idRol) {

        return ResponseEntity.ok(service.quitarRol(idUsuario, idRol));
    }
    
    

    /* * Devuelve todos los roles que tiene asignados un usuario.
     * @param idUsuario ID del usuario del que se consultan los roles.
     * @return Lista de roles en formato RolListDTO (id_rol, nombre_rol, activo).
     */
    @GetMapping("/usuarios/{idUsuario}/roles")
    @Operation(summary = "Listar roles de un usuario", description = "Devuelve todos los roles asignados al usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente",
            content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = RolListDTO.class)))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<List<RolListDTO>> rolesDeUsuario(
            @Parameter(description = "ID del usuario", example = "1", required = true)
            @PathVariable Long idUsuario) {

        return ResponseEntity.ok(service.obtenerRolesDeUsuario(idUsuario));
    }

    
    
    /* * Devuelve los usuarios asociados a un rol concreto.
     * @param idRol ID del rol cuyas pertenencias se desean consultar.
     * @return Lista con el nombre del usuario y su estado activo.
     */
    @GetMapping("/roles/{idRol}/usuarios")
    @Operation(summary = "Listar usuarios de un rol", description = "Devuelve usuarios que tienen el rol indicado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente",
            content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = UsuarioRolListUsuarioDTO.class)))),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    public ResponseEntity<List<UsuarioRolListUsuarioDTO>> usuariosDeRol(
            @Parameter(description = "ID del rol", example = "2", required = true)
            @PathVariable Long idRol) {

        return ResponseEntity.ok(service.obtenerUsuariosPorRol(idRol));
    }

    
    
	/* * Devuelve todas las asignaciones existentes en la tabla de relación.
	 * Útil para revisiones globales o depuración de pertenencias.
	 * @return Lista de pares (id_usuario, id_rol).
	 */
	@GetMapping("/usuarios_roles")
	@Operation(summary = "Listar asignaciones usuario-rol", description = "Devuelve todas las relaciones existentes")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente",
	        content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = UsuarioRolListDTO.class))))
	})
	public ResponseEntity<List<UsuarioRolListDTO>> todasAsignaciones() {
	    return ResponseEntity.ok(service.obtenerTodasAsignaciones());
	}
}

