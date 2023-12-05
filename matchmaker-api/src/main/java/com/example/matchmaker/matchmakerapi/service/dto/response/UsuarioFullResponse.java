package com.example.matchmaker.matchmakerapi.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFullResponse {
    private String id;
    private String nome;
    private String sobrenome;
    private LocalDate dtNascimento;
    private String email;
    private String contato;
    private LocalDateTime dtCadastro;
    private String identidadeGenero;
    private boolean deleted = false;


    public String getNomeCompleto(){
        return this.nome + " " + this.sobrenome;
    }

    public int getIdade(){
        Period periodoEntre = Period.between(this.dtNascimento, LocalDate.now());

        return periodoEntre.getYears();
    }

    public String getDtNascimento() {
        return dtNascimento.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getDtCadastro() {
        return dtCadastro.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

}
