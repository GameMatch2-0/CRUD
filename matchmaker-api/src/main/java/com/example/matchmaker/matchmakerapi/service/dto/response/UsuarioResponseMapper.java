package com.example.matchmaker.matchmakerapi.service.dto.response;

import com.example.matchmaker.matchmakerapi.entity.Usuario;

public class UsuarioResponseMapper {

    public static UsuarioFullResponse of(Usuario usuario){

        return new UsuarioFullResponse(
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

    }

}
