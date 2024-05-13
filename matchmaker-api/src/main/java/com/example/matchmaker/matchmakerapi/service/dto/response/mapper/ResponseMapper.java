package com.example.matchmaker.matchmakerapi.service.dto.response.mapper;

import com.example.matchmaker.matchmakerapi.entity.*;
import com.example.matchmaker.matchmakerapi.service.dto.response.*;

import java.util.List;

public class ResponseMapper {

    public static PerfilShortResponse toPerfilShortResponse(Perfil perfil){
        PerfilShortResponse response = new PerfilShortResponse();

        response.setIdPerfil(perfil.getIdPerfil());
        response.setIdUsuario(perfil.getUsuario().getId());
        response.setNomeCompleto(perfil.getUsuario().getNome(),perfil.getUsuario().getSobrenome());
        response.setUsername(perfil.getUsername());
        response.setBiografia(perfil.getBiografia());
        response.setNota(perfil.getNota());
        response.setPremium(perfil.isPremium());

        return response;
    }

    public static UsuarioFullResponse toUsuarioFullResponse(Usuario usuario){
        UsuarioFullResponse usuarioFullResponse = new UsuarioFullResponse();

        usuarioFullResponse.setId(usuario.getId());
        usuarioFullResponse.setNome(usuario.getNome());
        usuarioFullResponse.setSobrenome(usuario.getSobrenome());
        usuarioFullResponse.setDtNascimento(usuario.getDtNascimento());
        usuarioFullResponse.setEmail(usuario.getEmail());
        usuarioFullResponse.setContato(usuario.getCelular());
        usuarioFullResponse.setDtCadastro(usuario.getDtCadastro());
        usuarioFullResponse.setIdentidadeGenero(usuario.getIdentidadeGenero());
        usuarioFullResponse.setDeleted(usuario.isDeleted());

        return usuarioFullResponse;

    }
    public static UsuarioInPerfilResponse toUsuarioInPerfilResponse(Usuario usuario){
        UsuarioInPerfilResponse usuarioInPerfilResponse = new UsuarioInPerfilResponse();

        usuarioInPerfilResponse.setId(usuario.getId());
        usuarioInPerfilResponse.setNome(usuario.getNome());
        usuarioInPerfilResponse.setSobrenome(usuario.getSobrenome());
        usuarioInPerfilResponse.setDtNascimento(usuario.getDtNascimento());
        usuarioInPerfilResponse.setEmail(usuario.getEmail());
        usuarioInPerfilResponse.setContato(usuario.getCelular());
        usuarioInPerfilResponse.setDtCadastro(usuario.getDtCadastro());
        usuarioInPerfilResponse.setIdentidadeGenero(usuario.getIdentidadeGenero());
        usuarioInPerfilResponse.setDeleted(usuario.isDeleted());

        return usuarioInPerfilResponse;

    }

    public static PerfilFullResponse toPerfilFullResponse(
            Perfil perfil,
            List<JogoInPerfilResponse> generoJogoList,
            UsuarioInPerfilResponse usuario,
            List<InteresseFullResponse> interesseList,
            List<ConsoleFullResponse> consoleList,
            List<MidiaFullResponse> midiaList
    ){

        PerfilFullResponse response = new PerfilFullResponse();

        response.setIdPerfil(perfil.getIdPerfil());
        response.setUsuario(usuario);
        response.setUsername(perfil.getUsername());
        response.setBiografia(perfil.getBiografia());
        response.setNota(perfil.getNota());
        response.setOrientacaoSexual(perfil.getOrientacaoSexual());
//        response.setProcuraAmizade(response.isProcuraAmizade());
//        response.setProcuraNamoro(perfil.isProcuraNamoro());
//        response.setProcuraPlayer2(perfil.isProcuraPlayer2());
        response.setPremium(response.isPremium());
        response.setGenerosJogos(generoJogoList);
        response.setInteresseList(interesseList);
        response.setConsoleList(consoleList);
        response.setMidiaList(midiaList);

        return response;
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

    public static InteresseFullResponse toInteresseFullResponse(Interesse interesse, boolean isVisible){
        InteresseFullResponse response = new InteresseFullResponse();

        response.setId(interesse.getIdInteresse());
        response.setNome(interesse.getNome());
        response.setDescricao(interesse.getDescricao());
        response.setVisible(isVisible);

        return response;
    }

    public static ConsoleFullResponse toConsoleFullResponse(Console console, boolean isVisible){
        ConsoleFullResponse response = new ConsoleFullResponse();

        response.setId(console.getId());
        response.setNome(console.getNome());
        response.setVisible(isVisible);

        return response;
    }

    public static MidiaFullResponse toMidiaFullResponse(Midia midia){
        MidiaFullResponse response = new MidiaFullResponse();

        response.setIdMidia(midia.getIdMidia());
        response.setIdPerfil(midia.getPerfil().getIdPerfil());
        response.setLink(midia.getLink());
        response.setVisible(midia.isVisivel());

        return response;
    }
}
