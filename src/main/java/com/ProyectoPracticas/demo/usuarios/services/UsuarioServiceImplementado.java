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
		return repo.findAll().stream().filter(UsuarioEntity::getActivo).toList();
	}

	@Override
	public UsuarioEntity obtenerPorId(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	}

	@Override
	public UsuarioEntity crear(UsuarioDTO dto) {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNombre_usuario(dto.getNombre_usuario());
		usuario.setApellido_usuario(dto.getApellido_usuario());	
		usuario.setCorreo_usuario(dto.getCorreo_usuario());
		usuario.setContrasenha_usuario(dto.getContrasenha_usuario());
		usuario.setActivo(true);
		return repo.save(usuario);	
	}

	@Override
	public UsuarioEntity actualizar(Long id, UsuarioDTO dto, RolEntity rol) {
		UsuarioEntity usuario = obtenerPorId(id);
		
		if (dto.getNombre_usuario() != null) {
			usuario.setNombre_usuario(dto.getNombre_usuario());
		}
		
		if (dto.getApellido_usuario() != null) {
			usuario.setApellido_usuario(dto.getApellido_usuario());
		}
		
		if (dto.getCorreo_usuario() != null) {
			usuario.setCorreo_usuario(dto.getCorreo_usuario());
		}
		
		if (dto.getContrasenha_usuario() != null && rol != RolEntity.ADMIN) {
			usuario.setContrasenha_usuario(dto.getContrasenha_usuario());
		}
		
		return repo.save(usuario);
	}

	@Override
	public void eliminar(Long id) {
		UsuarioEntity usuario = obtenerPorId(id);
		usuario.setActivo(false);
		repo.save(usuario);
	}

}
