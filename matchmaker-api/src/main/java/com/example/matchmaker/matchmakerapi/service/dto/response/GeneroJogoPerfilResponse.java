package com.example.matchmaker.matchmakerapi.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeneroJogoPerfilResponse {
    private Long perfilId;
    private Integer generoJogoId;
    private boolean isVisible;
}
