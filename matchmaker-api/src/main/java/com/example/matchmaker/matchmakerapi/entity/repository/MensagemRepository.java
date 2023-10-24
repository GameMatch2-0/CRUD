package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    public List<Mensagem> findTop30ByIdConversa(Integer idConversa);
    public Mensagem findByIdMensagemAndVisivelFalse(Long idMensagem);
    public Mensagem findByCorpoMensagemContainsIgnoreCaseAndVisivelFalse(String corpoMensagem);
    public Optional<Mensagem> findAllByVisivelFalse();
    public Mensagem findAllByVisivelTrue();
}
