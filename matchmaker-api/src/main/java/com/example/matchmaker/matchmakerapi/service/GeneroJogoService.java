package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.api.GameIntegration;
import com.example.matchmaker.matchmakerapi.entity.GeneroJogo;
import com.example.matchmaker.matchmakerapi.entity.GeneroJogoPerfil;
import com.example.matchmaker.matchmakerapi.entity.repository.GeneroJogoRepository;
import com.example.matchmaker.matchmakerapi.service.dto.request.GameApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneroJogoService {
    private final GeneroJogoRepository generoJogoRepository;
    private final GeneroJogoPerfilService generoJogoPerfilService;
    private final GameIntegration gameIntegration;
    public List<GeneroJogo> getJogo(){
        return this.generoJogoRepository.findAll();
    }

    public GeneroJogo getGeneroJogoId(Integer id){
        return this.generoJogoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genero de jogo não encontrado")
        );
    }

    public List<GeneroJogo> getGeneroJogoByPerfilId(Long perfilId){
        List<GeneroJogoPerfil> generoJogoPerfils = this.generoJogoPerfilService.getGeneroIdByPerfilId(perfilId);
        List<GeneroJogo> generoJogoList = new ArrayList<>();

        generoJogoPerfils.forEach(it ->{
            generoJogoList.add(getGeneroJogoId(it.getId().getIdGeneroJogos()));
        });
        return generoJogoList;
    }

    public void addJogosApi(){
        GameApiRequest result = gameIntegration.getGames("da40d031fc32436faa42101ca4a33508");

        result.getResults().forEach(it ->{
            GeneroJogo generoJogo = new GeneroJogo();

            generoJogo.setIdGeneroJogos(it.getId());
            generoJogo.setNome(it.getName());
            generoJogo.setDescricao(it.getBackground_image());

            this.generoJogoRepository.save(generoJogo);
        });
    }

}
