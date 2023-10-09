package com.example.matchmaker.matchmakerapi.module.usuario.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getOrientacaoSexual() {
        return orientacaoSexual;
    }

    public void setOrientacaoSexual(String orientacaoSexual) {
        this.orientacaoSexual = orientacaoSexual;
    }

    public String getDtNascimento() {
        return dtNascimento.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
