package com.example.matchmaker.matchmakerapi.service.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class NewUserRequest {
    private UsuarioRequest usuario;
    private NewPerfilRequest perfil;
    private List<NewGeneroJogoPerfil> generoJogoPerfil;
    private List<NewInteressePerfil> interessePerfil;
    private List<NewConsolePerfil> consolePerfil;
    private List<NewMidiaRequest> midiaRequestList;
}
