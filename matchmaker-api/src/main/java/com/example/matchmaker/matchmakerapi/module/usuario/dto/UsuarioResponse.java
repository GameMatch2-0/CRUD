package com.example.matchmaker.matchmakerapi.module.usuario.dto;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
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
