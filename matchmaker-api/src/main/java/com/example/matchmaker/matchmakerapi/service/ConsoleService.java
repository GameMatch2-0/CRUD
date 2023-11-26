package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Console;
import com.example.matchmaker.matchmakerapi.entity.repository.ConsoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsoleService {
    private final ConsoleRepository consoleRepository;

    public List<Console> getConsole(){
        List<Console> consoleList = this.consoleRepository.findAll();

        if (consoleList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum console cadastrado");
        }

        return consoleList;
    }

    public Console findById(Long id){
        return this.consoleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Console não encontrado")
        );
    }
}
