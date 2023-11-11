package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Usuario;
import com.example.matchmaker.matchmakerapi.service.authentication.dto.UsuarioLoginDto;
import com.example.matchmaker.matchmakerapi.service.authentication.dto.UsuarioTokenDto;
import com.example.matchmaker.matchmakerapi.service.dto.request.UsuarioRequest;
import com.example.matchmaker.matchmakerapi.service.dto.request.RequestMapper;
import com.example.matchmaker.matchmakerapi.service.dto.response.ResponseMapper;
import com.example.matchmaker.matchmakerapi.service.dto.response.UsuarioFullResponse;
import com.example.matchmaker.matchmakerapi.entity.repository.UsuarioRepository;
import com.example.matchmaker.matchmakerapi.api.configuration.security.jwt.GerenciadorTokenJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;


    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha()
        );

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                this.usuarioRepository.findByEmailAndDeletedFalse(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuario não encontrado", null)
                        );
        usuarioAutenticado.setLogado(true);
        this.usuarioRepository.save(usuarioAutenticado);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return RequestMapper.toUsuarioTokenDto(usuarioAutenticado, token);
    }

    public void logof(String id){
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao fazer logout")
        );

        usuario.setLogado(false);
        this.usuarioRepository.save(usuario);
    }


    public Usuario salvar(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    public void criar(UsuarioRequest usuarioRequest) {
        final Usuario novoUsuario = RequestMapper.toEntity(usuarioRequest);

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        this.usuarioRepository.save(novoUsuario);
    }


    public List<UsuarioFullResponse> listar() {
        List<Usuario> usuarioList = this.usuarioRepository.findAllByDeletedFalse();

        return usuarioList.stream()
                .map(ResponseMapper::toUsuarioFullResponse)
                .collect(Collectors.toList());
    }

    public List<UsuarioFullResponse> listarApagados() {
        List<Usuario> usuarioList = this.usuarioRepository.findAllByDeletedTrue();

        return usuarioList.stream()
                .map(ResponseMapper::toUsuarioFullResponse)
                .collect(Collectors.toList());
    }


    public UsuarioFullResponse buscarPorId(String id) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado")
        );

        return ResponseMapper.toUsuarioFullResponse(usuario);
    }

    public Optional<Usuario> buscarPorNome(String nome) {
        return this.usuarioRepository.findByNomeIgnoreCaseAndDeletedFalse(nome);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return this.usuarioRepository.findByEmailAndDeletedFalse(email);
    }

    public Usuario atualizar(String id, UsuarioRequest usuarioRequest) {
        this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        );

        Usuario updateUsuario = RequestMapper.toEntity(usuarioRequest);
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
                && usuario.getIdentidadeGenero() != null
                && !usuario.getIdentidadeGenero().isEmpty() && usuario.getEmail() != null
                && !usuario.getEmail().isEmpty() && usuario.getEmail().length() >= 3
                && usuario.getEmail().contains("@") && usuario.getSenha() != null
                && !usuario.getSenha().isEmpty() && usuario.getDtNascimento() != null
                && !usuario.getDtNascimento().toString().isEmpty();

    }

    public boolean existsById(String id) {
        return this.usuarioRepository.existsById(id);
    }


}
