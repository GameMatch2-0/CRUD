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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InteressePerfilService {
    private final InteressePerfilRepository interessePerfilRepository;
    private final InteresseService interesseService;

    public List<InteressePerfil> getInteeressePerfil(){
        return this.interessePerfilRepository.findAll();
    }

    public boolean isVisibleByPerfilId(Long perfilId){
        Optional<InteressePerfil> interessePerfilOpt = this.interessePerfilRepository.findFirstById_IdPerfil(perfilId);

        if (interessePerfilOpt.isPresent()){
            return true;
        }else {
            return false;
        }
    }

    public List<Interesse> getInteresseByPerfilId(Long perfilId){
        List<InteressePerfil> interessePerfils = this.interessePerfilRepository.findAllById_IdPerfil(perfilId);

        List<Interesse> interesseList = new ArrayList<>();

        interessePerfils.forEach(it -> {
            interesseList.add(this.interesseService.findById(it.getId().getIdInteresses()));
        });

        return interesseList;
    }

    public InteressePerfil addInteressePerfil(Perfil perfil, Long interesseId, boolean isVisible){
        Interesse interesse = this.interesseService.findById(interesseId);

        InteressePerfilId interessePerfilId = new InteressePerfilId();
        interessePerfilId.setIdPerfil(perfil.getIdPerfil());
        interessePerfilId.setIdInteresses(interesse.getIdInteresse());

        InteressePerfil interessePerfil = new InteressePerfil();
        interessePerfil.setId(interessePerfilId);
        interessePerfil.setPerfil(perfil);
        interessePerfil.setInteresse(interesse);
        interessePerfil.setVisivel(isVisible);

        return interessePerfil;
    }
}
