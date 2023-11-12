package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.entity.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilService {
    private final PerfilRepository perfilRepository;

    public List<Perfil> getPerfil(){
        List<Perfil> perfilList = this.perfilRepository.findAll();

        if (perfilList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum perfil Cadastrado");
        }

        return perfilList;
    }
}
