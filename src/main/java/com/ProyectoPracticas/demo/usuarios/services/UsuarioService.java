package com.ProyectoPracticas.demo.usuarios.services;

import java.util.List;

import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioCreateDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioDeleteDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioDetailDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioListDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioUpdateDTO;

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
