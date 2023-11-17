package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.GeneroJogo;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.entity.repository.PerfilRepository;
import com.example.matchmaker.matchmakerapi.service.dto.response.PerfilFullResponse;
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

    public List<PerfilFullResponse> getPerfil() {
        List<PerfilFullResponse> responseMapperList = new ArrayList<>();
        List<Perfil> perfilList = this.perfilRepository.findAll();
        if (perfilList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum perfil Cadastrado");
        }

        perfilList.forEach(it ->{
            List<GeneroJogo> generoJogoList;
            generoJogoList = getGeneroJogosPorPerfilId(it.getIdPerfil());
            responseMapperList.add(ResponseMapper.toPerfilFullResponse(it ,generoJogoList));
        });

        return responseMapperList;
    }

    public Perfil getPerfilId(Long id) {
        return this.perfilRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<GeneroJogo> getGeneroJogosPorPerfilId(Long id) {
        List<GeneroJogo> generoJogoList = new ArrayList<>();
        Perfil perfil = getPerfilId(id);

        perfil.getGenerosJogosPerfil().forEach(it ->{
            generoJogoList.add(this.generoJogoService.getGeneroJogoId(it.getId().getIdGeneroJogos()));
        });
        return generoJogoList;
    }
}
