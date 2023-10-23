package com.example.matchmaker.matchmakerapi.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversaFullResponse {
    private Integer idConversa;
    private String idUsuarioLogado;
    private String idUsuarioConversa;
    private int notificacoes;
    private int alertaNotificacao;
    private boolean deleted = false;
}
