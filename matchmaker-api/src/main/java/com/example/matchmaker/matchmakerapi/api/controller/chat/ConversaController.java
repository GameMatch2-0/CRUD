package com.example.matchmaker.matchmakerapi.api.controller.chat;

import com.example.matchmaker.matchmakerapi.entity.Conversa;
import com.example.matchmaker.matchmakerapi.entity.Usuario;
import com.example.matchmaker.matchmakerapi.service.ConversaService;
import com.example.matchmaker.matchmakerapi.service.UsuarioService;
import com.example.matchmaker.matchmakerapi.service.authentication.dto.UsuarioTokenDto;
import com.example.matchmaker.matchmakerapi.service.dto.response.ConversaFullResponse;
import com.example.matchmaker.matchmakerapi.service.dto.response.UsuarioFullResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversas")
@RequiredArgsConstructor
public class ConversaController {
    private final ConversaService conversaService;

    @GetMapping("/{idUsuarioLogado}")
    public ResponseEntity<List<ConversaFullResponse>> getConversas(@PathVariable String idUsuarioLogado){
        List<ConversaFullResponse> conversaLista = this.conversaService.listarConversas(idUsuarioLogado);

        if (conversaLista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(conversaLista);
    }

    @PostMapping("/{idUsuarioLogado}/{idUsuarioConversa}")
    public ResponseEntity<ConversaFullResponse> novaConversa(@PathVariable String idUsuarioLogado, @PathVariable String idUsuarioConversa){
        if (idUsuarioLogado.equals(idUsuarioConversa)){
            ResponseEntity.status(409).build();
        }
        ConversaFullResponse conversa = this.conversaService.novaConversa(idUsuarioLogado, idUsuarioConversa);
        return ResponseEntity.ok(conversa);
    }

    @DeleteMapping("/{idConversa}")
    public ResponseEntity<ConversaFullResponse> deletarConversa(@PathVariable Integer idConversa){
        ConversaFullResponse conversa = this.conversaService.deletarConversa(idConversa);

        if (conversa == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}
