package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.FilaObj;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Procedure("SP_curtir_perfil")
    void curtirOutroPerfil(Integer idPerfilLogado, Integer idPerfilCurtido, LocalDate dataHora);


    @Procedure("SP_descurtir_perfil")
    void descurtirOutroPerfil(Integer idPerfilLogado, Integer idPerfilDescurtido);


    @Procedure("SP_busca_carrossel")
    List<Perfil> buscaPerfisCarrossel(Integer idPerfilLogado);

    @Procedure("SP_buscar_amigos")
    List<String> buscarAmigos(Integer idPerfilLogado);
}
