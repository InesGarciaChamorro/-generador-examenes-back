package com.ProyectoPracticas.demo.presentation.services.roles;

import java.util.List;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolCreateDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolDeleteDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolDetailDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolUpdateDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolListDTO;
import com.ProyectoPracticas.demo.domain.dtos.roles.RolCreateResponseDTO;

/* * Interfaz que define los métodos para la gestión de roles.
 * Proporciona operaciones para listar, obtener por ID, crear, actualizar y eliminar roles.
 */
public interface RolService {
	List<RolListDTO> listar();
	RolDetailDTO obtenerPorId(Long id);
	RolCreateResponseDTO crear(RolCreateDTO dto);
	RolDetailDTO actualizar(Long id, RolUpdateDTO dto);
	RolDeleteDTO eliminar(Long id);
}