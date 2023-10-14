package com.example.matchmaker.matchmakerapi.module.usuario.service;

import com.example.matchmaker.matchmakerapi.module.usuario.Entity.Usuario;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.request.UsuarioRequest;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.request.UsuarioRequestMapper;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.response.UsuarioFullResponse;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.response.UsuarioResponseMapper;
import com.example.matchmaker.matchmakerapi.module.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;


    public Usuario salvar(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    public void criar(UsuarioRequest usuarioRequest) {
        final Usuario novoUsuario = UsuarioRequestMapper.of(usuarioRequest);
        this.usuarioRepository.save(novoUsuario);
    }


    public List<UsuarioFullResponse> listar() {
        List<Usuario> usuarioList = this.usuarioRepository.findAllByDeletedFalse();

        return usuarioList.stream()
                .map(UsuarioResponseMapper::of)
                .collect(Collectors.toList());
    }

    public List<UsuarioFullResponse> listarApagados() {
        List<Usuario> usuarioList = this.usuarioRepository.findAllByDeletedTrue();

        return usuarioList.stream()
                .map(UsuarioResponseMapper::of)
                .collect(Collectors.toList());
    }


    public UsuarioFullResponse buscarPorId(String id) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado")
        );

        return UsuarioResponseMapper.of(usuario);
    }

    public Optional<Usuario> buscarPorNome(String nome) {
        return this.usuarioRepository.findByNomeIgnoreCaseAndDeletedFalse(nome);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return this.usuarioRepository.findByEmailAndDeletedFalse(email);
    }

    // Esse metodo vai ser usado quando formos atras de usuarios com jogos em comum, recebe os jogosFavoritos do usuario que esta logado
    public Optional<Usuario> buscarPorJogosFavoritosEmComum(String[] jogosFavoritos) {
        return this.usuarioRepository.findByJogosFavoritosInAndDeletedFalse(jogosFavoritos);
    }

    public Usuario atualizar(String id, UsuarioRequest usuarioRequest) {
        this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        );

        Usuario updateUsuario = UsuarioRequestMapper.of(usuarioRequest);
        return salvar(updateUsuario);
    }

    public void deletar(String id) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado")
        );
        usuario.setDeleted(true);
        salvar(usuario);
    }

    public boolean validaUsuario(UsuarioRequest usuario) {
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

    public boolean existsById(String id) {
        return this.usuarioRepository.existsById(id);
    }


}
