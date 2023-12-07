package com.example.matchmaker.matchmakerapi.service.dto.response;

import lombok.Data;

@Data
public class PerfilShortResponse {
    private Long idPerfil;
    private String idUsuario;
    private String username;
    private String biografia;
    private Float nota;
    private boolean isPremium = false;
}
