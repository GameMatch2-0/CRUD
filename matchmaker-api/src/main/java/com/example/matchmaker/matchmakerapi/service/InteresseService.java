package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Interesse;
import com.example.matchmaker.matchmakerapi.entity.repository.InteresseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InteresseService {
    private final InteresseRepository interesseRepository;

    public List<Interesse> getInteresse(){
        List<Interesse> interesseList = this.interesseRepository.findAll();

        if (interesseList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Lista vazia");
        }

        return interesseList;
    }

    public Interesse findById(Long id){
        return this.interesseRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Interesse não encontrado")
        );
    }
}
