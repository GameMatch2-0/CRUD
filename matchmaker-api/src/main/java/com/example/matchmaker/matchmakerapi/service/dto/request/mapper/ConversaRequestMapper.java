package com.example.matchmaker.matchmakerapi.service.dto.request.mapper;

import com.example.matchmaker.matchmakerapi.entity.Conversa;
import com.example.matchmaker.matchmakerapi.entity.Perfil;

public class ConversaRequestMapper {

    public static Conversa toConversa(
            final Perfil perfilUsuario,
            final Perfil perfilConversa) {

        Conversa conversa = new Conversa();

        conversa.setIdPerfilConversa(perfilConversa);
        conversa.setIdPerfilLogado(perfilUsuario);
        conversa.setNotificacoes(0);
        conversa.setAlertaNotificacao(false);
        conversa.setDeleted(false);

        return conversa;
    }
}
