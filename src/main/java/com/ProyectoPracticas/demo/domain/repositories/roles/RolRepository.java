package com.ProyectoPracticas.demo.domain.repositories.roles;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoPracticas.demo.domain.entities.roles.RolEntity;

/**
 * Repositorio para la entidad RolEntity.
 * Proporciona métodos para realizar operaciones CRUD y consultas personalizadas en la base de datos.
 */

public interface RolRepository extends JpaRepository<RolEntity, Long> {
	Optional<RolEntity> findByNombreRol(String nombreRol);
}
