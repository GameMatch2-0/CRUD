package com.example.matchmaker.matchmakerapi.module.usuario.controller;

import com.example.matchmaker.matchmakerapi.module.usuario.Entity.Usuario;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.UsuarioRequestDto;
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
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarioList = this.usuarioService.listar();

        if (usuarioList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarioList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscar(@PathVariable String id) {
        Optional<Usuario> usuario = this.usuarioService.buscarPorId(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(usuario);
        }
    }

    @GetMapping("/apagados")
    public ResponseEntity<List<Usuario>> listarApagados() {
        List<Usuario> usuarioList = this.usuarioService.listarApagados();

        if (usuarioList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarioList);
        }
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
    public ResponseEntity<Usuario> alterar(@PathVariable String id, @RequestBody UsuarioRequestDto usuarioRequestDto) {
        Optional<Usuario> optionalUsuario = this.usuarioService.buscarPorId(id);

        if (optionalUsuario.isEmpty() || optionalUsuario.get().isDeleted()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = optionalUsuario.get();

        if (!this.usuarioService.validaUsuario(usuarioRequestDto)) {
            return ResponseEntity.badRequest().build();
        }
        Usuario updateUsuario = this.usuarioService.atualizar(id, usuario, usuarioRequestDto);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable String id) {
        Optional<Usuario> optionalUsuario = this.usuarioService.buscarPorId(id);

        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = optionalUsuario.get();

        this.usuarioService.deletar(id, usuario);
        return ResponseEntity.noContent().build();
    }




}
