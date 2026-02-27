package com.ProyectoPracticas.demo.presentation.services.usuariosRoles;

import java.util.List;

import com.ProyectoPracticas.demo.domain.dtos.roles.RolListDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolCreateDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolCreateResponseDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolDeleteDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolListDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolListUsuarioDTO;

/* Interfaz que define los métodos para la gestión de usuarios_roles.
 * Proporciona operaciones para listar, obtener por ID, crear, y eliminar usuarios_roles.*/
public interface UsuarioRolService {
	UsuarioRolCreateResponseDTO asignarRol(Long idUsuario, UsuarioRolCreateDTO dto);
    UsuarioRolDeleteDTO quitarRol(Long idUsuario, Long idRol);
    List<RolListDTO> obtenerRolesDeUsuario(Long idUsuario);
    List<UsuarioRolListUsuarioDTO> obtenerUsuariosPorRol(Long idRol);
    List<UsuarioRolListDTO> obtenerTodasAsignaciones();
}
