package com.example.matchmaker.matchmakerapi.module.usuario.dto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDto {
    private String nome;
    private String apelido;
    private String orientacaoSexual;
    private LocalDate dtNascimento;
    private String email;
    private String contato;
    private String senha;
    private LocalDateTime dtCadastro;
    @ElementCollection
    private List<String> jogosFavoritos;


}
