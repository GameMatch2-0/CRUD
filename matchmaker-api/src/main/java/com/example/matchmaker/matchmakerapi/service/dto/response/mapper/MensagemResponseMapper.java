package com.example.matchmaker.matchmakerapi.service.dto.response.mapper;

import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import com.example.matchmaker.matchmakerapi.service.dto.response.MensagemFullResponse;

import java.time.LocalDateTime;

public class MensagemResponseMapper {
        public static MensagemFullResponse of(Mensagem mensagem){

            return new MensagemFullResponse(
                    mensagem.getIdMensagem(),
                    mensagem.getIdConversa(),
                    mensagem.getDtEnvio(),
                    mensagem.getDtEdicao(),
                    mensagem.getIdUsuario(),
                    mensagem.getCorpoMensagem(),
                    mensagem.getVisivel()
            );

        }
}
