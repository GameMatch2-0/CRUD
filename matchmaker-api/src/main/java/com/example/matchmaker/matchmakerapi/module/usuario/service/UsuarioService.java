package com.example.matchmaker.matchmakerapi.module.usuario.service;

import com.example.matchmaker.matchmakerapi.module.usuario.Entity.Usuario;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.UsuarioRequestDto;
import com.example.matchmaker.matchmakerapi.module.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;


    public Usuario salvar(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    public Usuario criar(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario();
        return buildUsuario(usuarioRequestDto, usuario);
    }

    private Usuario buildUsuario(UsuarioRequestDto usuarioRequestDto, Usuario usuario) {
        usuario.setNome(usuarioRequestDto.getNome());
        usuario.setOrientacaoSexual(usuarioRequestDto.getOrientacaoSexual());
        usuario.setDtNascimento(usuarioRequestDto.getDtNascimento());
        usuario.setEmail(usuarioRequestDto.getEmail());
        usuario.setContato(usuarioRequestDto.getContato());
        usuario.setSenha(usuarioRequestDto.getSenha());
        usuario.setJogosFavoritos(usuarioRequestDto.getJogosFavoritos());
        usuario.setDtCadastro(LocalDateTime.now());
        return salvar(usuario);
    }

    public List<Usuario> listar() {
        return this.usuarioRepository.findAllByDeletedFalse();
    }

    public List<Usuario> listarApagados() {
        return this.usuarioRepository.findAllByDeletedTrue();
    }

    public Optional<Usuario> buscarPorId(String id) {
        return this.usuarioRepository.findById(id);
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

    public Usuario atualizar(String id, Usuario usuario, UsuarioRequestDto usuarioRequestDto) {
        usuario.setId(id);
        return buildUsuario(usuarioRequestDto, usuario);
    }

    public void deletar(String id, Usuario usuario) {
        usuario.setDeleted(true);
        salvar(usuario);
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
