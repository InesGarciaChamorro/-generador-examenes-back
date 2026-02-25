package com.ProyectoPracticas.demo.usuarios.repositorios;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ProyectoPracticas.demo.usuarios.entidades.RolEntity;


public interface RolRepository extends JpaRepository<RolEntity, Long> {
	Optional<RolEntity> findByNombreRol(String nombreRol);
}
