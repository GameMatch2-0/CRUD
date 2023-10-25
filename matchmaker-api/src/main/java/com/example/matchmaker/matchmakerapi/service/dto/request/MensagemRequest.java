package com.example.matchmaker.matchmakerapi.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemRequest {
    private Integer idConversa;
    private Long idMensagem;
    private String corpoMensagem;
    private Boolean visivel;
}
