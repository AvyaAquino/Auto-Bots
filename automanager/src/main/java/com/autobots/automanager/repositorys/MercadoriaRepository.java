package com.autobots.automanager.repositorys;

import com.autobots.automanager.entidades.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long> {
}
