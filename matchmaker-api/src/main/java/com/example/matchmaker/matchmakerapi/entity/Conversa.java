package com.example.matchmaker.matchmakerapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conversa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConversa;
    private String idUsuarioLogado;
    private String idUsuarioConversa;
    private int notificacoes;
    private int alertaNotificacao;
    private boolean deleted = false;
}
