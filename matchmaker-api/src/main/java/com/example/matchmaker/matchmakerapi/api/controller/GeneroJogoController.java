package com.example.matchmaker.matchmakerapi.api.controller;

import com.example.matchmaker.matchmakerapi.entity.GeneroJogo;
import com.example.matchmaker.matchmakerapi.service.GeneroJogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genero-jogos")
@RequiredArgsConstructor
public class GeneroJogoController {
    private final GeneroJogoService generoJogoService;

    @GetMapping
    public List<GeneroJogo> getGeneroJogos(){
        return this.generoJogoService.getJogo();
    }
}
