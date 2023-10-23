package com.example.matchmaker.matchmakerapi.api.controller.mensagem;

import com.example.matchmaker.matchmakerapi.service.ConversaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversas/mensagens")
@RequiredArgsConstructor
public class MensagemController {
    private final ConversaService conversaService;

    @GetMapping
    public void getMensagens(){

    }

}
