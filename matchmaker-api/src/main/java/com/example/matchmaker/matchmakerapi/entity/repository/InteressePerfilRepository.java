package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.entity.InteressePerfil;
import com.example.matchmaker.matchmakerapi.entity.embeddable.InteressePerfilId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteressePerfilRepository extends JpaRepository<InteressePerfil, InteressePerfilId> {
}
