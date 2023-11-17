package com.example.matchmaker.matchmakerapi.service.dto.response;

import com.example.matchmaker.matchmakerapi.entity.GeneroJogo;
import com.example.matchmaker.matchmakerapi.entity.GeneroJogoPerfil;
import com.example.matchmaker.matchmakerapi.entity.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PerfilFullResponse {
    private Long idPerfil;
    private String idUsuario;
    private String username;
    private String biografia;
    private Float nota;
    private String orientacaoSexual;
    private boolean procuraAmizade;
    private boolean procuraNamoro;
    private boolean procuraPlayer2;
    private boolean isPremium = false;
    private List<GeneroJogo> generosJogos;
}
