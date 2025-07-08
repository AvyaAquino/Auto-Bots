package com.autobots.automanager.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
