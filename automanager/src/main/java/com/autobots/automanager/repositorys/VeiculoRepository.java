package com.autobots.automanager.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entidades.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
