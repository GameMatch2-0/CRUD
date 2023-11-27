package com.example.matchmaker.matchmakerapi.api.controller;

import com.example.matchmaker.matchmakerapi.entity.Midia;
import com.example.matchmaker.matchmakerapi.service.MidiaService;
import com.example.matchmaker.matchmakerapi.service.dto.request.NewMidiaRequest;
import com.example.matchmaker.matchmakerapi.service.dto.response.MidiaFullResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping
//    public ResponseEntity<NewMidiaRequest> newMidia(@RequestBody NewMidiaRequest newMidia){
//
//    }

}
