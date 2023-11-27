package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.entity.Midia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MidiaRepository extends JpaRepository<Midia, Long> {
    List<Midia> findAllByPerfil_IdPerfil(Long id);
    void deleteAllByPerfil_IdPerfil(Long idPerfil);
}
