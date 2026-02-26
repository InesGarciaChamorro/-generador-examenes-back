package com.ProyectoPracticas.demo.usuarios.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioCreateDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioDeleteDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioDetailDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioListDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioUpdateDTO;
import com.ProyectoPracticas.demo.usuarios.entidades.RolEntity;
import com.ProyectoPracticas.demo.usuarios.entidades.UsuarioEntity;
import com.ProyectoPracticas.demo.usuarios.repositorios.RolRepository;
import com.ProyectoPracticas.demo.usuarios.repositorios.UsuarioRepository;

/* * Implementación de la interfaz UsuarioService.
 * Proporciona la lógica de negocio para la gestión de usuarios, incluyendo operaciones para listar, obtener por ID, crear, actualizar y eliminar usuarios.
 */
@Service
public class UsuarioServiceImplementado implements UsuarioService {
	
	/* * Repositorio para acceder a los datos de los usuarios en la base de datos.
	 */
	private UsuarioRepository repo;
	
	/* * Repositorio para acceder a los datos de los roles en la base de datos.
	 */
	private RolRepository rolRepo;
	
	/* * Mapper para convertir entre entidades y DTOs.
	 */
	private ModelMapper modelMapper;
	
	
	/* * Constructor para inyectar las dependencias necesarias.
	 * @param repo Repositorio de usuarios.
	 * @param rolRepo Repositorio de roles.
	 * @param modelMapper Mapper para conversiones entre entidades y DTOs.
	 */
	public UsuarioServiceImplementado(UsuarioRepository repo, RolRepository rolRepo, ModelMapper modelMapper) {
		this.repo = repo;
		this.rolRepo = rolRepo;
		this.modelMapper = modelMapper;
	}
	
	
	/* * Lista todos los usuarios registrados en la base de datos, convirtiendo cada entidad a un DTO de lista para su presentación.
	 * @return Lista de UsuarioListDTO con la información básica de cada usuario.
	 */
	@Override
	public List<UsuarioListDTO> listar() {
		return repo.findAll().stream()
				.map(usuario -> modelMapper.map(usuario, UsuarioListDTO.class))
				.toList();
	}

	/* * Obtiene un usuario por su ID, verificando que esté activo, y lo convierte a un DTO para su presentación.
	 * @param id El ID del usuario a obtener.
	 * @return UsuarioDetailDTO con la información del usuario encontrado.
	 * @throws RuntimeException Si el usuario no se encuentra o no está activo.
	 */
	@Override
	public UsuarioDetailDTO obtenerPorId(Long id) {
		UsuarioEntity entity =repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado por id"));
		return modelMapper.map(entity, UsuarioDetailDTO.class);
	}

	/* * Crea un nuevo usuario en la base de datos a partir de un DTO de creación, y lo convierte a un DTO de detalle para su presentación.
	 * @param dto El DTO de creación con la información del nuevo usuario.
	 * @return UsuarioDetailDTO con la información del usuario creado.
	 */
	@Override
	public UsuarioDetailDTO crear(UsuarioCreateDTO dto) {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNombreUsuario(dto.getNombreUsuario());
		usuario.setApellidoUsuario(dto.getApellidoUsuario());	
		usuario.setCorreoUsuario(dto.getCorreoUsuario());
		usuario.setContrasenhaUsuario(dto.getContrasenhaUsuario());
		usuario.setActivo(1);
		UsuarioEntity entity = repo.save(usuario);
		return modelMapper.map(entity, UsuarioDetailDTO.class);	
	}

	/* * Actualiza un usuario existente en la base de datos a partir de un DTO de actualización, verificando el rol del usuario que realiza la operación, y lo convierte a un DTO de detalle para su presentación.
	 * @param id El ID del usuario a actualizar.
	 * @param dto El DTO de actualización con la información a modificar del usuario.
	 * @param rol El rol del usuario que realiza la operación (ADMIN, USUARIO o PROFESOR).
	 * @return UsuarioDetailDTO con la información del usuario actualizado.
	 * @throws RuntimeException Si el usuario no se encuentra, si el rol no tiene permisos para realizar la operación, o si se intenta cambiar campos no permitidos según el rol.
	 */
	@Override
	public UsuarioDetailDTO actualizar(Long id, UsuarioUpdateDTO dto, String rol) {
		UsuarioEntity usuario = repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado por actualizar"));
		
		boolean esAdmin = "ADMIN".equalsIgnoreCase(rol);
		
		
		// ADMIN no puede cambiar contraseña 
		if (esAdmin && dto.getContrasenhaUsuario() != null) {
			throw new RuntimeException("El admin no puede cambiar la contraseña");
		}
		
		
		// USUARIO o PROFESOR no puede cambiar rol
		if (!esAdmin && dto.getNombreRol() != null) {
			throw new RuntimeException("Solo el admin puede cambiar el rol de un usuario");
		}
		
		if (dto.getNombreUsuario() != null) {
			usuario.setNombreUsuario(dto.getNombreUsuario());
		}
		if (dto.getApellidoUsuario() != null) {
			usuario.setApellidoUsuario(dto.getApellidoUsuario());
		}
		if (dto.getCorreoUsuario() != null) {
			usuario.setCorreoUsuario(dto.getCorreoUsuario());
		}
		
		
		//Usuario o profesor solo pueden cambiar su contraseña
		if (!esAdmin && dto.getContrasenhaUsuario() != null) {
			usuario.setContrasenhaUsuario(dto.getContrasenhaUsuario());
		}
		
		if (dto.getActivo() != null) {
			usuario.setActivo(dto.getActivo());
		}
		
		// Solo el admin puede cambiar el rol de un usuario
		if (esAdmin && dto.getNombreRol() != null) {
			RolEntity nuevoRol = rolRepo.findByNombreRol(dto.getNombreRol())
					.orElseThrow(() -> new RuntimeException("Rol no encontrado por nombre"));
			usuario.getConjuntoRoles().clear();
			usuario.getConjuntoRoles().add(nuevoRol);
		}
		UsuarioEntity save = repo.save(usuario);
		return modelMapper.map(save, UsuarioDetailDTO.class);
	}
	
	/* * Elimina un usuario de la base de datos estableciendo su estado como inactivo, y lo convierte a un DTO de eliminación para su presentación.
	 * @param id El ID del usuario a eliminar.
	 * @return UsuarioDeleteDTO con la información del usuario eliminado.
	 * @throws RuntimeException Si el usuario no se encuentra por eliminar.
	 */
	@Override
	public UsuarioDeleteDTO eliminar(Long id) {
		UsuarioEntity usuario = repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado por eliminar"));
		usuario.setActivo(0);
		UsuarioEntity save = repo.save(usuario);
		
		UsuarioDeleteDTO dto = new UsuarioDeleteDTO();
		dto.setIdUsuario(save.getIdUsuario());
		dto.setNombreUsuario(save.getNombreUsuario());
		dto.setApellidoUsuario(save.getApellidoUsuario());
		return dto;
	}

}
