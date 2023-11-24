package com.example.matchmaker.matchmakerapi.api.controller;

import com.example.matchmaker.matchmakerapi.entity.InteressePerfil;
import com.example.matchmaker.matchmakerapi.service.InteressePerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/interesses-perfis")
@RequiredArgsConstructor
public class InteressePerfilController {
    private final InteressePerfilService interessePerfilService;

    @GetMapping
    public List<InteressePerfil> getInteressePerfil(){
        return this.interessePerfilService.getInteeressePerfil();
    }
}
