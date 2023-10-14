package com.example.matchmaker.matchmakerapi.domain.repository;

import com.example.matchmaker.matchmakerapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    // Busca por usuarios pelo id
    public Optional<Usuario> findById(String id);
    // Busca por usuarios pelo nome e que não foram apagados
    public Optional<Usuario> findByNomeIgnoreCaseAndDeletedFalse(String nome);
    // Busca por usuarios pelo nome e que foram apagados
    public Optional<Usuario> findByNomeIgnoreCaseAndDeletedTrue(String nome);
    // Busca por todos usuarios que não foram apagados
    public List<Usuario> findAllByDeletedFalse();
    // Busca por todos usuarios que foram apagados
    public List<Usuario> findAllByDeletedTrue();
    // Busca por usuarios pelo email e que não foram apagados
    public Optional<Usuario> findByEmailAndDeletedFalse(String email);
    // Busca por usuarios pelo email e que foram apagados
    public Optional<Usuario> findByEmailAndDeletedTrue(String email);
    // Busca por usuarios com JogosFavoritos em comum
    public Optional<Usuario> findByJogosFavoritosInAndDeletedFalse(String[] jogosFavoritos);

}
