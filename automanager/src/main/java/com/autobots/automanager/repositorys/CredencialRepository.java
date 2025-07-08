package com.autobots.automanager.repositorys;

import com.autobots.automanager.entidades.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CredencialRepository extends JpaRepository<Credencial, Long> {
    @Query("SELECT c FROM Credencial c WHERE c.inativo = false ")
    List<Credencial> findActiveCredentials();

    @Query("SELECT c FROM Credencial c WHERE c.id = :id AND c.inativo = false")
    Optional<Credencial> findActiveCredentialById(@Param("id")Long id);
}
