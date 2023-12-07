package com.example.matchmaker.matchmakerapi.entity;

import jakarta.persistence.*;
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
    @ManyToOne
    private Perfil idPerfilLogado;
    @ManyToOne
    private Perfil idPerfilConversa;
    private int notificacoes;
    private boolean alertaNotificacao;
    private boolean deleted = false;
}
