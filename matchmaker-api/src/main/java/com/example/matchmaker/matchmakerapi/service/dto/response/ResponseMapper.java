package com.example.matchmaker.matchmakerapi.service.dto.response;

import com.example.matchmaker.matchmakerapi.entity.Usuario;

public class ResponseMapper {
    public static UsuarioFullResponse toUsuarioFullResponse(Usuario usuario){
        UsuarioFullResponse usuarioFullResponse = new UsuarioFullResponse();

        usuarioFullResponse.setId(usuario.getId());
        usuarioFullResponse.setNome(usuario.getNome());
        usuarioFullResponse.setSobrenome(usuario.getSobrenome());
        usuarioFullResponse.setDtNascimento(usuario.getDtNascimento());
        usuarioFullResponse.setEmail(usuario.getEmail());
        usuarioFullResponse.setContato(usuario.getContato());
        usuarioFullResponse.setDtCadastro(usuario.getDtCadastro());
        usuarioFullResponse.setIdentidadeGenero(usuario.getIdentidadeGenero());
        usuarioFullResponse.setLogado(usuario.isLogado());
        usuarioFullResponse.setDeleted(usuario.isDeleted());

        return usuarioFullResponse;

    }
}
