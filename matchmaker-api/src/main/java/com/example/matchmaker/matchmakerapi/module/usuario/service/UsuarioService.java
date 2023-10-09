package com.example.matchmaker.matchmakerapi.module.usuario.service;

import com.example.matchmaker.matchmakerapi.module.usuario.Entity.Usuario;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.UsuarioRequestDto;
import com.example.matchmaker.matchmakerapi.module.usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private UsuarioService service;

    private UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario salvar(Usuario usuario) {
        return this.repo.save(usuario);
    }

    public Usuario criar(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario();
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
        return this.repo.findAllByDeletedFalse();
    }

    public List<Usuario> listarApagados() {
        return this.repo.findAllByDeletedTrue();
    }

    public Optional<Usuario> buscarPorId(String id) {
        return this.repo.findById(id);
    }

    public Optional<Usuario> buscarPorNome(String nome) {
        return this.repo.findByNomeIgnoreCaseAndDeletedFalse(nome);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return this.repo.findByEmailAndDeletedFalse(email);
    }

    // Esse metodo vai ser usado quando formos atras de usuarios com jogos em comum, recebe os jogosFavoritos do usuario que esta logado
    public Optional<Usuario> buscarPorJogosFavoritosEmComum(String[] jogosFavoritos) {
        return this.repo.findByJogosFavoritosInAndDeletedFalse(jogosFavoritos);
    }

    public Usuario atualizar(String id, Usuario usuario, UsuarioRequestDto usuarioRequestDto) {
        usuario.setId(id);
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

    public void deletar(String id, Usuario usuario) {
        usuario.setDeleted(true);
        salvar(usuario);
    }


}
