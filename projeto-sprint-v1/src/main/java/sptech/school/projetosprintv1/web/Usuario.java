package sptech.school.projetosprintv1.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario {
    private String nome;
    private String orientacaoSexual;
    private Date dtNascimento;
    private String email;
    private String senha;
    private String confirmarSenha;
    private int dia;
    private int mes;
    private int ano;
    private LocalDate dtCadastro;

    private List<Tags> tags = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nome, String orientacaoSexual, Date dtNascimento, String email, String senha, String confirmarSenha, int dia, int mes, int ano) {
        this.nome = nome;
        this.orientacaoSexual = orientacaoSexual;
        this.dtNascimento = dtNascimento;
        this.email = email;
        this.senha = senha;
        this.confirmarSenha = confirmarSenha;
        this.dtCadastro = LocalDate.of(ano, mes, dia);
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

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
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

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public LocalDate getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(LocalDate dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public void addTag(Tags tag) {
        tags.add(tag);
    }

    public List<Tags> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Dados do Cadastro:" +
                "nome='" + nome + '\'' +
                ", orientacaoSexual='" + orientacaoSexual + '\'' +
                ", dtNascimento=" + dtNascimento +
                ", email='" + email + '\'' +
                ", dtCadastro=" + dtCadastro + "\n";
    }

    public void add(String s) {
    }
}
