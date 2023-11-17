package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    // @Procedure("SP_curtir_perfil")
    // void curtirOutroPerfil(Integer idPerfilLogado, Integer idPerfilCurtido, LocalDate dataHora);

}
