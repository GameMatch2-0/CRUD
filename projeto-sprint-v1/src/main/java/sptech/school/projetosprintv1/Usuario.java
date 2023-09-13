package sptech.school.projetosprintv1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Usuario {
    private String nome;
    private String orientacaoSexual;
    private LocalDate dtNascimento;
    private String email;
    private Integer celular;
    private String senha;
    private LocalDateTime dtCadastro;
    private String[] jogosFavoritos = new String[5];
    private boolean deleted = false;

    public Usuario() {
    }

    public Usuario(String nome, String orientacaoSexual, String email, LocalDate dtNascimento, Integer celular, String senha, String[] jogosFavoritos) {
        this.nome = nome;
        this.orientacaoSexual = orientacaoSexual;
        this.email = email;
        this.dtNascimento = dtNascimento;
        this.celular = celular;
        this.senha = senha;
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

    public int getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
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
