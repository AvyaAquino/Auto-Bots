package com.autobots.automanager.repositorys;

import com.autobots.automanager.entidades.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
