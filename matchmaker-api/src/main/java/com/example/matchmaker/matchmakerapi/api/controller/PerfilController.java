package com.example.matchmaker.matchmakerapi.api.controller;

import com.example.matchmaker.matchmakerapi.FilaObj;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.service.PerfilService;
import com.example.matchmaker.matchmakerapi.service.dto.request.NewMidiaRequest;
import com.example.matchmaker.matchmakerapi.service.dto.request.NewUserRequest;
import com.example.matchmaker.matchmakerapi.service.dto.response.PerfilFullResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.PerfilShortResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/perfis")
public class PerfilController {

    private final PerfilService perfilService;

    @GetMapping("/{id}")
    public ResponseEntity<PerfilShortResponse> getShortPerfil(@PathVariable Long id){
        Perfil perfil = this.perfilService.getPerfilId(id);

        return ResponseEntity.ok(ResponseMapper.toPerfilShortResponse(perfil));
    }


    @PutMapping("/{perfilId}/midias")
    public ResponseEntity<String> atualizarMidiasDoPerfil(
            @PathVariable String perfilId,
            @RequestBody List<NewMidiaRequest> midias) {

        this.perfilService.atualizarMidiasDoPerfil(Long.valueOf(perfilId), midias);

        return ResponseEntity.ok("Mídias atualizadas com sucesso!");
    }

    @PostMapping("/novo-cadastro")
    public ResponseEntity<PerfilFullResponse> novoUsuario(@RequestBody NewUserRequest newUserRequest){
        PerfilFullResponse perfil = this.perfilService.novoCadastro(newUserRequest);
//        return ResponseEntity.ok(perfil);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/{perfilId}/curtidas/{idPerfilCurtido}")
    public ResponseEntity<String> curtirPerfil(
            @PathVariable Integer perfilId,
            @PathVariable Integer idPerfilCurtido
    ){
        this.perfilService.curtirPerfil(perfilId, idPerfilCurtido);
        return ResponseEntity.ok("Perfil curtido com sucesso");
    }

    @PostMapping("/{perfilId}/curtidas/{idPerfilDescurtido}/descurtir")
    public ResponseEntity<String> descurtirPerfil(
            @PathVariable Integer perfilId,
            @PathVariable Integer idPerfilDescurtido
    ){
        this.perfilService.descurtirPerfil(perfilId, idPerfilDescurtido);
        return ResponseEntity.ok("Perfil descurtido com sucesso");
    }

    @GetMapping("/{perfilId}/cards")
    public ResponseEntity<FilaObj<PerfilFullResponse>> getCardsPerfil(@PathVariable Integer perfilId){
        FilaObj filaCards = this.perfilService.getCardsPerfil(perfilId);
        return ResponseEntity.ok(filaCards);
    }
    @GetMapping
    public List<PerfilFullResponse> getPerfil(){
        List<PerfilFullResponse> perfilList = this.perfilService.getPerfil();
        return perfilList;
    }

    @GetMapping("/{perfilId}/amigos")
    public ResponseEntity<List<String>> getAmigosPerfil(@PathVariable Integer perfilId){
        List<String> listaAmigos = this.perfilService.getAmigos(perfilId);
        return ResponseEntity.ok(listaAmigos);
    }


}
