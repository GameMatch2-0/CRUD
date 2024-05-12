package com.example.matchmaker.matchmakerapi.service.dto.response.mapper;

import com.example.matchmaker.matchmakerapi.entity.Conversa;
import com.example.matchmaker.matchmakerapi.service.dto.response.ConversaFullResponse;

public class ConversaResponseMapper {

    public static ConversaFullResponse of(Conversa conversa, String ultimaMensagem){

        return new ConversaFullResponse(
                conversa.getIdConversa(),
                conversa.getIdPerfilLogado().getIdPerfil(),
                conversa.getIdPerfilConversa().getIdPerfil(),
                ultimaMensagem,
                conversa.getNotificacoes(),
                conversa.isAlertaNotificacao(),
                conversa.isDeleted()
        );

    }
}
