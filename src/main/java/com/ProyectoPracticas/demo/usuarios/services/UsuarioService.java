package com.ProyectoPracticas.demo.usuarios.services;

import java.util.List;

import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioDTO;
import com.ProyectoPracticas.demo.usuarios.entidades.RolEntity;
import com.ProyectoPracticas.demo.usuarios.entidades.UsuarioEntity;

public interface UsuarioService {
	List<UsuarioEntity> listar();
	UsuarioEntity obtenerPorId(Long id);
	UsuarioEntity crear(UsuarioDTO dto);
	UsuarioEntity actualizar(Long id, UsuarioDTO dto, RolEntity rol);
	void eliminar(Long id);

}
