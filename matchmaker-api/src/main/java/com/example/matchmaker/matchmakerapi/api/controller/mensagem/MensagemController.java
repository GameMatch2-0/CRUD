package com.example.matchmaker.matchmakerapi.api.controller.mensagem;

import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import com.example.matchmaker.matchmakerapi.service.ConversaService;
import com.example.matchmaker.matchmakerapi.service.MensagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversas/mensagens")
@RequiredArgsConstructor
public class MensagemController {
    private final MensagemService mensagemService;

    // Endpoint para listar as ultimas 30 mensagens de uma conversa ao entrar nela
    @GetMapping
    public ResponseEntity<List<Mensagem>> listarMensagens(@RequestParam Integer idConversa) {
        List<Mensagem> listaMensagens = mensagemService.getUltimas30Mensagens(idConversa);

        if (listaMensagens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listaMensagens);
    }

    @PostMapping("/{idConversa}/{idUsuario}")
    public ResponseEntity<Mensagem> enviarMensagem(@PathVariable Integer idConversa, @PathVariable String idUsuario, @RequestBody Mensagem mensagem) {
        Mensagem mensagemEnviada = mensagemService.salvar(mensagem);
        return ResponseEntity.ok(mensagemEnviada);
    }

    @PutMapping

    @DeleteMapping
    public ResponseEntity<Mensagem> deletarMensagem(@RequestBody Mensagem mensagem) {
        Mensagem mensagemDeletada = mensagemService.deletar(mensagem);
        return ResponseEntity.ok(mensagemDeletada);
    }

}
