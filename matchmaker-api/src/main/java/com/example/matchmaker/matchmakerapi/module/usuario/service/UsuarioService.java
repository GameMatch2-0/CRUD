package com.example.matchmaker.matchmakerapi.module.usuario.service;

import com.example.matchmaker.matchmakerapi.module.usuario.Entity.Usuario;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.UsuarioRequestDto;
import com.example.matchmaker.matchmakerapi.module.usuario.dto.UsuarioResponse;
import com.example.matchmaker.matchmakerapi.module.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
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


    public List<UsuarioResponse> listar() {
        List<Usuario> usuarioList = this.usuarioRepository.findAllByDeletedFalse();

        return buildUsuarioResponseList(usuarioList);
    }

    public List<UsuarioResponse> listarApagados() {
        List<Usuario> usuarioList = this.usuarioRepository.findAllByDeletedTrue();

        return buildUsuarioResponseList(usuarioList);
    }


    public UsuarioResponse buscarPorId(String id) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(
                () ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado")
        );

        UsuarioResponse usuarioResponse = new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getApelido(),
                usuario.getOrientacaoSexual(),
                usuario.getDtNascimento(),
                usuario.getEmail(),
                usuario.getContato(),
                usuario.getSenha(),
                usuario.getDtCadastro(),
                usuario.getJogosFavoritos(),
                usuario.isDeleted()
        );

        return usuarioResponse;
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

    public Usuario atualizar(String id, UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        );

        buildUsuario(usuarioRequestDto, usuario);
        return salvar(usuario);
    }

    public void deletar(String id) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado")
        );
        usuario.setDeleted(true);
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

    public boolean existsById(String id){
        return this.usuarioRepository.existsById(id);
    }

    private List<UsuarioResponse> buildUsuarioResponseList(List<Usuario> usuarioList) {
        List<UsuarioResponse> usuarioResponseList = new ArrayList<>();
        usuarioList.forEach(it -> {
            UsuarioResponse usuarioResponse = new UsuarioResponse(
                    it.getId(),
                    it.getNome(),
                    it.getApelido(),
                    it.getOrientacaoSexual(),
                    it.getDtNascimento(),
                    it.getEmail(),
                    it.getContato(),
                    it.getSenha(),
                    it.getDtCadastro(),
                    it.getJogosFavoritos(),
                    it.isDeleted()
            );
            usuarioResponseList.add(usuarioResponse);
        });
        return usuarioResponseList;
    }

    private Usuario buildUsuario(UsuarioRequestDto usuarioRequestDto, Usuario usuario) {
        usuario.setNome(usuarioRequestDto.getNome());
        usuario.setOrientacaoSexual(usuarioRequestDto.getOrientacaoSexual());
        usuario.setDtNascimento(usuarioRequestDto.getDtNascimento());
        usuario.setEmail(usuarioRequestDto.getEmail());
        usuario.setContato(usuarioRequestDto.getContato());
        usuario.setSenha(usuarioRequestDto.getSenha());
        usuario.setJogosFavoritos(usuarioRequestDto.getJogosFavoritos());

        return usuario;
    }
}
