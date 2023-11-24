package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.InteressePerfil;
import com.example.matchmaker.matchmakerapi.entity.repository.InteressePerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InteressePerfilService {
    private final InteressePerfilRepository interessePerfilRepository;

    public List<InteressePerfil> getInteeressePerfil(){
        return this.interessePerfilRepository.findAll();
    }
}
