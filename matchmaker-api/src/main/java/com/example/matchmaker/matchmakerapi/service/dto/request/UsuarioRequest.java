package com.example.matchmaker.matchmakerapi.service.dto.request;

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
public class UsuarioRequest {
    private String nome;
    private String sobrenome;
    private String email;
    private String contato;
    private String senha;
    private LocalDate dtNascimento;
    private String identidadeGenero;

}
