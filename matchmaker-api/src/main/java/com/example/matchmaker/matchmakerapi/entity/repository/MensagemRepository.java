package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findTop30ByIdConversa(Integer idConversa);
    Mensagem findByIdMensagemAndVisivelFalse(Long idMensagem);
    Mensagem findByCorpoMensagemContainsIgnoreCaseAndVisivelTrue(String corpoMensagem);
    Mensagem findAllByVisivelTrue();
    Mensagem findByIdMensagemAndVisivelTrue(Long idMensagem);
    Mensagem findByIdMensagemAndIdConversaAndVisivelTrue(Long idMensagem, Integer idConversa);
}
