package com.example.matchmaker.matchmakerapi.api.controller;

import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.service.PerfilService;
import com.example.matchmaker.matchmakerapi.service.dto.response.PerfilFullResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
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
        List<Perfil> perfilList = this.perfilService.getPerfil();
        List<PerfilFullResponse> perfilFullResponseList = new ArrayList<>();
        perfilList.forEach(it ->{
            perfilFullResponseList.add(ResponseMapper.toPerfilFullResponse(it));
        });
        return perfilFullResponseList;
    }


}
