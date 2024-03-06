package com.example.matchmaker.matchmakerapi.service.dto.request.mapper;

import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.service.dto.request.NewMensagemDto;

public class MensagemRequestMapper {

    public static Mensagem toMensagem(final NewMensagemDto request, final Perfil perfil){
        Mensagem mensagem = new Mensagem();

        mensagem.setIdConversa(request.getIdConversa());
        mensagem.setCorpoMensagem(request.getCorpoMensagem());
        mensagem.setPerfil(perfil);
        mensagem.setDtEnvio(request.getDtEnvio());
        mensagem.setDtEdicao(request.getDtEdicao());
        mensagem.setVisivel(request.getVisivel());

        return mensagem;
    }
}
