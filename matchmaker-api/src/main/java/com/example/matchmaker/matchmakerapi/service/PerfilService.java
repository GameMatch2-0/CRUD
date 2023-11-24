package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.GeneroJogo;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.entity.Usuario;
import com.example.matchmaker.matchmakerapi.entity.repository.PerfilRepository;
import com.example.matchmaker.matchmakerapi.service.dto.response.JogoInPerfilResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.PerfilFullResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.UsuarioInPerfilResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilService {
    private final PerfilRepository perfilRepository;
    private final GeneroJogoService generoJogoService;
    private final GeneroJogoPerfilService generoJogoPerfilService;
    private final UsuarioService usuarioService;

    public List<PerfilFullResponse> getPerfil() {
        List<PerfilFullResponse> responseMapperList = new ArrayList<>();
        List<Perfil> perfilList = this.perfilRepository.findAll();
        if (perfilList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum perfil Cadastrado");
        }

        perfilList.forEach(it ->{
            Usuario usuario = this.usuarioService.buscarPorId(it.getUsuario().getId());
            UsuarioInPerfilResponse user = ResponseMapper.toUsuarioInPerfilResponse(usuario);

            List<JogoInPerfilResponse> generoJogoList;
            generoJogoList = getGeneroJogosPorPerfilId(it.getIdPerfil());
            responseMapperList.add(ResponseMapper.toPerfilFullResponse(it ,generoJogoList, user));
        });

        return responseMapperList;
    }

    public Perfil getPerfilId(Long id) {
        return this.perfilRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<JogoInPerfilResponse> getGeneroJogosPorPerfilId(Long perfilId) {
        List<JogoInPerfilResponse> generoJogoList = new ArrayList<>();
        boolean isVisible = this.generoJogoPerfilService.getIsVisibleByPerfilId(perfilId);

        List<GeneroJogo> generoJogos = this.generoJogoService.getGeneroJogoByPerfilId(perfilId);
        generoJogos.forEach(it -> {
            generoJogoList.add(ResponseMapper.toJogoInPerfilResponse(it, isVisible));
        });

        return generoJogoList;
    }
}
