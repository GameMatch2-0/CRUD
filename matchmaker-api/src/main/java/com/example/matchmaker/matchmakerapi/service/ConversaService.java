package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Conversa;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.entity.repository.ConversaRepository;
import com.example.matchmaker.matchmakerapi.entity.repository.PerfilRepository;
import com.example.matchmaker.matchmakerapi.service.dto.request.mapper.ConversaRequestMapper;
import com.example.matchmaker.matchmakerapi.service.dto.response.ConversaFullResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ConversaResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversaService {

    private final ConversaRepository repo;
    private final PerfilService service;
    private final MensagemService mensagemService;

    public List<ConversaFullResponse> listarConversas(Long idPerfil){
         List<Conversa> conversasList = repo.buscarConversas(idPerfil.intValue());
         List<ConversaFullResponse> conversaFullResponses = new ArrayList<>();

         conversasList.forEach(conversa ->{
             final var ultimaMensagem = mensagemService.ultimaMensagem(conversa.getIdConversa().longValue(), idPerfil);

             conversaFullResponses.add(
                     ConversaResponseMapper.of(conversa, ultimaMensagem)
             );
         });
            return conversaFullResponses;
    }

    public ConversaFullResponse novaConversa(Long idPerfilLogado, Long idPerfilConversa){
        Perfil perfilUsuario = this.service.getPerfilId(idPerfilLogado);
        Perfil perfilConversa = this.service.getPerfilId(idPerfilConversa);

        final var conversa = ConversaRequestMapper.toConversa(perfilUsuario,perfilConversa);
        repo.save(conversa);
        return ConversaResponseMapper.of(conversa, null);
    }

    public ConversaFullResponse deletarConversa(Integer idConversa){
        Conversa conversa = repo.findByIdConversaAndDeletedFalse(idConversa).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversa não encontrada")
        );
        conversa.setDeleted(true);
        repo.save(conversa);
        return ConversaResponseMapper.of(conversa, null);
    }

    public ConversaFullResponse buscarPorIdConversa(Integer idConversa){
        Conversa conversa = repo.findByIdConversaAndDeletedFalse(idConversa).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversa não encontrada")
        );
        return ConversaResponseMapper.of(conversa, null);
    }



}
