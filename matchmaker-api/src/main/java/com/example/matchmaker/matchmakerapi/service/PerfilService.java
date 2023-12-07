package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.FilaObj;
import com.example.matchmaker.matchmakerapi.entity.*;
import com.example.matchmaker.matchmakerapi.entity.repository.PerfilRepository;
import com.example.matchmaker.matchmakerapi.service.dto.request.NewMidiaRequest;
import com.example.matchmaker.matchmakerapi.service.dto.request.NewUserRequest;
import com.example.matchmaker.matchmakerapi.service.dto.request.mapper.RequestMapper;
import com.example.matchmaker.matchmakerapi.service.dto.response.*;
import com.example.matchmaker.matchmakerapi.service.dto.response.mapper.ResponseMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilService {
    private final PerfilRepository perfilRepository;
    private final GeneroJogoService generoJogoService;
    private final GeneroJogoPerfilService generoJogoPerfilService;
    private final UsuarioService usuarioService;
    private final InteressePerfilService interessePerfilService;
    private final ConsolePerfilService consolePerfilService;
    private final MidiaService midiaService;

    public PerfilFullResponse novoCadastro(NewUserRequest newUserRequest){
        Usuario usuario = this.usuarioService.criar(newUserRequest.getUsuario());

        Perfil perfil = RequestMapper.toPerfil(newUserRequest.getPerfil(),usuario);
        perfilRepository.save(perfil);


        newUserRequest.getGeneroJogoPerfil().forEach(it ->{
            GeneroJogo generoJogo = generoJogoService.getGeneroJogoId(it.getGeneroJogoId());
            perfil.addGeneroJogo(this.generoJogoPerfilService.addGeneroJogoPerfil(perfil, generoJogo,it.isVisible()));
        });

        newUserRequest.getInteressePerfil().forEach(it ->{
            perfil.addInteressePerfil(this.interessePerfilService.addInteressePerfil(perfil, it.getInteresseId(), it.isVisible()));
        });

        newUserRequest.getConsolePerfil().forEach(it -> {
            perfil.addConsole(this.consolePerfilService.addConsolePerfil(perfil, it.getConsoleId(), it.isVisible()));
        });

        List<MidiaFullResponse> midiaList = this.midiaService.addMidia(perfil,newUserRequest.getMidiaList());
        perfilRepository.save(perfil);

        return getPerfilFullResponse(perfil.getIdPerfil());
    }

    public PerfilFullResponse getPerfilFullResponse(Long perfilId){
        Perfil perfil = this.getPerfilId(perfilId);

        List<JogoInPerfilResponse> generoJogoList = this.getGeneroJogosPorPerfilId(perfilId);
        List<InteresseFullResponse> interesseList = this.getInteressePorPerfilId(perfilId);
        UsuarioInPerfilResponse usuario = ResponseMapper.toUsuarioInPerfilResponse(this.usuarioService.buscarPorId(perfil.getUsuario().getId()));
        List<ConsoleFullResponse> consoleList = this.getConsolePorPerfilId(perfilId);
        List<MidiaFullResponse> midiaList = this.getMidiaByPerfilId(perfilId);

        return ResponseMapper.toPerfilFullResponse(
                perfil,
                generoJogoList,
                usuario,
                interesseList,
                consoleList,
                midiaList
        );
    }


    public List<PerfilFullResponse> getPerfil() {
        List<PerfilFullResponse> responseMapperList = new ArrayList<>();
        List<Perfil> perfilList = this.perfilRepository.findAll();
        if (perfilList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum perfil Cadastrado");
        }

        perfilList.forEach(it ->{
            Usuario usuario = this.usuarioService.buscarPorId(it.getUsuario().getId());
            UsuarioInPerfilResponse user = ResponseMapper.toUsuarioInPerfilResponse(usuario);

            List<JogoInPerfilResponse> generoJogoList;
            generoJogoList = getGeneroJogosPorPerfilId(it.getIdPerfil());

            List<InteresseFullResponse> interesseList;
            interesseList = getInteressePorPerfilId(it.getIdPerfil());

            List<ConsoleFullResponse> consoleList;
            consoleList = getConsolePorPerfilId(it.getIdPerfil());

            List<MidiaFullResponse> midiaList;
            midiaList = getMidiaByPerfilId(it.getIdPerfil());
            responseMapperList.add(ResponseMapper.toPerfilFullResponse(it ,generoJogoList, user, interesseList, consoleList,midiaList));

        });

        return responseMapperList;
    }

    @Transactional
    public List<String> getAmigos(Integer idPerfil) {
        List<String> amigosList = this.perfilRepository.buscarAmigos(idPerfil);
        if (amigosList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum amigo encontrado");
        }

        return amigosList;
    }

    @Transactional
    public FilaObj<PerfilFullResponse> getCardsPerfil( Integer perfilId) {
        FilaObj<PerfilFullResponse> responseMapperList = new FilaObj<PerfilFullResponse>();
        List<Perfil> perfilList = this.perfilRepository.buscaPerfisCarrossel(perfilId);
        if (perfilList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum perfil encontrado");
        }

        perfilList.forEach(it ->{
            Usuario usuario = this.usuarioService.buscarPorId(it.getUsuario().getId());
            UsuarioInPerfilResponse user = ResponseMapper.toUsuarioInPerfilResponse(usuario);

            List<JogoInPerfilResponse> generoJogoList;
            generoJogoList = getGeneroJogosPorPerfilId(it.getIdPerfil());

            List<InteresseFullResponse> interesseList;
            interesseList = getInteressePorPerfilId(it.getIdPerfil());

            List<ConsoleFullResponse> consoleList;
            consoleList = getConsolePorPerfilId(it.getIdPerfil());

            List<MidiaFullResponse> midiaList;
            midiaList = getMidiaByPerfilId(it.getIdPerfil());
            responseMapperList.insert(ResponseMapper.toPerfilFullResponse(it ,generoJogoList, user, interesseList, consoleList,midiaList));

        });

        return responseMapperList;
    }

    public Perfil getPerfilId(Long id) {
        return this.perfilRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
        );
    }

    public void atualizarMidiasDoPerfil(Long perfilId, List<NewMidiaRequest> midias) {
        Perfil perfil = getPerfilId(perfilId);

        this.midiaService.addMidia(perfil,midias);
    }

    public List<JogoInPerfilResponse> getGeneroJogosPorPerfilId(Long perfilId) {
        List<JogoInPerfilResponse> generoJogoList = new ArrayList<>();
        boolean isVisible = this.generoJogoPerfilService.getIsVisibleByPerfilId(perfilId);

        List<GeneroJogo> generoJogos = this.generoJogoService.getGeneroJogoByPerfilId(perfilId);
        generoJogos.forEach(it -> {
            generoJogoList.add(ResponseMapper.toJogoInPerfilResponse(it, isVisible));
        });

        return generoJogoList;
    }

    public List<InteresseFullResponse> getInteressePorPerfilId(Long perfilId){
        List<InteresseFullResponse> interesseList = new ArrayList<>();
        boolean isVisible = this.interessePerfilService.isVisibleByPerfilId(perfilId);

        List<Interesse> interesses = this.interessePerfilService.getInteresseByPerfilId(perfilId);
        interesses.forEach(it -> {
            interesseList.add(ResponseMapper.toInteresseFullResponse(it,isVisible));
        });

        return interesseList;
    }

    List<ConsoleFullResponse> getConsolePorPerfilId(Long perfilId){
        List<ConsoleFullResponse> consoleList = new ArrayList<>();
        boolean isVisible = this.consolePerfilService.getIsVisibleByPerfilId(perfilId);

        List<Console> consoles = this.consolePerfilService.getConsoleListByPerfilId(perfilId);
        consoles.forEach(it -> {
            consoleList.add(ResponseMapper.toConsoleFullResponse(it,isVisible));
        });

        return consoleList;
    }

    List<MidiaFullResponse> getMidiaByPerfilId(Long perfilId){
        List<MidiaFullResponse> midiaFullResponseList = new ArrayList<>();

        List<Midia> midiaList = midiaService.getMidiaByPerfilId(perfilId);
        midiaList.forEach(it ->{
            midiaFullResponseList.add(ResponseMapper.toMidiaFullResponse(it));
        });

        return midiaFullResponseList;
    }

    @Transactional
    public void curtirPerfil(Integer perfilId, Integer perfilCurtido){
        LocalDate momento = LocalDate.now();
        perfilRepository.curtirOutroPerfil(perfilId, perfilCurtido, momento);
    }

    @Transactional
    public void descurtirPerfil(Integer perfilId, Integer perfilCurtido){
        perfilRepository.descurtirOutroPerfil(perfilId, perfilCurtido);
    }
}
