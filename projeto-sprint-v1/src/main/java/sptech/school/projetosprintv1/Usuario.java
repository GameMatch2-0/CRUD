package sptech.school.projetosprintv1;

import sptech.school.projetosprintv1.Tags;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Usuario {
    private String nome;
    private String orientacaoSexual;
    private LocalDate dtNascimento;
    private String email;
    private String senha;
    private LocalDate dtCadastro;
    private String[] jogosFavoritos = new String[5];
    private boolean deleted = false;

    public Usuario() {
    }

    public Usuario(String nome, String orientacaoSexual, String email, String senha, int dia, int mes, int ano, String[] jogosFavoritos) {
        this.nome = nome;
        this.orientacaoSexual = orientacaoSexual;
        this.dtNascimento = LocalDate.of(ano, mes, dia);
        this.email = email;
        this.senha = senha;
        this.dtCadastro = LocalDate.now();
        this.jogosFavoritos = jogosFavoritos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrientacaoSexual() {
        return orientacaoSexual;
    }

    public void setOrientacaoSexual(String orientacaoSexual) {
        this.orientacaoSexual = orientacaoSexual;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(LocalDate dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public String[] getJogosFavoritos() {
        return jogosFavoritos;
    }

    public void setJogosFavoritos(String[] jogosFavoritos) {
        this.jogosFavoritos = jogosFavoritos;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", orientacaoSexual='" + orientacaoSexual + '\'' +
                ", dtNascimento=" + dtNascimento +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dtCadastro=" + dtCadastro +
                ", jogosFavoritos=" + Arrays.toString(jogosFavoritos) +
                '}';
    }
}
