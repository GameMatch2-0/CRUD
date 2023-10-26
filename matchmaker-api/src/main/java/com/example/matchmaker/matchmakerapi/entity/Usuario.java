package com.example.matchmaker.matchmakerapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    private String id = String.valueOf(UUID.randomUUID());
    private String nome;
    private String apelido;
    private String orientacaoSexual;
    private LocalDate dtNascimento;
    private String email;
    private String contato;
    private String senha;
    private LocalDateTime dtCadastro = LocalDateTime.now();
    @ElementCollection
    private List<String> jogosFavoritos = new ArrayList<>();
    private boolean deleted = false;
    private boolean logado = false;



    public String getDtNascimento() {
        return dtNascimento.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }


    public String getDtCadastro() {
        return dtCadastro.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public void setDtCadastro(LocalDateTime dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public List<String> getJogosFavoritos() {
        return jogosFavoritos;
    }

    public void setJogosFavoritos(List<String> jogosFavoritos) {
        this.jogosFavoritos = jogosFavoritos;
    }


}
