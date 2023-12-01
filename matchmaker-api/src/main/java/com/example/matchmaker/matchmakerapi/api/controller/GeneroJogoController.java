package com.example.matchmaker.matchmakerapi.api.controller;

import com.example.matchmaker.matchmakerapi.api.GameIntegration;
import com.example.matchmaker.matchmakerapi.entity.GeneroJogo;
import com.example.matchmaker.matchmakerapi.service.GeneroJogoService;
import com.example.matchmaker.matchmakerapi.service.dto.request.GameApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genero-jogos")
@RequiredArgsConstructor
public class GeneroJogoController {
    private final GeneroJogoService generoJogoService;

    @Autowired
    private GameIntegration gameIntegration;
    @GetMapping("/get-all")
    public ResponseEntity<GameApiRequest> getGames(){
        GameApiRequest result = gameIntegration.getGames("da40d031fc32436faa42101ca4a33508");
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public List<GeneroJogo> getGeneroJogos(){
        return this.generoJogoService.getJogo();
    }


}
