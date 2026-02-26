package com.ProyectoPracticas.demo.presentation.services.usuarios;

import java.util.List;

import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioCreateDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioDeleteDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioDetailDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioListDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioUpdateDTO;

/* * Interfaz que define los métodos para la gestión de usuarios.
 * Proporciona operaciones para listar, obtener por ID, crear, actualizar y eliminar usuarios.
 */
public interface UsuarioService {
	List<UsuarioListDTO> listar();
	UsuarioDetailDTO obtenerPorId(Long id);
	UsuarioDetailDTO crear(UsuarioCreateDTO dto);
	UsuarioDetailDTO actualizar(Long id, UsuarioUpdateDTO dto, String rol);
	UsuarioDeleteDTO eliminar(Long id);
}
