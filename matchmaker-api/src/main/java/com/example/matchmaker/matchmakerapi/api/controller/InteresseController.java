package com.example.matchmaker.matchmakerapi.api.controller;

import com.example.matchmaker.matchmakerapi.entity.Interesse;
import com.example.matchmaker.matchmakerapi.service.InteresseService;
import com.example.matchmaker.matchmakerapi.service.dto.response.InteresseFullResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/interesses")
@RequiredArgsConstructor
public class InteresseController {
    private final InteresseService interesseService;
    @GetMapping
    public ResponseEntity<List<InteresseFullResponse>> getInteresse(){
        List<Interesse> interesseList = this.interesseService.getInteresse();
        List<InteresseFullResponse> interesseFullResponseList = new ArrayList<>();

        interesseList.forEach(it -> {
            interesseFullResponseList.add(ResponseMapper.toInteresseFullResponse(it));
        });

        return ResponseEntity.ok(interesseFullResponseList);
    }

}
