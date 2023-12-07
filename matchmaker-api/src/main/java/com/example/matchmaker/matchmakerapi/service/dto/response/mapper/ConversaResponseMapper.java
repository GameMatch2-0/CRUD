package com.example.matchmaker.matchmakerapi.service.dto.response.mapper;

import com.example.matchmaker.matchmakerapi.entity.Conversa;
import com.example.matchmaker.matchmakerapi.service.dto.response.ConversaFullResponse;

public class ConversaResponseMapper {

    public static ConversaFullResponse of(Conversa conversa){

        return new ConversaFullResponse(
                conversa.getIdConversa(),
                conversa.getIdPerfilLogado().getIdPerfil(),
                conversa.getIdPerfilConversa().getIdPerfil(),
                conversa.getNotificacoes(),
                conversa.getAlertaNotificacao(),
                conversa.isDeleted()
        );

    }
}
