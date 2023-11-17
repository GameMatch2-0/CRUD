package com.example.matchmaker.matchmakerapi.api.controller;

import com.example.matchmaker.matchmakerapi.entity.GeneroJogoPerfil;
import com.example.matchmaker.matchmakerapi.service.GeneroJogoPerfilService;
import com.example.matchmaker.matchmakerapi.service.dto.response.GeneroJogoPerfilResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/genero-jogos-perfis")
@RequiredArgsConstructor
public class GeneroJogoPerfilController {
    private final GeneroJogoPerfilService generoJogoPerfilService;

    @GetMapping
    public List<GeneroJogoPerfilResponse> getGeneroJogoPerfil(){
        List<GeneroJogoPerfil> generoJogoPerfilList = generoJogoPerfilService.getGeneroJogoPerfil();
        List<GeneroJogoPerfilResponse> responseList = new ArrayList<>();

        generoJogoPerfilList.forEach(it -> {
            responseList.add(ResponseMapper.toGeneroJogoPerfilResponse(it));
        });

        return responseList;
    }
}
