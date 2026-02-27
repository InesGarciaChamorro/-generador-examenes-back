package com.ProyectoPracticas.demo.presentation.services.usuariosRoles;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.ProyectoPracticas.demo.domain.dtos.roles.RolListDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolCreateDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolCreateResponseDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolDeleteDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolListDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuariosRoles.UsuarioRolListUsuarioDTO;
import com.ProyectoPracticas.demo.domain.entities.roles.RolEntity;
import com.ProyectoPracticas.demo.domain.entities.usuarios.UsuarioEntity;
import com.ProyectoPracticas.demo.domain.repositories.roles.RolRepository;
import com.ProyectoPracticas.demo.domain.repositories.usuarios.UsuarioRepository;

/* * Implementación de la interfaz UsuarioRolService.
 * Proporciona la lógica de negocio para la gestión de la relación entre usuarios y roles, aunque actualmente no tiene métodos implementados.
 */
@Service
public class UsuarioRolServiceImplementado implements UsuarioRolService{

/* * Repositorio de usuarios. */
    private final UsuarioRepository usuarioRepo;

    /* * Repositorio de roles. */
    private final RolRepository rolRepo;

    /* *Constructor para inyectar las dependencias necesarias.
     * @param usuarioRepo Repositorio de acceso a datos de UsuarioEntity.
     * @param rolRepo Repositorio de acceso a datos de RolEntity.
     */
    public UsuarioRolServiceImplementado(UsuarioRepository usuarioRepo, RolRepository rolRepo) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
    }

    /* * Asigna un rol a un usuario si no lo tenía previamente.
     * @param idUsuario ID del usuario al que se le asignará el rol.
     * @param dto DTO de entrada con el ID del rol a asignar (UsuarioRolCreateDTO).
     * @return DTO de salida con la relación creada (UsuarioRolCreateResponseDTO).
     * @throws RuntimeException si no existe el usuario o el rol.
     */
    @Override
    public UsuarioRolCreateResponseDTO asignarRol(Long idUsuario, UsuarioRolCreateDTO dto) {
        UsuarioEntity usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        RolEntity rol = rolRepo.findById(dto.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        // Para que no se dupliquen los roles
        boolean yaAsignado = usuario.getConjuntoRoles().stream()
                .anyMatch(r -> r.getIdRol().equals(rol.getIdRol()));
        if (!yaAsignado) {
            usuario.getConjuntoRoles().add(rol);
            usuarioRepo.save(usuario);
        }

        UsuarioRolCreateResponseDTO response = new UsuarioRolCreateResponseDTO();
        response.setIdUsuario(usuario.getIdUsuario());
        response.setIdRol(rol.getIdRol());
        return response;
    }

    /* *Quita un rol asignado a un usuario. 
     * @param idUsuario ID del usuario del que se quiere quitar el rol.
     * @param idRol ID del rol a quitar.
     * @return DTO con los IDs de la relación eliminada (UsuarioRolDeleteDTO).
     * @throws RuntimeException si no existe el usuario o el rol.
     */
    @Override
    public UsuarioRolDeleteDTO quitarRol(Long idUsuario, Long idRol) {
        UsuarioEntity usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        rolRepo.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.getConjuntoRoles().removeIf(r -> r.getIdRol().equals(idRol));
        usuarioRepo.save(usuario);

        UsuarioRolDeleteDTO dto = new UsuarioRolDeleteDTO();
        dto.setIdUsuario(idUsuario);
        dto.setIdRol(idRol);
        return dto;
    }

    /* *Obtiene el listado de roles asignados a un usuario concreto.
     * @param idUsuario ID del usuario del que se desean consultar los roles.
     * @return Lista de roles en formato RolListDTO (idRol, nombreRol, activo).
     * @throws RuntimeException si el usuario no existe.
     */
    @Override
    public List<RolListDTO> obtenerRolesDeUsuario(Long idUsuario) {
        UsuarioEntity usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<RolListDTO> lista = new ArrayList<>();
        for (RolEntity r : usuario.getConjuntoRoles()) {
            RolListDTO dto = new RolListDTO();
            dto.setIdRol(r.getIdRol());
            dto.setNombreRol(r.getNombreRol());
            dto.setActivo(r.getActivo());
            lista.add(dto);
        }
        return lista;
    }

    /* *Obtiene el listado de usuarios que tienen asignado un rol concreto.
     * @param idRol ID del rol.
     * @return Lista de usuarios con su nombre y estado activo (UsuarioRolListUsuarioDTO).
     * @throws RuntimeException si el rol no existe.
     */
    @Override
    public List<UsuarioRolListUsuarioDTO> obtenerUsuariosPorRol(Long idRol) {
        RolEntity rol = rolRepo.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        List<UsuarioRolListUsuarioDTO> lista = new ArrayList<>();
        for (UsuarioEntity u : rol.getUsuarios()) {
            UsuarioRolListUsuarioDTO dto = new UsuarioRolListUsuarioDTO();
            dto.setNombreUsuario(u.getNombreUsuario());
            dto.setActivo(u.getActivo());
            lista.add(dto);
        }
        return lista;
    }

    /* *Obtiene todas las asignaciones existentes (pares usuario-rol) en el sistema.
     * @return Lista de asignaciones en formato UsuarioRolListDTO (idUsuario, idRol).
     */
    @Override
    public List<UsuarioRolListDTO> obtenerTodasAsignaciones() {
        List<UsuarioRolListDTO> lista = new ArrayList<>();
        for (UsuarioEntity u : usuarioRepo.findAll()) {
            for (RolEntity r : u.getConjuntoRoles()) {
                UsuarioRolListDTO dto = new UsuarioRolListDTO();
                dto.setIdUsuario(u.getIdUsuario());
                dto.setIdRol(r.getIdRol());
                lista.add(dto);
            }
        }
        return lista;
    }
}


