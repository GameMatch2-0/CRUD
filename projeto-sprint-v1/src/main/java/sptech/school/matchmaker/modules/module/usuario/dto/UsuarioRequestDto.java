package sptech.school.matchmaker.modules.module.usuario.dto;

import java.time.LocalDateTime;

public class UsuarioRequestDto {
    private String id;
    private String nome;
    private String orientacaoSexual;
    private String dtNascimento;
    private String email;
    private String contato;
    private String senha;
    private LocalDateTime dtCadastro;
    private String[] jogosFavoritos;

}
