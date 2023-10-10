package com.example.matchmaker.matchmakerapi.module.usuario.controller;

import com.example.matchmaker.matchmakerapi.module.usuario.Entity.Usuario;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.UsuarioRequestDto;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.UsuarioResponse;
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
    public ResponseEntity<List<UsuarioResponse>> listar() {
        List<UsuarioResponse> usuarioResponseList = this.usuarioService.listar();

        if (usuarioResponseList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarioResponseList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscar(@PathVariable String id) {
        UsuarioResponse usuarioResponse = this.usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuarioResponse);
    }

    @GetMapping("/apagados")
    public ResponseEntity<List<UsuarioResponse>> listarApagados() {
        List<UsuarioResponse> usuarioList = this.usuarioService.listarApagados();

        if (usuarioList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarioList);
    }

    @PostMapping()
    public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        Optional<Usuario> usuarioOptional = this.usuarioService.buscarPorEmail(usuarioRequestDto.getEmail());

        if (usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        if (!this.usuarioService.validaUsuario(usuarioRequestDto)) {
            return ResponseEntity.badRequest().build();
        }

        Usuario novoUsuario = this.usuarioService.criar(usuarioRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> alterar(@PathVariable String id, @RequestBody UsuarioRequestDto usuarioRequestDto) {

        if (!this.usuarioService.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        if (!this.usuarioService.validaUsuario(usuarioRequestDto)) {
            return ResponseEntity.badRequest().build();
        }
        Usuario usuarioAtualizado = this.usuarioService.atualizar(id, usuarioRequestDto);
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
