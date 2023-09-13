package sptech.school.projetosprintv1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetosprintv1.Usuario;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private List<Usuario> usuarioLista = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuariosExistentes = new ArrayList<>();
        if (usuarioLista.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            for (Usuario usuario : usuarioLista) {
                if (!usuario.isDeleted()) {
                    usuariosExistentes.add(usuario);
                }
            }
            return ResponseEntity.status(200).body(usuariosExistentes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable int id) {
        if (id < 0 || id > usuarioLista.size() || usuarioLista.get(id).isDeleted()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(usuarioLista.get(id));
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        for (Usuario usuario1 : usuarioLista) {
            if (usuario1.getEmail().equals(usuario.getEmail()) && !usuario1.isDeleted()) {
                return ResponseEntity.status(409).build();
            }
        }

        if (validaUsuario(usuario)) {
            return ResponseEntity.status(400).build();
        } else {
            usuarioLista.add(usuario);
            return ResponseEntity.status(201).body(usuario);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable int id, @RequestBody Usuario usuario) {
        if (id < 0 || id > usuarioLista.size() || usuarioLista.get(id).isDeleted()) {
            return ResponseEntity.status(404).build();
        } else if (validaUsuario(usuario)) {
            return ResponseEntity.status(400).build();
        } else {
            usuarioLista.set(id, usuario);
            return ResponseEntity.status(200).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable int id) {
        if (id < 0 || id > usuarioLista.size() || usuarioLista.get(id).isDeleted()) {
            return ResponseEntity.status(404).build();
        } else {
            usuarioLista.remove(id);
            return ResponseEntity.status(200).build();
        }
    }

    public boolean validaUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()
                || usuario.getOrientacaoSexual() == null
                || usuario.getOrientacaoSexual().isEmpty() || usuario.getEmail() == null
                || usuario.getEmail().isEmpty() || usuario.getEmail().length() < 3
                || !usuario.getEmail().contains("@") || usuario.getSenha() == null
                || usuario.getSenha().isEmpty() || usuario.getDtNascimento() == null
                || usuario.getDtNascimento().toString().isEmpty()
                || usuario.getJogosFavoritos() == null || usuario.getJogosFavoritos().length == 0) {
            return false;
        }
        return true;
    }


}
