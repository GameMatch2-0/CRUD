package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Conversa;
import com.example.matchmaker.matchmakerapi.entity.repository.ConversaRepository;
import com.example.matchmaker.matchmakerapi.service.dto.response.ConversaFullResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ConversaResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversaService {

    private final ConversaRepository repo;

    public List<ConversaFullResponse> listarConversas(String idUsuario){
         List<Conversa> conversasList = repo.findAllByIdUsuarioLogadoAndDeletedFalse(idUsuario);
            return conversasList.stream()
                    .map(ConversaResponseMapper::of)
                    .collect(Collectors.toList());
    }

    public ConversaFullResponse novaConversa(String idUsuarioLogado, String idUsuarioConversa){
        Conversa conversa = new Conversa();
        conversa.setIdUsuarioLogado(idUsuarioLogado);
        conversa.setIdUsuarioConversa(idUsuarioConversa);
        conversa.setNotificacoes(0);
        conversa.setAlertaNotificacao(0);
        conversa.setDeleted(false);
        repo.save(conversa);
        return ConversaResponseMapper.of(conversa);
    }

    public ConversaFullResponse deletarConversa(Integer idConversa){
        Conversa conversa = repo.findByIdConversaAndDeletedFalse(idConversa);
        conversa.setDeleted(true);
        repo.save(conversa);
        return ConversaResponseMapper.of(conversa);
    }



}
