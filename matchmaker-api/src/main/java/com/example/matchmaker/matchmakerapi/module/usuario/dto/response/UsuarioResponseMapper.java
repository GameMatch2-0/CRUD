package com.example.matchmaker.matchmakerapi.module.usuario.dto.response;

import com.example.matchmaker.matchmakerapi.module.usuario.Entity.Usuario;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.request.UsuarioRequest;

import java.time.LocalDateTime;

public class UsuarioResponseMapper {

    public static UsuarioFullResponse of(Usuario usuario){
        UsuarioFullResponse usuarioFullResponse = new UsuarioFullResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getApelido(),
                usuario.getOrientacaoSexual(),
                usuario.getDtNascimento(),
                usuario.getEmail(),
                usuario.getContato(),
                usuario.getSenha(),
                usuario.getDtCadastro(),
                usuario.getJogosFavoritos(),
                usuario.isDeleted()
        );

        return usuarioFullResponse;

    }

}
