package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import com.example.matchmaker.matchmakerapi.entity.repository.MensagemRepository;
import com.example.matchmaker.matchmakerapi.service.dto.request.MensagemRequest;
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

    public Mensagem salvar(Integer idConversa, Mensagem mensagem) {
        mensagem.setIdConversa(idConversa);
        mensagem.setDtEnvio(LocalDateTime.now());
        mensagem.setDtEdicao(LocalDateTime.now());
        mensagem.setVisivel(true);
        return repo.save(mensagem);
    }

    public Mensagem deletar(Long idMensagem, Integer idConversa) {
        Mensagem mensagemDeletada = repo.findByIdMensagemAndIdConversaAndVisivelTrue(idMensagem, idConversa);
        mensagemDeletada.setVisivel(false);
        return repo.save(mensagemDeletada);
    }

    public Mensagem atualizarMensagem(MensagemRequest mensagem) {
        Mensagem mensagemAtualizada = repo.findByIdMensagemAndIdConversaAndVisivelTrue(mensagem.getIdMensagem(), mensagem.getIdConversa());
        mensagemAtualizada.setCorpoMensagem(mensagem.getCorpoMensagem());
        mensagemAtualizada.setDtEdicao(LocalDateTime.now());
        return repo.save(mensagemAtualizada);
    }

    public Mensagem buscarPorIdMensagemAndIdConversa(Long idMensagem, Integer idConversa) {
        return repo.findByIdMensagemAndIdConversaAndVisivelTrue(idMensagem, idConversa);
    }
}
