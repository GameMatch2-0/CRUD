package com.example.matchmaker.matchmakerapi.api.controller;

import com.example.matchmaker.matchmakerapi.entity.Midia;
import com.example.matchmaker.matchmakerapi.service.MidiaService;
import com.example.matchmaker.matchmakerapi.service.dto.response.MidiaFullResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/midias")
@RequiredArgsConstructor
public class MidiaController {
    private final MidiaService midiaService;

    @GetMapping
    public ResponseEntity<List<MidiaFullResponse>> getAllMidia(){
        List<Midia> midiaList = this.midiaService.getAllMidia();
        List<MidiaFullResponse> midiaFullResponseList = new ArrayList<>();

        midiaList.forEach(it ->{
            midiaFullResponseList.add(ResponseMapper.toMidiaFullResponse(it));
        });

        return ResponseEntity.ok(midiaFullResponseList);
    }
}
