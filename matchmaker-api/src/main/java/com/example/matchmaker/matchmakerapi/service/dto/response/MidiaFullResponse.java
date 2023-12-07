package com.example.matchmaker.matchmakerapi.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MidiaFullResponse {
    private Long idMidia;
    private Long idPerfil;
    private String link;
    private boolean isVisible;
}
