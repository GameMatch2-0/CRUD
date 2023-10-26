package com.example.matchmaker.matchmakerapi.api.controller.mensagem;

import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import com.example.matchmaker.matchmakerapi.service.ConversaService;
import com.example.matchmaker.matchmakerapi.service.ListaObj;
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

    @PostMapping
    public ResponseEntity<Mensagem> enviarMensagem(@RequestBody Mensagem mensagem) {
        Mensagem mensagemEnviada = mensagemService.salvar(mensagem.getIdConversa()  , mensagem);

        if (mensagemEnviada.getCorpoMensagem().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(mensagemEnviada);
    }

    @PutMapping
    public ResponseEntity<Mensagem> atualizarMensagem(@RequestBody MensagemRequest mensagem) {
        Mensagem mensagemSalva = mensagemService.buscarPorIdMensagemAndIdConversa(mensagem.getIdMensagem(), mensagem.getIdConversa());
        if (mensagemSalva == null) {
            return ResponseEntity.notFound().build();
        }
        if (mensagemSalva.getCorpoMensagem().equals(mensagem.getCorpoMensagem())) {
            return ResponseEntity.status(409).build();
        }
        Mensagem mensagemAtualizada = mensagemService.atualizarMensagem(mensagem);
        return ResponseEntity.ok(mensagemAtualizada);
    }

    @DeleteMapping("/{idConversa}/{idMensagem}")
    public ResponseEntity<Mensagem> deletarMensagem(@PathVariable Integer idConversa, @PathVariable Long idMensagem) {
        Mensagem mensagemDeletada = mensagemService.deletar(idMensagem, idConversa);
        if (mensagemDeletada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    // Endpoint para trazer todas mensagens de uma conversa no modelo da ListaObj
//    @GetMapping("/todas/{idConversa}")
//    public ResponseEntity<List<Mensagem>> listarTodasMensagens(@PathVariable Integer idConversa) {
//        List<Mensagem> listaMensagens = mensagemService.listarTodasMensagens(idConversa);
//
//        if (listaMensagens.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//
//        return ResponseEntity.ok(listaMensagens);
//    }
    
    // Endpoint para trazer todas mensagens de uma conversa no modelo da ListaObj ordenando por data de envio
    @GetMapping("/todas/{idConversa}/ordenado")
    public ResponseEntity<List<Mensagem>> listarTodasMensagensPorData(@PathVariable Integer idConversa) {
        List<Mensagem> listaMensagens = mensagemService.listarTodasMensagensOrdenadasPorDataEnvio(idConversa);

        if (listaMensagens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listaMensagens);
    }
}
