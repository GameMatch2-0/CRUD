package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Conversa;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.entity.repository.ConversaRepository;
import com.example.matchmaker.matchmakerapi.entity.repository.PerfilRepository;
import com.example.matchmaker.matchmakerapi.service.dto.response.ConversaFullResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ConversaResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversaService {

    private final ConversaRepository repo;
    private final PerfilRepository perfilRepo;

    public List<ConversaFullResponse> listarConversas(Long idPerfil){
         List<Conversa> conversasList = repo.findAllByIdPerfilLogadoAndDeletedFalse(idPerfil);
            return conversasList.stream()
                    .map(ConversaResponseMapper::of)
                    .collect(Collectors.toList());
    }

    public ConversaFullResponse novaConversa(Long idPerfilLogado, Long idPerfilConversa){
        Conversa conversa = new Conversa();
        Perfil perfilUsuario = this.perfilRepo.findByIdPerfilAndDeletedFalse(idPerfilLogado).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
        );
        Perfil perfilConversa = this.perfilRepo.findByIdPerfilAndDeletedFalse(idPerfilConversa).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
        );
        conversa.setIdPerfilLogado(perfilUsuario);
        conversa.setIdPerfilConversa(perfilConversa);
        conversa.setNotificacoes(0);
        conversa.setAlertaNotificacao(false);
        conversa.setDeleted(false);
        repo.save(conversa);
        return ConversaResponseMapper.of(conversa);
    }

    public ConversaFullResponse deletarConversa(Integer idConversa){
        Conversa conversa = repo.findByIdConversaAndDeletedFalse(idConversa).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversa não encontrada")
        );
        conversa.setDeleted(true);
        repo.save(conversa);
        return ConversaResponseMapper.of(conversa);
    }

    public ConversaFullResponse buscarPorIdConversa(Integer idConversa){
        Conversa conversa = repo.findByIdConversaAndDeletedFalse(idConversa).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversa não encontrada")
        );
        return ConversaResponseMapper.of(conversa);
    }



}
