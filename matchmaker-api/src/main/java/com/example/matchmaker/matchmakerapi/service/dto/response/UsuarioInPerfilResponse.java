package com.example.matchmaker.matchmakerapi.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioInPerfilResponse {
    private String id;
    private String nome;
    private String sobrenome;
    private LocalDate dtNascimento;
    private String email;
    private String contato;
    private LocalDateTime dtCadastro;
    private String identidadeGenero;
    private boolean deleted = false;
}
