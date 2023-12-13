package com.example.matchmaker.matchmakerapi.service.dto.response;

import lombok.Data;

@Data
public class PerfilShortResponse {
    private Long idPerfil;
    private String idUsuario;
    private String nomeCompleto;
    private String username;
    private String biografia;
    private Float nota;
    private boolean isPremium = false;

    public void setNomeCompleto(String nome, String sobrenome){
        this.setNomeCompleto(nome + " " + sobrenome);
    }
}
