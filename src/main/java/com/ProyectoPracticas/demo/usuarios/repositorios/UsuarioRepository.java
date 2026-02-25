package com.ProyectoPracticas.demo.usuarios.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoPracticas.demo.usuarios.entidades.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {	
	List<UsuarioEntity> findByActivo(int activo);
}