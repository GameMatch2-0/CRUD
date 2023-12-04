package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Midia;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.entity.repository.MidiaRepository;
import com.example.matchmaker.matchmakerapi.service.dto.request.NewMidiaRequest;
import com.example.matchmaker.matchmakerapi.service.dto.response.MidiaFullResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ResponseMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
    @Transactional
    public List<MidiaFullResponse> addMidia(Perfil perfil, List<NewMidiaRequest> newMidiaRequests){
        this.midiaRepository.deleteAllByPerfil_IdPerfil(perfil.getIdPerfil());
        List<MidiaFullResponse> midiaFullResponses = new ArrayList<>();

        newMidiaRequests.forEach(it -> {
            Midia midia = new Midia();

            midia.setPerfil(perfil);
            midia.setLink(it.getLink());
            midia.setVisible(it.isVisible());

            midiaRepository.save(midia);
            midiaFullResponses.add(ResponseMapper.toMidiaFullResponse(midia));
        });

        return midiaFullResponses;
    }
}
