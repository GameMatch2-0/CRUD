package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Plano;
import com.example.matchmaker.matchmakerapi.entity.repository.PlanoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanoService {
    private final PlanoRepository planoRepository;

    public List<Plano> getPlano(){
        List<Plano> planoList = this.planoRepository.findAll();

        if (planoList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum plano encontrado");
        }

        return planoList;
    }
}
