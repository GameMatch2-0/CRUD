package com.example.matchmaker.matchmakerapi.service.dto.response.mapper;

import com.example.matchmaker.matchmakerapi.entity.GeneroJogo;
import com.example.matchmaker.matchmakerapi.entity.GeneroJogoPerfil;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.entity.Usuario;
import com.example.matchmaker.matchmakerapi.service.dto.response.GeneroJogoPerfilResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.JogoInPerfilResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.PerfilFullResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.UsuarioFullResponse;

import java.util.List;

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

    public static PerfilFullResponse toPerfilFullResponse(Perfil perfil, List<JogoInPerfilResponse> generoJogoList){
        PerfilFullResponse perfilFullResponse = new PerfilFullResponse();

        perfilFullResponse.setIdPerfil(perfil.getIdPerfil());
        perfilFullResponse.setIdUsuario(perfil.getUsuario().getId());
        perfilFullResponse.setUsername(perfil.getUsername());
        perfilFullResponse.setBiografia(perfil.getBiografia());
        perfilFullResponse.setNota(perfil.getNota());
        perfilFullResponse.setOrientacaoSexual(perfil.getOrientacaoSexual());
        perfilFullResponse.setProcuraAmizade(perfilFullResponse.isProcuraAmizade());
        perfilFullResponse.setProcuraNamoro(perfil.isProcuraNamoro());
        perfilFullResponse.setProcuraPlayer2(perfil.isProcuraPlayer2());
        perfilFullResponse.setPremium(perfilFullResponse.isPremium());
        perfilFullResponse.setGenerosJogos(generoJogoList);

        return perfilFullResponse;
    }

    public static GeneroJogoPerfilResponse toGeneroJogoPerfilResponse(GeneroJogoPerfil generoJogoPerfil){
        GeneroJogoPerfilResponse resposta = new GeneroJogoPerfilResponse();

        resposta.setPerfilId(generoJogoPerfil.getId().getIdPerfil());
        resposta.setGeneroJogoId(generoJogoPerfil.getId().getIdGeneroJogos());
        resposta.setVisible(generoJogoPerfil.isVisivel());

        return resposta;
    }

    public static JogoInPerfilResponse toJogoInPerfilResponse(GeneroJogo generoJogo, boolean isVisible){
        JogoInPerfilResponse response = new JogoInPerfilResponse();

        response.setId(generoJogo.getIdGeneroJogos());
        response.setNome(generoJogo.getNome());
        response.setVisible(isVisible);
        return response;
    }
}
