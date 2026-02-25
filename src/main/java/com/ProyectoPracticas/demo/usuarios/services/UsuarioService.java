package com.ProyectoPracticas.demo.usuarios.services;

import java.util.List;

import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioCreateDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioResponseDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioUpdateDTO;
import com.ProyectoPracticas.demo.usuarios.entidades.RolEntity;

public interface UsuarioService {
	List<UsuarioResponseDTO> listar();
	UsuarioResponseDTO obtenerPorId(Long id);
	UsuarioResponseDTO crear(UsuarioCreateDTO dto);
	UsuarioResponseDTO actualizar(Long id, UsuarioUpdateDTO dto, String rol);
	void eliminar(Long id);
}
