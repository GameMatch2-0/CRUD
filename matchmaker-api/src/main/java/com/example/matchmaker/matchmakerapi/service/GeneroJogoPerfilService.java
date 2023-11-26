package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.GeneroJogoPerfil;
import com.example.matchmaker.matchmakerapi.entity.repository.GeneroJogoPerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneroJogoPerfilService {
    private final GeneroJogoPerfilRepository generoJogoPerfilRepository;

    public List<GeneroJogoPerfil> getGeneroJogoPerfil(){
        List<GeneroJogoPerfil> generoJogoPerfilList = this.generoJogoPerfilRepository.findAll();

        //findfirstby - inplemmentar a logica e trocar essa merda de gambiarra do krlh

        if (generoJogoPerfilList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum perfil atribuido a genero");
        }

        return generoJogoPerfilList;
    }

    public boolean getIsVisibleByPerfilId(Long perfilId){
        List<GeneroJogoPerfil> list = this.generoJogoPerfilRepository.findAllByIdIdPerfil(perfilId);
        if (list.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Genero de jogo vinculado a este perfil");
        }
        return list.get(0).isVisivel();
    }

    public List<GeneroJogoPerfil> getGeneroIdByPerfilId(Long perfilId){
        List<GeneroJogoPerfil> list = this.generoJogoPerfilRepository.findAllByIdIdPerfil(perfilId);
        if (list.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Genero de jogo vinculado a este perfil");
        }
        return list;
    }
}
