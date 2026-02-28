package com.ProyectoPracticas.demo.presentation.services.roles;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.ProyectoPracticas.demo.presentation.exceptions.NotFoundException;
import com.ProyectoPracticas.demo.presentation.exceptions.DuplicateException;

import com.ProyectoPracticas.demo.domain.dtos.roles.RolCreateDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolDeleteDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolDetailDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolListDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolUpdateDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolCreateResponseDTO;
import com.ProyectoPracticas.demo.domain.entities.roles.RolEntity;
import com.ProyectoPracticas.demo.domain.repositories.roles.RolRepository;

/* * Implementación de la interfaz RolService.
 * Proporciona la lógica de negocio para la gestión de roles, incluyendo operaciones para listar, obtener por ID, crear, actualizar y eliminar roles.
 */
@Service
public class RolServiceImplementado implements RolService {
	
	/* * Repositorio para acceder a los datos de los roles en la base de datos.
	 */
	private RolRepository repo;
	
	/* * Mapper para convertir entre entidades y DTOs.
	 */
	private ModelMapper modelMapper;
	
	/* * Constructor para inyectar las dependencias necesarias.
	 * @param repo Repositorio de roles.
	 * @param modelMapper Mapper para conversiones entre entidades y DTOs.
	 */
	public RolServiceImplementado(RolRepository repo, ModelMapper modelMapper) {
		this.repo = repo;
		this.modelMapper = modelMapper;
	}
	
	/* * Lista todos los roles registrados en la base de datos, convirtiendo cada entidad a un DTO de lista para su presentación.
	 * @return Lista de RolListDTO con la información básica de cada rol.
	 */
	@Override
	public List<RolListDTO> listar() {
		return repo.findAll().stream()
				.map(rol -> modelMapper.map(rol, RolListDTO.class))
				.toList();
	}
	
	/* * Obtiene un rol por su ID y lo convierte a un DTO para su presentación.
	 * @param id ID del rol a obtener.
	 * @return RolDetailDTO con la información detallada del rol.
	 * @throws RuntimeException si el rol no se encuentra en la base de datos.
	 */
	@Override
	public RolDetailDTO obtenerPorId(Long id) {
		RolEntity entity = repo.findById(id).orElseThrow(() -> new NotFoundException("Rol no encontrado"));
		return modelMapper.map(entity, RolDetailDTO.class);
	}
	
	
	/* * Crea un nuevo rol en la base de datos a partir de un DTO de creación, y devuelve un DTO con los detalles del rol creado.
	 * @param dto DTO con la información necesaria para crear un nuevo rol.
	 * @return RolDetailDTO con la información detallada del rol creado.
	 */
	@Override
	public RolCreateResponseDTO crear(RolCreateDTO dto) {
		if (repo.findByNombreRol(dto.getNombreRol()).isPresent()) {
			throw new DuplicateException("Rol ya existente");
		}
		RolEntity rol = new RolEntity();
        rol.setNombreRol(dto.getNombreRol());
        rol.setActivo(1);

        RolEntity saved = repo.save(rol);
        RolCreateResponseDTO response = new RolCreateResponseDTO();
        response.setIdRol(saved.getIdRol());
        response.setNombreRol(saved.getNombreRol());
        return response;
	}
	
	
	/* * Actualiza un rol existente en la base de datos a partir de un DTO de creación, y devuelve un DTO con los detalles del rol actualizado.
	 * @param id ID del rol a actualizar.
	 * @param dto DTO con la información actualizada del rol.
	 * @return RolDetailDTO con la información detallada del rol actualizado.
	 * @throws RuntimeException si el rol no se encuentra en la base de datos.
	 */
	@Override
	public RolDetailDTO actualizar(Long id, RolUpdateDTO dto) {
		RolEntity rol = repo.findById(id).orElseThrow(() -> new NotFoundException("Rol no encontrado"));
		
		if (dto.getNombreRol() != null) {
			rol.setNombreRol(dto.getNombreRol());
		}
		if (dto.getActivo() != null) {
			rol.setActivo(dto.getActivo());
		}
		
		return modelMapper.map(repo.save(rol), RolDetailDTO.class);
	}
	
	
	/* * Elimina un rol de la base de datos por su ID.
	 * @param id ID del rol a eliminar.
	 * @throws RuntimeException si el rol no se encuentra en la base de datos.
	 */
	@Override
	public RolDeleteDTO eliminar(Long id) {
		RolEntity rol = repo.findById(id).orElseThrow(() -> new NotFoundException("Rol no encontrado"));
		rol.setActivo(0);
		repo.save(rol);
		
		RolDeleteDTO dto = new RolDeleteDTO();
		dto.setIdRol(rol.getIdRol());
		dto.setNombreRol(rol.getNombreRol());		
		
		return dto;
	}
	
}