package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, String> {
    // @Procedure("SP_curtir_usuario")
    // void curtirOutroUsuario(Integer idUsuarioLogado, Integer idUsuarioCurtido, LocalDate dataHora);

}
