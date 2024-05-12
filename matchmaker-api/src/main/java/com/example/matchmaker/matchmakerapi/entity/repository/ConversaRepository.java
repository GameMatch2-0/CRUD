package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.entity.Conversa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ConversaRepository extends JpaRepository<Conversa, Integer> {
    Optional<Conversa> findByIdConversaAndDeletedFalse(Integer idConversa);

    List<Conversa> findAllByIdPerfilLogadoAndDeletedFalse(Long idPerfilLogado);

    List<Conversa> findByIdPerfilConversaAndDeletedFalse(Long idPerfilConversa);

    List<Conversa> findByNotificacoesAndDeletedFalse(int notificacoes);

    @Procedure("SP_tela_chat")
    List<Conversa> buscarConversas(Integer idPerfilLogado);

    List<Conversa> findAllByDeletedTrue();



}
