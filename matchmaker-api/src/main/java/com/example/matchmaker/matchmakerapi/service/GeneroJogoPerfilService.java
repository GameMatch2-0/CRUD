package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.ConsolePerfil;
import com.example.matchmaker.matchmakerapi.entity.GeneroJogo;
import com.example.matchmaker.matchmakerapi.entity.GeneroJogoPerfil;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.entity.embeddable.GeneroJogoPerfilId;
import com.example.matchmaker.matchmakerapi.entity.repository.GeneroJogoPerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneroJogoPerfilService {
    private final GeneroJogoPerfilRepository generoJogoPerfilRepository;

    public List<GeneroJogoPerfil> getGeneroJogoPerfil(){
        List<GeneroJogoPerfil> generoJogoPerfilList = this.generoJogoPerfilRepository.findAll();
        //findfirstby - inplemmentar a logica e trocar essa merda de gambiarra do krlh

        return generoJogoPerfilList;
    }

    public boolean getIsVisibleByPerfilId(Long perfilId){
        Optional<GeneroJogoPerfil> generoJogoPerfilOpt = this.generoJogoPerfilRepository.findFirstById_IdPerfil(perfilId);

        if (generoJogoPerfilOpt.isPresent()){
            return true;
        }else {
            return false;
        }
    }

    public List<GeneroJogoPerfil> getGeneroIdByPerfilId(Long perfilId){
        return this.generoJogoPerfilRepository.findAllByIdIdPerfil(perfilId);

    }

    public GeneroJogoPerfil addGeneroJogoPerfil(Perfil perfil, GeneroJogo generoJogo, boolean isVisible){
        GeneroJogoPerfilId generoJogoPerfilId = new GeneroJogoPerfilId();
        generoJogoPerfilId.setIdGeneroJogos(generoJogo.getIdGeneroJogos());
        generoJogoPerfilId.setIdPerfil(perfil.getIdPerfil());

        GeneroJogoPerfil generoJogoPerfil = new GeneroJogoPerfil();
        generoJogoPerfil.setId(generoJogoPerfilId);
        generoJogoPerfil.setPerfil(perfil);
        generoJogoPerfil.setGeneroJogos(generoJogo);
        generoJogoPerfil.setVisivel(isVisible);

        generoJogoPerfilRepository.save(generoJogoPerfil);
        return null;
    }
}
