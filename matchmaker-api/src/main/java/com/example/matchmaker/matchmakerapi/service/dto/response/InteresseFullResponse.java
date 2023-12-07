package com.example.matchmaker.matchmakerapi.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InteresseFullResponse {
    private Long id;
    private String nome;
    private String descricao;
    private boolean isVisible;
}
