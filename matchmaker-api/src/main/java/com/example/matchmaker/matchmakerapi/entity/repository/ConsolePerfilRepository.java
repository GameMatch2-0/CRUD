package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.entity.ConsolePerfil;
import com.example.matchmaker.matchmakerapi.entity.embeddable.ConsolePerfilId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsolePerfilRepository extends JpaRepository<ConsolePerfil, ConsolePerfilId> {
    Optional<ConsolePerfil> findFirstById_IdPerfil(Long id);
    List<ConsolePerfil> findAllById_IdPerfil(Long id);
}
