package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.entity.GeneroJogoPerfil;
import com.example.matchmaker.matchmakerapi.entity.embeddable.GeneroJogoPerfilId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneroJogoPerfilRepository extends JpaRepository<GeneroJogoPerfil, GeneroJogoPerfilId> {
    List<GeneroJogoPerfil> findAllByIdIdPerfil(Long id);
    Optional<GeneroJogoPerfil> findFirstById_IdPerfil(Long id);
}
