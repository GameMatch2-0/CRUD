package sptech.school.matchmaker.modules.module.usuario.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class Usuario {

    private UUID id;
    private String nome;
    private String orientacaoSexual;
    private LocalDate dtNascimento;
    private String email;
    private String contato;
    private String senha;
    private LocalDateTime dtCadastro;
    private String[] jogosFavoritos = new String[5];
    private boolean deleted = false;

    public Usuario() {
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
