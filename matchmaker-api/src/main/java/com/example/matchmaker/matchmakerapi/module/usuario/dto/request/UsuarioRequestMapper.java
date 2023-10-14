package com.example.matchmaker.matchmakerapi.module.usuario.dto.request;

import com.example.matchmaker.matchmakerapi.module.usuario.Entity.Usuario;

import java.time.LocalDateTime;

public class UsuarioRequestMapper {

    public static Usuario of(UsuarioRequest usuarioRequest){
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioRequest.getNome());
        usuario.setApelido(usuarioRequest.getApelido());
        usuario.setOrientacaoSexual(usuarioRequest.getOrientacaoSexual());
        usuario.setDtNascimento(usuarioRequest.getDtNascimento());
        usuario.setContato(usuarioRequest.getContato());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(usuarioRequest.getSenha());
        usuario.setDtCadastro(LocalDateTime.now());
        usuario.setJogosFavoritos(usuarioRequest.getJogosFavoritos());

        return usuario;
    }

}
