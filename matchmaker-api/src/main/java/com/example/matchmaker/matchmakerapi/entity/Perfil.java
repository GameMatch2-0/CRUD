package com.example.matchmaker.matchmakerapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Perfil{
    @Id
    @JoinColumn(name = "idUsuario")
    private String idUsuario;
    @OneToOne
    @MapsId
    @JoinColumn(name = "idusuario")
    private Usuario usuario;
    private String username;
    private String biografia;
    private Float nota;
    private String orientacaoSexual;
    private boolean procuraAmizade;
    private boolean procuraNamoro;
    private boolean procuraPlayer2;
    private boolean isPremium = false;
    @ManyToOne
    @JoinColumn(name = "idPlano")
    private Plano plano;
}
