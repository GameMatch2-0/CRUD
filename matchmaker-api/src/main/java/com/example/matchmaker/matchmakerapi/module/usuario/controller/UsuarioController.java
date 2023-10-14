package com.example.matchmaker.matchmakerapi.module.usuario.controller;

import com.example.matchmaker.matchmakerapi.module.usuario.Entity.Usuario;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.request.UsuarioRequest;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.response.UsuarioFullResponse;
import com.example.matchmaker.matchmakerapi.module.usuario.service.UsuarioService;
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
        return ResponseEntity.ok(this.usuarioService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable String id) {
        if(!this.usuarioService.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        this.usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
