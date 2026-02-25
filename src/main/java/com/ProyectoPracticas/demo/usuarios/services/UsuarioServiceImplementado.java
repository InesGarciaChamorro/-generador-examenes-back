package com.ProyectoPracticas.demo.usuarios.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioCreateDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioResponseDTO;
import com.ProyectoPracticas.demo.usuarios.dtos.UsuarioUpdateDTO;
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
	public List<UsuarioResponseDTO> listar() {
		return repo.findAll().stream()
				.map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
				.toList();
	}

	@Override
	public UsuarioResponseDTO obtenerPorId(Long id) {
		UsuarioEntity entity =repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		return modelMapper.map(entity, UsuarioResponseDTO.class);
	}

	@Override
	public UsuarioResponseDTO crear(UsuarioCreateDTO dto) {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNombreUsuario(dto.getNombreUsuario());
		usuario.setApellidoUsuario(dto.getApellidoUsuario());	
		usuario.setCorreoUsuario(dto.getCorreoUsuario());
		usuario.setContrasenhaUsuario(dto.getContrasenhaUsuario());
		usuario.setActivo(1);
		UsuarioEntity entity = repo.save(usuario);
		return modelMapper.map(entity, UsuarioResponseDTO.class);	
	}

	@Override
	public UsuarioResponseDTO actualizar(Long id, UsuarioUpdateDTO dto, String rol) {
		UsuarioEntity usuario = repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		
		boolean esAdmin = rol != null && rol.equalsIgnoreCase("ADMIN");
		
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
		if (esAdmin && dto.getActivo() != null) {
			usuario.setActivo(dto.getActivo());
		}
		
		UsuarioEntity entity = repo.save(usuario);
		return modelMapper.map(entity, UsuarioResponseDTO.class);	
	}

	@Override
	public void eliminar(Long id) {
		UsuarioEntity usuario = repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		usuario.setActivo(0);
		repo.save(usuario);
	}

}
