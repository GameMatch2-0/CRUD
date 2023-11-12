package com.example.matchmaker.matchmakerapi.api.controller;

import com.example.matchmaker.matchmakerapi.entity.Plano;
import com.example.matchmaker.matchmakerapi.service.PlanoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planos")
public class PlanoController {
    private final PlanoService planoService;

    @GetMapping
    public List<Plano> getPlano(){
        return this.planoService.getPlano();
    }
}
