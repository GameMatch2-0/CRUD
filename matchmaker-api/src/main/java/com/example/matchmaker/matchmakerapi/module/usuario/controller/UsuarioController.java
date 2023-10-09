package com.example.matchmaker.matchmakerapi.module.usuario.controller;

import com.example.matchmaker.matchmakerapi.module.usuario.Entity.Usuario;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.UsuarioRequestDto;
import com.example.matchmaker.matchmakerapi.module.usuario.repository.UsuarioRepository;
import com.example.matchmaker.matchmakerapi.module.usuario.service.UsuarioService;
import com.example.matchmaker.matchmakerapi.module.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarioList = this.service.listar();

        if (usuarioList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarioList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscar(@PathVariable String id) {
        Optional<Usuario> usuario = this.service.buscarPorId(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(usuario);
        }
    }

    @GetMapping("/apagados")
    public ResponseEntity<List<Usuario>> listarApagados() {
        List<Usuario> usuarioList = this.service.listarApagados();

        if (usuarioList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarioList);
        }
    }

    @PostMapping()
    public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        Optional<Usuario> usuarioOptional = this.service.buscarPorEmail(usuarioRequestDto.getEmail());

        if (usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        if (!validaUsuario(usuarioRequestDto)) {
            return ResponseEntity.badRequest().build();
        }

        Usuario novoUsuario = this.service.criar(usuarioRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable String id, @RequestBody UsuarioRequestDto usuarioRequestDto) {
        Optional<Usuario> optionalUsuario = this.service.buscarPorId(id);

        if (optionalUsuario.isEmpty() || optionalUsuario.get().isDeleted()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = optionalUsuario.get();

        if (!validaUsuario(usuarioRequestDto)) {
            return ResponseEntity.badRequest().build();
        }
        Usuario updateUsuario = this.service.atualizar(id, usuario, usuarioRequestDto);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable String id) {
        Optional<Usuario> optionalUsuario = this.service.buscarPorId(id);

        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = optionalUsuario.get();

        this.service.deletar(id, usuario);
        return ResponseEntity.noContent().build();
    }

    public boolean validaUsuario(UsuarioRequestDto usuario) {
        return usuario.getNome() != null && !usuario.getNome().isEmpty()
                && usuario.getOrientacaoSexual() != null
                && !usuario.getOrientacaoSexual().isEmpty() && usuario.getEmail() != null
                && !usuario.getEmail().isEmpty() && usuario.getEmail().length() >= 3
                && usuario.getEmail().contains("@") && usuario.getSenha() != null
                && !usuario.getSenha().isEmpty() && usuario.getDtNascimento() != null
                && !usuario.getDtNascimento().toString().isEmpty()
                && usuario.getJogosFavoritos() != null && usuario.getJogosFavoritos().size() != 0
                && usuario.getJogosFavoritos().size() < 6;

    }


}
