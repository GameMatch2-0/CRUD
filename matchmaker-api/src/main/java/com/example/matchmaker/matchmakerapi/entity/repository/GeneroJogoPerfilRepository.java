package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.entity.GeneroJogoPerfil;
import com.example.matchmaker.matchmakerapi.entity.embeddable.GeneroJogoPerfilId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroJogoPerfilRepository extends JpaRepository<GeneroJogoPerfil, GeneroJogoPerfilId> {
}
