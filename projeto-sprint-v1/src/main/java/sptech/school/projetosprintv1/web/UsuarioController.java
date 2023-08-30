package sptech.school.projetosprintv1.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private List<Usuario> usuarioLista = new ArrayList<>();

        @GetMapping
        public ResponseEntity<List<Usuario>> listar() {
            if (usuarioLista.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(usuarioLista);
            }
        }
        @PostMapping
        public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {

            if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
                return ResponseEntity.status(400).build();
            } else {
                usuarioLista.add(usuario);
                return ResponseEntity.status(201).body(usuario);
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<Usuario> alterar(@PathVariable int id, @RequestBody Usuario usuario) {
            if (usuarioLista.size() < id) {
                return ResponseEntity.status(404).build();
            } else {
                usuarioLista.set(id, usuario);
                return ResponseEntity.status(200).build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Usuario> deletar(@PathVariable int id) {
            if (usuarioLista.size() < id) {
                return ResponseEntity.status(404).build();
            } else {
                usuarioLista.remove(id);
                return ResponseEntity.status(200).build();
            }
        }


}
