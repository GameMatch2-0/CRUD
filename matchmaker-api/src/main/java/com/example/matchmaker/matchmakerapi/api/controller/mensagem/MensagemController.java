package com.example.matchmaker.matchmakerapi.api.controller.mensagem;

import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import com.example.matchmaker.matchmakerapi.service.ConversaService;
import com.example.matchmaker.matchmakerapi.service.MensagemService;
import com.example.matchmaker.matchmakerapi.service.dto.request.MensagemRequest;
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
    @GetMapping("/{idConversa}")
    public ResponseEntity<List<Mensagem>> listarMensagens(@PathVariable Integer idConversa) {
        List<Mensagem> listaMensagens = mensagemService.getUltimas30Mensagens(idConversa);

        if (listaMensagens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listaMensagens);
    }

    @PostMapping("/{idConversa}")
    public ResponseEntity<Mensagem> enviarMensagem(@PathVariable Integer idConversa, @RequestBody Mensagem mensagem) {
        Mensagem mensagemEnviada = mensagemService.salvar(idConversa, mensagem);
        return ResponseEntity.ok(mensagemEnviada);
    }

    @PutMapping("/{idConversa}")
    public ResponseEntity<Mensagem> atualizarMensagem(@PathVariable Integer idConversa, @RequestBody MensagemRequest mensagem) {
        Mensagem mensagemSalva = mensagemService.buscarPorIdMensagemAndIdConversa(mensagem.getIdMensagem(), idConversa);
        if (mensagemSalva.getCorpoMensagem().equals(mensagem.getCorpoMensagem())) {
            return ResponseEntity.status(409).build();
        }
        Mensagem mensagemAtualizada = mensagemService.atualizarMensagem(mensagem);
        return ResponseEntity.ok(mensagemAtualizada);
    }

    @DeleteMapping("/{idConversa}")
    public ResponseEntity<Mensagem> deletarMensagem(@PathVariable Integer idConversa, @PathVariable Long idMensagem) {
        Mensagem mensagemDeletada = mensagemService.deletar(idMensagem, idConversa);
        return ResponseEntity.ok(mensagemDeletada);
    }

}
