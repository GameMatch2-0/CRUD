package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findTop30ByIdConversa(Integer idConversa);
//    Mensagem findByIdMensagemAndVisivelFalse(Long idMensagem);
//    Mensagem findByCorpoMensagemContainsIgnoreCaseAndVisivelTrue(String corpoMensagem);
    List<Mensagem> findAllByVisivelTrue();
//    Mensagem findByIdMensagemAndidConversaAndVisivelTrue(Long idMensagem, Integer idConversa);
    Optional<Mensagem> findByIdMensagemAndIdConversaAndVisivelTrue(Long idMensagem, Integer idConversa);

    @Query("SELECT m FROM Mensagem m " +
            "WHERE m.idConversa = :idConversa " +
            "AND m.perfil.id <> :idPerfilLogado " +
            "AND m.dtEnvio = (SELECT MAX(m2.dtEnvio) FROM Mensagem m2 WHERE m2.idConversa = :idConversa)")
    Optional<Mensagem> ultimaMensagem(@Param("idConversa") Long idConversa,
                                      @Param("idPerfilLogado")Long idPerfilLogado);

}
