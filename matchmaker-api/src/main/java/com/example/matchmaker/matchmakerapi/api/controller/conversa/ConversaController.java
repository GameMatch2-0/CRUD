package com.example.matchmaker.matchmakerapi.api.controller.conversa;

import com.example.matchmaker.matchmakerapi.entity.Conversa;
import com.example.matchmaker.matchmakerapi.entity.Usuario;
import com.example.matchmaker.matchmakerapi.service.ConversaService;
import com.example.matchmaker.matchmakerapi.service.authentication.dto.UsuarioTokenDto;
import com.example.matchmaker.matchmakerapi.service.dto.response.ConversaFullResponse;
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
    public ResponseEntity<ConversaFullResponse> novaConversa(@PathVariable String idUsuarioLogado, String idUsuarioConversa){
        ConversaFullResponse conversa = this.conversaService.novaConversa(idUsuarioLogado, idUsuarioConversa);
        return ResponseEntity.ok(conversa);
    }

    @DeleteMapping("/{idConversa}")
    public ResponseEntity<ConversaFullResponse> deletarConversa(Integer idConversa){
        ConversaFullResponse conversa = this.conversaService.deletarConversa(idConversa);
        return ResponseEntity.ok(conversa);
    }

}
