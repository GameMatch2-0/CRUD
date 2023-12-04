package com.example.matchmaker.matchmakerapi.service.dto.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewUserRequest {
    private UsuarioRequest usuario;
    private NewPerfilRequest perfil;
    private List<NewGeneroJogoPerfil> generoJogoPerfil = new ArrayList<>();
    private List<NewInteressePerfil> interessePerfil = new ArrayList<>();
    private List<NewConsolePerfil> consolePerfil = new ArrayList<>();
    private List<NewMidiaRequest> midiaList = new ArrayList<>();
}
