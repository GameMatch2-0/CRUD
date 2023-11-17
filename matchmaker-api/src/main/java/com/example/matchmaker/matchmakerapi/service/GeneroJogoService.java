package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.GeneroJogo;
import com.example.matchmaker.matchmakerapi.entity.repository.GeneroJogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneroJogoService {
    private final GeneroJogoRepository generoJogoRepository;
    public List<GeneroJogo> getJogo(){
        return this.generoJogoRepository.findAll();
    }

    public GeneroJogo getGeneroJogoId(Long id){
        return this.generoJogoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genero de jogo não encontrado")
        );
    }
}
