package com.autobots.automanager.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entidades.Venda;

public interface VendaRespository extends JpaRepository<Venda, Long> {
}
