package com.example.matchmaker.matchmakerapi.api.controller.usuario;

import com.example.matchmaker.matchmakerapi.entity.Usuario;
import com.example.matchmaker.matchmakerapi.service.authentication.dto.UsuarioLoginDto;
import com.example.matchmaker.matchmakerapi.service.authentication.dto.UsuarioTokenDto;
import com.example.matchmaker.matchmakerapi.service.dto.request.UsuarioRequest;
import com.example.matchmaker.matchmakerapi.service.dto.response.UsuarioFullResponse;
import com.example.matchmaker.matchmakerapi.service.UsuarioService;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.UsuarioResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login (@RequestBody UsuarioLoginDto usuarioLoginDto){
        UsuarioTokenDto usuarioToken = this.usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.ok(usuarioToken);
    }

    @PostMapping("/logoff/{id}")
    public ResponseEntity<Usuario> logoff (@PathVariable String id){
        this.usuarioService.logof(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioFullResponse>> listar() {
        List<UsuarioFullResponse> usuarioFullResponseList = this.usuarioService.listar();

        if (usuarioFullResponseList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarioFullResponseList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioFullResponse> buscar(@PathVariable String id) {
        UsuarioFullResponse usuarioFullResponse = this.usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuarioFullResponse);
    }

    @GetMapping("/apagados")
    public ResponseEntity<List<UsuarioFullResponse>> listarApagados() {
        List<UsuarioFullResponse> usuarioList = this.usuarioService.listarApagados();

        if (usuarioList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarioList);
    }

    @PostMapping()
    public ResponseEntity<Void> cadastrar(@RequestBody UsuarioRequest usuarioRequest) {
        Optional<Usuario> usuarioOptional = this.usuarioService.buscarPorEmail(usuarioRequest.getEmail());

        if (usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        if (!this.usuarioService.validaUsuario(usuarioRequest)) {
            return ResponseEntity.badRequest().build();
        }

        this.usuarioService.criar(usuarioRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioFullResponse> alterar(@PathVariable String id, @RequestBody UsuarioRequest usuarioRequest) {

        if (!this.usuarioService.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        if (!this.usuarioService.validaUsuario(usuarioRequest)) {
            return ResponseEntity.badRequest().build();
        }
        Usuario usuarioAtualizado = this.usuarioService.atualizar(id, usuarioRequest);
        return ResponseEntity.ok(UsuarioResponseMapper.of(usuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable String id) {
        if(!this.usuarioService.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        this.usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/gerarCsv")
    public ResponseEntity<Usuario> generateCsvArchiveDeletedUsers(@RequestParam String nomeArq){
        List<Usuario> lista = this.usuarioService.listarUsuariosParaCsv();
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        this.usuarioService.gravaArquivoCsv(lista, nomeArq);
        return ResponseEntity.ok().build();
    }


}
