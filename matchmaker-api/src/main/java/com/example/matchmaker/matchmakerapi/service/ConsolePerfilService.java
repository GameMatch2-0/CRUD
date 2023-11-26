package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Console;
import com.example.matchmaker.matchmakerapi.entity.ConsolePerfil;
import com.example.matchmaker.matchmakerapi.entity.repository.ConsolePerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsolePerfilService {
    private final ConsolePerfilRepository consolePerfilRepository;
    private final ConsoleService consoleService;
    public boolean getIsVisibleByPerfilId(Long perfilId){
        return this.consolePerfilRepository.findFirstById_PerfilId(perfilId).isVisible();
    }

    public List<Console> getConsoleListByPerfilId(Long perfilId){
        List<ConsolePerfil> consolePerfils = this.consolePerfilRepository.findAllById_PerfilId(perfilId);

        List<Console> consoleList = new ArrayList<>();

        consolePerfils.forEach(it -> {
            consoleList.add(this.consoleService.findById(it.getId().getConsoleId()));
        });

        return consoleList;
    }
}
