package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
    // A AVALIACAO TEM QUE VIR JA TRATADA LEVANDO EM CONSIDERAÇÃO A NOTA DO CARA Q TA AVALIANDO PORRA
<<<<<<< HEAD
    @Procedure("SP_nova_avaliacao")
    Optional<Avaliacao> adicionarAvaliacao (Integer idPerfilAvaliado, Integer idPerfilLogado, Double avaliacao, String descricao, LocalDate dataHora);

    @Procedure("SP_remover_avaliacao")
    void removerAvaliacao(Integer idPerfilAvaliado, Integer idPerfilLogado);
=======
//    @Procedure("SP_nova_avaliacao")
//    Optional<Avaliacao> adicionarAvaliacao (Integer idPerfilAvaliado, Integer idPerfilLogado, Double avaliacao, String descricao, LocalDate dataHora);
//
//    @Procedure("SP_remover_avaliacao")
//    void removerAvaliacao(Integer idPerfilAvaliado, Integer idPerfilLogado);
//
//    List<Avaliacao> findByIdPerfil(Integer IdPerfil);
>>>>>>> ae64f4703291db31fa877fb6c15eee2264bfda63


}
