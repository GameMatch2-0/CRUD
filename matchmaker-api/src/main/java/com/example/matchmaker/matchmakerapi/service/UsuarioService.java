package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.api.configuration.security.jwt.GerenciadorTokenJwt;
import com.example.matchmaker.matchmakerapi.entity.Usuario;
import com.example.matchmaker.matchmakerapi.entity.repository.UsuarioRepository;
import com.example.matchmaker.matchmakerapi.service.authentication.dto.UsuarioLoginDto;
import com.example.matchmaker.matchmakerapi.service.authentication.dto.UsuarioTokenDto;
import com.example.matchmaker.matchmakerapi.service.dto.request.UsuarioRequest;
import com.example.matchmaker.matchmakerapi.service.dto.request.mapper.RequestMapper;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ResponseMapper;
import com.example.matchmaker.matchmakerapi.service.dto.response.UsuarioFullResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
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

    public List<Usuario> listarUsuariosParaCsv() {
        List<Usuario> usuarioList = this.usuarioRepository.findAllByDeletedFalse();

        return usuarioList;
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

    public Usuario buscarUsuarioPorIdPerfil(Long idPerfil) {
        return this.usuarioRepository.findByPerfil(idPerfil).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado")
        );
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

    public void gravaArquivoCsv(List<Usuario> lista, String nomeArq) {
        if (lista.isEmpty()) {
            System.out.println("A lista está vazia. Não há nada para gravar");
            return;
        } else {
            FileWriter arq = null; // Representa o arquivo que será gravado
            Formatter saida = null; // Objeto que será usado para escrever no arquivo
            Boolean deuRuim = false; // Flag para indicar se deu erro

            nomeArq += ".csv";

            // Criando o arquivo
            try {
                arq = new FileWriter(nomeArq, false); // Abre o arquivo
                saida = new Formatter(arq); // Instancia o obj saida, associando-o ao arquivo saida
            } catch (IOException erro) {
                System.out.println("Erro ao abrir o arquivo");
                System.exit(1);
            }

            // Gravando os objetos no arquivo
            try {
                // Percorre a lista, escrevendo cada objeto no arquivo e gravando um registro para cada Cachorro
                for (int i = 0; i < lista.size(); i++) {
                    // Instancia um objeto Cachorro para receber cada elemento da lista
                    Usuario user= lista.get(i);
                    saida.format("%s;%s;%s;%s;%s;%s;%s;%b;%b\n", user.getId(), user.getNome(), user.getSobrenome(), user.getDtNascimento(), user.getEmail(), user.getContato(), user.getDtCadastro(), user.isDeleted(), user.isLogado());
                }
            } catch (FormatterClosedException erro) {
                System.out.println("Erro ao gravar no arquivo");
                erro.printStackTrace();
                deuRuim = true;
            } finally {
                saida.close(); // Fecha o arquivo
                try {
                    arq.close(); // Fecha o arquivo
                } catch (IOException err) {
                    System.out.println("Erro ao fechar o arquivo");
                    deuRuim = true;
                }
                if (deuRuim) {
                    System.exit(1);
                }
            }
        }
    }
}
