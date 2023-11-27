package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Midia;
import com.example.matchmaker.matchmakerapi.entity.repository.MidiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MidiaService {
    private final MidiaRepository midiaRepository;

    public List<Midia> getAllMidia(){
        List<Midia> midiaList = this.midiaRepository.findAll();

        if (midiaList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhuma midia listada");
        }

        return midiaList;
    }

    public List<Midia> getMidiaByPerfilId(Long perfilId){
        List<Midia> midiaList = this.midiaRepository.findAllByPerfil_IdPerfil(perfilId);

        if (midiaList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,"Nenhuma Midia para este usuario");
        }

        return midiaList;
    }
}
