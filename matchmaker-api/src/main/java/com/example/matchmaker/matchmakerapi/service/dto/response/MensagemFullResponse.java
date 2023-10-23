package com.example.matchmaker.matchmakerapi.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemFullResponse {
    private Long idMensagem;
    private Long idConversa;
    private String idUsuario;
    private String dtEnvio;
    private String corpoMensagem;
    private boolean deleted = false;
}
