package com.example.matchmaker.matchmakerapi.entity.repository;

import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    public Mensagem findByIdMensagemAndVisivelFalse(Long id);
    public Mensagem findByCorpoMensagemContainsIgnoreCaseAndVisivelFalse(String corpoMensagem);
    public Optional<Mensagem> findAllByVisivelFalse();
    public Mensagem findAllByVisivelTrue();
}
