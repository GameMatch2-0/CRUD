package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.GeneroJogoPerfil;
import com.example.matchmaker.matchmakerapi.entity.repository.GeneroJogoPerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneroJogoPerfilService {
    private final GeneroJogoPerfilRepository generoJogoPerfilRepository;

    public List<GeneroJogoPerfil> getGeneroJogoPerfil(){
        return this.generoJogoPerfilRepository.findAll();
    }
}
