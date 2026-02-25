package com.ProyectoPracticas.demo.usuarios.services;

import java.util.List;

import org.modelmapper.ModelMapper;
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
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<UsuarioDTO> listar() {
		return repo.findAll().stream()
				.map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
				.toList();
	}

	@Override
	public UsuarioDTO obtenerPorId(Long id) {
		UsuarioEntity entity =repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		return modelMapper.map(entity, UsuarioDTO.class);
	}

	@Override
	public UsuarioDTO crear(UsuarioDTO dto) {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNombreUsuario(dto.getNombreUsuario());
		usuario.setApellidoUsuario(dto.getApellidoUsuario());	
		usuario.setCorreoUsuario(dto.getCorreoUsuario());
		usuario.setContrasenhaUsuario(dto.getContrasenhaUsuario());
		usuario.setActivo(0);
		UsuarioEntity entity = repo.save(usuario);
		return modelMapper.map(entity, UsuarioDTO.class);	
	}

	@Override
	public UsuarioDTO actualizar(Long id, UsuarioDTO dto, RolEntity rol) {
		UsuarioEntity usuario = repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		
		if (dto != null) {
			if (dto.getNombreUsuario() != null) {
				usuario.setNombreUsuario(dto.getNombreUsuario());
			}
			if (dto.getApellidoUsuario() != null) {
				usuario.setApellidoUsuario(dto.getApellidoUsuario());
			}
			if (dto.getCorreoUsuario() != null) {
				usuario.setCorreoUsuario(dto.getCorreoUsuario());
			}
			if (dto.getContrasenhaUsuario() != null) {
				usuario.setContrasenhaUsuario(dto.getContrasenhaUsuario());
			}
		}
		UsuarioEntity entity = repo.save(usuario);
		return modelMapper.map(entity, UsuarioDTO.class);	
	}

	@Override
	public void eliminar(Long id) {
		UsuarioEntity usuario = repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		usuario.setActivo(1);
		repo.save(usuario);
	}

}
