package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Console;
import com.example.matchmaker.matchmakerapi.entity.ConsolePerfil;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.entity.embeddable.ConsolePerfilId;
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
        return this.consolePerfilRepository.findFirstById_IdPerfil(perfilId).isVisivel();
    }

    public List<Console> getConsoleListByPerfilId(Long perfilId){
        List<ConsolePerfil> consolePerfils = this.consolePerfilRepository.findAllById_IdPerfil(perfilId);

        List<Console> consoleList = new ArrayList<>();

        consolePerfils.forEach(it -> {
            consoleList.add(this.consoleService.findById(it.getId().getIdConsole()));
        });

        return consoleList;
    }

    public ConsolePerfil addConsolePerfil(Perfil perfil, Long consoleId, boolean isVisible){
        Console console = this.consoleService.findById(consoleId);

        ConsolePerfilId consolePerfilId = new ConsolePerfilId();
        consolePerfilId.setIdPerfil(perfil.getIdPerfil());
        consolePerfilId.setIdConsole(console.getId());

        ConsolePerfil consolePerfil = new ConsolePerfil();
        consolePerfil.setId(consolePerfilId);
        consolePerfil.setPerfil(perfil);
        consolePerfil.setConsole(console);
        consolePerfil.setVisivel(isVisible);

        return consolePerfil;
    }

    public List<ConsolePerfil> getAll(){
        return this.consolePerfilRepository.findAll();
    }
}
