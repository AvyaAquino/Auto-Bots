package com.autobots.automanager.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entidades.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}