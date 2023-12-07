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
    public ResponseEntity<List<ConversaFullResponse>> getConversas(@PathVariable Long idPerfil){
        List<ConversaFullResponse> conversaLista = this.conversaService.listarConversas(idPerfil);

        if (conversaLista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(conversaLista);
    }

    @PostMapping("/{idUsuarioLogado}/{idUsuarioConversa}")
    public ResponseEntity<ConversaFullResponse> novaConversa(@PathVariable Long idPerfilLogado, @PathVariable Long idPerfilConversa){
        if (idPerfilLogado.equals(idPerfilConversa)){
            ResponseEntity.status(409).build();
        }
        ConversaFullResponse conversa = this.conversaService.novaConversa(idPerfilLogado, idPerfilConversa);
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
