package com.example.matchmaker.matchmakerapi.service.dto.request.mapper;

import com.example.matchmaker.matchmakerapi.entity.Conversa;
import com.example.matchmaker.matchmakerapi.service.dto.request.ConversaRequest;

public class ConversaRequestMapper {

    public static Conversa of(ConversaRequest conversaRequest){
        Conversa conversa = new Conversa();

        conversa.setIdUsuarioLogado(conversaRequest.getIdUsuarioLogado());
        conversa.setIdUsuarioConversa(conversaRequest.getIdUsuarioConversa());

        return conversa;
    }
}
