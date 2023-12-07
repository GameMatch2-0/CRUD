package com.example.matchmaker.matchmakerapi.service.dto.response.mapper;

import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import com.example.matchmaker.matchmakerapi.service.dto.response.MensagemFullResponse;

public class MensagemResponseMapper {
        public static MensagemFullResponse of(Mensagem mensagem){

            return new MensagemFullResponse(
                    mensagem.getIdMensagem(),
                    mensagem.getIdConversa(),
                    mensagem.getPerfil().getIdPerfil(),
                    mensagem.getDtEnvio(),
                    mensagem.getDtEdicao(),
                    mensagem.getCorpoMensagem(),
                    mensagem.getVisivel()
            );

        }
}
