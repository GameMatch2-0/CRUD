package com.example.matchmaker.matchmakerapi.service.dto.request.mapper;

import com.example.matchmaker.matchmakerapi.entity.Midia;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.entity.Usuario;
import com.example.matchmaker.matchmakerapi.service.authentication.dto.UsuarioTokenDto;
import com.example.matchmaker.matchmakerapi.service.dto.request.NewMidiaRequest;
import com.example.matchmaker.matchmakerapi.service.dto.request.NewPerfilRequest;
import com.example.matchmaker.matchmakerapi.service.dto.request.UsuarioRequest;

public class RequestMapper {

    public static Usuario toUsuario(UsuarioRequest usuarioRequest){
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioRequest.getNome());
        usuario.setSobrenome(usuarioRequest.getSobrenome());
        usuario.setIdentidadeGenero(usuarioRequest.getIdentidadeGenero());
        usuario.setDtNascimento(usuarioRequest.getDtNascimento());
        usuario.setCelular(usuarioRequest.getContato());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(usuarioRequest.getSenha());

        return usuario;
    }

    public static UsuarioTokenDto toUsuarioTokenDto(Usuario usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }

    public static Midia toMidia(NewMidiaRequest newMidiaRequest){
        Midia midia = new Midia();

        midia.setLink(newMidiaRequest.getLink());
        midia.setVisivel(newMidiaRequest.isVisible());

        return midia;
    }

    public static Perfil toPerfil(NewPerfilRequest newPerfilRequest,Usuario usuario){
        Perfil perfil = new Perfil();

        perfil.setUsuario(usuario);
        perfil.setUsername(newPerfilRequest.getUsername());
        perfil.setBiografia(newPerfilRequest.getBiografia());
        perfil.setNota(newPerfilRequest.getNota());
        perfil.setOrientacaoSexual(newPerfilRequest.getOrientacaoSexual());
        perfil.setProcuraAmizade(newPerfilRequest.isProcuraAmizade());
        perfil.setProcuraNamoro(newPerfilRequest.isProcuraNamoro());
        perfil.setProcuraPlayer2(newPerfilRequest.isProcuraPlayer2());

        return perfil;
    }

}
