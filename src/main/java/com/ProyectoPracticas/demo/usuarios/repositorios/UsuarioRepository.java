package com.ProyectoPracticas.demo.usuarios.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ProyectoPracticas.demo.usuarios.entidades.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
	List<UsuarioEntity> findAll();

	@Query("SELECT u FROM UsuarioEntity u WHERE u.nombre_usuario = :nombre")
	List<UsuarioEntity> findByNombre(String nombre);
}