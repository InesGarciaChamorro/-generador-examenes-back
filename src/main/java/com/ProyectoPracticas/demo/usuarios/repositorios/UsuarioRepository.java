package com.ProyectoPracticas.demo.usuarios.repositorios;

import com.ProyectoPracticas.demo.usuarios.entidades.UsuarioEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository
    extends JpaRepository<UsuarioEntity, Long> {
        List<UsuarioEntity> findByNombre(String nombre);
    }