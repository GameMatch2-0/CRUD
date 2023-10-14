package com.example.matchmaker.matchmakerapi.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFullResponse {
    private String id;
    private String nome;
    private String apelido;
    private String orientacaoSexual;
    private String dtNascimento;
    private String email;
    private String contato;
    private String senha;
    private String dtCadastro;
    private List<String> jogosFavoritos;
    private boolean deleted = false;

}
