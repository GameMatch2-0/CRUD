package com.example.matchmaker.matchmakerapi.service.dto.request;

import com.example.matchmaker.matchmakerapi.entity.Usuario;
import com.example.matchmaker.matchmakerapi.service.authentication.dto.UsuarioTokenDto;

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

    public static UsuarioTokenDto of(Usuario usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }

}
