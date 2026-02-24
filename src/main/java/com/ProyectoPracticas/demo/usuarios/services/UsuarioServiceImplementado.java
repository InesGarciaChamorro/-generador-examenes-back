package com.ProyectoPracticas.demo.usuarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioDTO;
import com.ProyectoPracticas.demo.usuarios.entidades.RolEntity;
import com.ProyectoPracticas.demo.usuarios.entidades.UsuarioEntity;
import com.ProyectoPracticas.demo.usuarios.repositorios.UsuarioRepository;

@Service
public class UsuarioServiceImplementado implements UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public List<UsuarioEntity> listar() {
		return repo.findAll().stream().filter(UsuarioEntity::isActivo).toList();
	}

	@Override
	public UsuarioEntity obtenerPorId(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	}

	@Override
	public UsuarioEntity crear(UsuarioDTO dto) {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNombre(dto.getNombre());
		usuario.setApellido(dto.getApellido());	
		usuario.setCorreo(dto.getCorreo());
		usuario.setRol(dto.getRol());	
		usuario.setActivo(true);
		return repo.save(usuario);	
	}

	@Override
	public UsuarioEntity actualizar(Long id, UsuarioDTO dto, RolEntity rol) {
		UsuarioEntity usuario = obtenerPorId(id);
		
		// Solo actualizamos el rol si el rol del usuario no es ADMIN
		if (!rol.equals(RolEntity.ADMIN)) {
			dto.setRol(usuario.getRol());
		}
		usuario.setNombre(dto.getNombre());
		usuario.setApellido(dto.getApellido());
		usuario.setCorreo(dto.getCorreo());
		usuario.setRol(dto.getRol());
		return repo.save(usuario);	
	}

	@Override
	public void eliminar(Long id) {
		UsuarioEntity usuario = obtenerPorId(id);
		usuario.setActivo(false);
		repo.save(usuario);
	}

}
