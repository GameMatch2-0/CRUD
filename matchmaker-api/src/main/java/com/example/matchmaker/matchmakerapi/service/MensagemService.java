package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import com.example.matchmaker.matchmakerapi.entity.repository.MensagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MensagemService {
    private final MensagemRepository repo;

    public List<Mensagem> getUltimas30Mensagens(Integer idConversa) {
        return repo.findTop30ByIdConversa(idConversa);
    }

    public Mensagem salvar(Mensagem mensagem) {
        mensagem.setDtEnvio(LocalDateTime.now());
        mensagem.setVisivel(true);
        return repo.save(mensagem);
    }

    public Mensagem deletar(Mensagem mensagem) {
        Mensagem mensagemDeletada = repo.findByIdMensagemAndVisivelFalse(mensagem.getIdMensagem());
        mensagemDeletada.setVisivel(false);
        return repo.save(mensagemDeletada);
    }
}
