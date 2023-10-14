package com.example.matchmaker.matchmakerapi.service.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioTokenDto {
    private String userId;
    private String nome;
    private String email;
    private String token;
}
