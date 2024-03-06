package com.example.matchmaker.matchmakerapi.service.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewMensagemDto {
    private Integer idConversa;
    private Long perfilId;
    private LocalDateTime dtEnvio = LocalDateTime.now();
    private LocalDateTime dtEdicao = LocalDateTime.now();
    private String corpoMensagem;
    private Boolean visivel;
}
