package com.autobots.automanager.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entidades.Mercadoria;

public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long> {
}
