package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Avaliacao;
import com.example.matchmaker.matchmakerapi.entity.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {
//    private final AvaliacaoRepository avaliacaoRepository;
//    private final PerfilService perfilService;
//
//    public List<Avaliacao> getAvaliacao(){
//        List<Avaliacao> avaliacaoList = this.avaliacaoRepository.findAll();
//        if (avaliacaoList.isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhuma avaliação encontrada");
//        }
//        return avaliacaoList;
//    }
//
//    public Avaliacao buscarPorId(Integer id){
//        Avaliacao avaliacao = this.avaliacaoRepository.findById(id).orElseThrow(
//                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada")
//        );
//        return avaliacao;
//    }
//
//    public List<Avaliacao> buscarPorPerfilId(Integer perfild){
//        List<Avaliacao> avaliacaoList = this.avaliacaoRepository.findByIdPerfil(perfild);
//        if (avaliacaoList.isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhuma avaliação encontrada");
//        }
//        return avaliacaoList;
//    }
//
//    public Optional<Avaliacao> novaAvaliacao(
//            Integer idPerfilAvaliado,
//            Integer idPerfilLogado,
//            Double avaliacao,
//            String descricao
//    ){
//        Double avaliacaoTratada = avaliacao * ((perfilService.getPerfilId(idPerfilLogado.longValue()).getNota() * 2)/10 );
//        return this.avaliacaoRepository.adicionarAvaliacao(idPerfilAvaliado, idPerfilLogado.intValue(), avaliacaoTratada, descricao, LocalDate.now());
//    }
//
//    public void removerAvaliacao(Integer idPerfilAvaliado,
//                                 Integer idPerfilLogado){
//        this.avaliacaoRepository.removerAvaliacao(idPerfilAvaliado, idPerfilAvaliado.intValue());
//    }

}
