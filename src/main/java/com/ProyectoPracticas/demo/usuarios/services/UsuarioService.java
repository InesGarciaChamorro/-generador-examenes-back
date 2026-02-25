package com.ProyectoPracticas.demo.usuarios.services;

import java.util.List;

import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioDTO;
import com.ProyectoPracticas.demo.usuarios.entidades.RolEntity;

public interface UsuarioService {
	List<UsuarioDTO> listar();
	UsuarioDTO obtenerPorId(Long id);
	UsuarioDTO crear(UsuarioDTO dto);
	UsuarioDTO actualizar(Long id, UsuarioDTO dto, RolEntity rol);
	void eliminar(Long id);

}
