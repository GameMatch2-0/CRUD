package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Interesse;
import com.example.matchmaker.matchmakerapi.entity.InteressePerfil;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.entity.embeddable.InteressePerfilId;
import com.example.matchmaker.matchmakerapi.entity.repository.InteressePerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InteressePerfilService {
    private final InteressePerfilRepository interessePerfilRepository;
    private final InteresseService interesseService;

    public List<InteressePerfil> getInteeressePerfil(){
        return this.interessePerfilRepository.findAll();
    }

    public boolean isVisibleByPerfilId(Long perfilId){
        return this.interessePerfilRepository.findFirstById_PerfilId(perfilId).isVisible();
    }

    public List<Interesse> getInteresseByPerfilId(Long perfilId){
        List<InteressePerfil> interessePerfils = this.interessePerfilRepository.findAllById_PerfilId(perfilId);

        List<Interesse> interesseList = new ArrayList<>();

        interessePerfils.forEach(it -> {
            interesseList.add(this.interesseService.findById(it.getId().getInteresseId()));
        });

        return interesseList;
    }

    public InteressePerfil addInteressePerfil(Perfil perfil, Long interesseId, boolean isVisible){
        Interesse interesse = this.interesseService.findById(interesseId);

        InteressePerfilId interessePerfilId = new InteressePerfilId();
        interessePerfilId.setPerfilId(perfil.getIdPerfil());
        interessePerfilId.setInteresseId(interesse.getIdInteresse());

        InteressePerfil interessePerfil = new InteressePerfil();
        interessePerfil.setId(interessePerfilId);
        interessePerfil.setPerfil(perfil);
        interessePerfil.setInteresse(interesse);
        interessePerfil.setVisible(isVisible);

        return interessePerfil;
    }
}