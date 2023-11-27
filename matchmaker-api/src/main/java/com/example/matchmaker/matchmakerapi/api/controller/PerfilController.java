package com.example.matchmaker.matchmakerapi.api.controller;

import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.service.PerfilService;
import com.example.matchmaker.matchmakerapi.service.dto.request.NewMidiaRequest;
import com.example.matchmaker.matchmakerapi.service.dto.response.PerfilFullResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/perfis")
public class PerfilController {

    private final PerfilService perfilService;

    @GetMapping
    public List<PerfilFullResponse> getPerfil(){
        List<PerfilFullResponse> perfilList = this.perfilService.getPerfil();
        return perfilList;
    }

    @PutMapping("/{perfilId}/midias")
    public ResponseEntity<String> atualizarMidiasDoPerfil(
            @PathVariable String perfilId,
            @RequestBody List<NewMidiaRequest> midias) {

        this.perfilService.atualizarMidiasDoPerfil(Long.valueOf(perfilId), midias);

        return ResponseEntity.ok("Mídias atualizadas com sucesso!");
    }


}
