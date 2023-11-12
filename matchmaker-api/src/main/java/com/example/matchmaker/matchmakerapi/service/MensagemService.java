package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.ListaObj;
import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import com.example.matchmaker.matchmakerapi.entity.repository.MensagemRepository;
import com.example.matchmaker.matchmakerapi.service.dto.request.MensagemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        Mensagem mensagemDeletada = repo.findByIdMensagemAndIdConversaAndVisivelTrue(idMensagem, idConversa).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado")
        );
        if (mensagemDeletada != null) {
            mensagemDeletada.setVisivel(false);
        }
        mensagemDeletada.setDtEdicao(LocalDateTime.now());

        return repo.save(mensagemDeletada);
    }

    public Mensagem atualizarMensagem(MensagemRequest mensagem) {
        Mensagem mensagemAtualizada = repo.findByIdMensagemAndIdConversaAndVisivelTrue(mensagem.getIdMensagem(), mensagem.getIdConversa()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mensagem ou conversa não encontrado")
        );
        mensagemAtualizada.setCorpoMensagem(mensagem.getCorpoMensagem());
        mensagemAtualizada.setDtEdicao(LocalDateTime.now());
        return repo.save(mensagemAtualizada);
    }

    public Mensagem buscarPorIdMensagemAndIdConversa(Long idMensagem, Integer idConversa) {
        return repo.findByIdMensagemAndIdConversaAndVisivelTrue(idMensagem, idConversa).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mensagem ou conversa não encontrado")
        );
    }

    public List<Mensagem> listarTodasMensagensOrdenadasPorDataEnvio(Integer idConversa) {
        ListaObj<Mensagem> listaMensagens = new ListaObj<>(30);
        List<Mensagem> mensagens = repo.findTop30ByIdConversa(idConversa);
        for (Mensagem mensagem : mensagens) {
            listaMensagens.adiciona(mensagem);
        }

        listaMensagens.ordenaPorDataEnvio();

        List<Mensagem> listaFormatada = new ArrayList<>();
        for (int i = 0; i< listaMensagens.getTamanho(); i++) {
            listaFormatada.add(listaMensagens.getElemento(i));
        }
        return listaFormatada;
    }

//    public ListaObj<Mensagem> listarTodasMensagensOrdenadasPorDataEnvio(Integer idConversa) {
//        ListaObj<Mensagem> listaMensagens = new ListaObj<>(30);
//        List<Mensagem> mensagens = repo.findTop30ByIdConversa(idConversa);
//
//        for (Mensagem mensagem : mensagens) {
//            listaMensagens.adiciona(mensagem);
//        }
//
//        listaMensagens.ordenaPorDataEnvio();
//
//        return listaMensagens;
//    }

}
