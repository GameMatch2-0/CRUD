package com.example.matchmaker.matchmakerapi.module.usuario.controller;

import com.example.matchmaker.matchmakerapi.module.usuario.Entity.Usuario;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.UsuarioRequestDto;
import com.example.matchmaker.matchmakerapi.module.usuario.repository.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarioList = this.usuarioRepository.findAllByDeletedFalse();

        if (usuarioList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarioList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscar(@PathVariable String id) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(usuario);
        }
    }

    @GetMapping("/apagados")
    public ResponseEntity<List<Usuario>> listarApagados() {
        List<Usuario> usuarioList = this.usuarioRepository.findAllByDeletedTrue();

        if (usuarioList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarioList);
        }
    }

    @PostMapping()
    public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByEmailAndDeletedFalse(usuarioRequestDto.getEmail());

        if (usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        if (!validaUsuario(usuarioRequestDto)) {
            return ResponseEntity.badRequest().build();
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDto.getNome());
        usuario.setContato(usuarioRequestDto.getContato());
        usuario.setDtNascimento(usuarioRequestDto.getDtNascimento());
        usuario.setEmail(usuarioRequestDto.getEmail());
        usuario.setJogosFavoritos(usuarioRequestDto.getJogosFavoritos());
        usuario.setOrientacaoSexual(usuarioRequestDto.getOrientacaoSexual());
        usuario.setSenha(usuarioRequestDto.getSenha());

        Usuario novoUsuario = this.usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable String id, @RequestBody UsuarioRequestDto usuarioRequestDto) {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(id);

        if (optionalUsuario.isEmpty() || optionalUsuario.get().isDeleted()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = optionalUsuario.get();

        if (!validaUsuario(usuarioRequestDto)) {
            return ResponseEntity.badRequest().build();
        }

        usuario.setId(id);
        usuario.setSenha(usuarioRequestDto.getSenha());
        usuario.setNome(usuarioRequestDto.getNome());
        usuario.setEmail(usuarioRequestDto.getEmail());
        usuario.setContato(usuarioRequestDto.getContato());
        usuario.setJogosFavoritos(usuarioRequestDto.getJogosFavoritos());
        usuario.setOrientacaoSexual(usuarioRequestDto.getOrientacaoSexual());

        Usuario updateUsuario = this.usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable String id) {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(id);

        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = optionalUsuario.get();

        usuario.setDeleted(true);
        Usuario deleteUsuario = this.usuarioRepository.save(usuario);
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
