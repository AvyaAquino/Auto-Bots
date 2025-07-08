package com.autobots.automanager.repositorys;

import com.autobots.automanager.entidades.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
