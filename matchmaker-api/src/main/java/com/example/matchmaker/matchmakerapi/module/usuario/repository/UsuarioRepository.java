package com.example.matchmaker.matchmakerapi.module.usuario.repository;

import com.example.matchmaker.matchmakerapi.module.usuario.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    public Optional<Usuario> findById(String id);
    public List<Usuario> findAllByDeletedTrue();
    public List<Usuario> findAllByDeletedFalse();
    public Optional<Usuario> findByEmailAndDeletedFalse(String email);
}
