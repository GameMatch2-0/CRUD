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
    private Long idUsuarioLogado;
    private Long idUsuarioConversa;
    private String ultimaMensagem;
    private int notificacoes;
    private boolean alertaNotificacao;
    private Boolean deleted = true;
}
