package com.example.matchmaker.matchmakerapi.service.dto.request;

import com.example.matchmaker.matchmakerapi.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewPerfilRequest {
    private String username;
    private String biografia;
    private Float nota;
    private String orientacaoSexual;
    private boolean procuraAmizade;
    private boolean procuraNamoro;
    private boolean procuraPlayer2;
    private boolean isPremium = false;
    private List<String> lista;
}
