package com.example.matchmaker.matchmakerapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Perfil{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerfil")
    private Long idPerfil;
    @OneToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;
    private String username;
    private String biografia;
    private Float nota;
    private String orientacaoSexual;
//    private boolean procuraAmizade;
//    private boolean procuraNamoro;
//    private boolean procuraPlayer2;
    private boolean isPremium = false;
    @ManyToOne
    @JoinColumn(name = "idPlano")
    private Plano plano;
    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
    private List<GeneroJogoPerfil> generosJogosPerfil = new ArrayList<>();
    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
    private List<InteressePerfil> interessePerfils = new ArrayList<>();
    @OneToMany(mappedBy = "perfil",cascade = CascadeType.ALL)
    private List<ConsolePerfil> consolePerfils = new ArrayList<>();
    @OneToMany(mappedBy = "perfil",cascade = CascadeType.ALL)
    private List<Midia> midiaList = new ArrayList<>();

    public void addGeneroJogo(GeneroJogoPerfil generoJogoPerfil){
        this.generosJogosPerfil.add(generoJogoPerfil);
    }
    public void addInteressePerfil(InteressePerfil interessePerfil){
        this.interessePerfils.add(interessePerfil);
    }
    public void addConsole(ConsolePerfil consolePerfil){
        this.consolePerfils.add(consolePerfil);
    }

    public void addMidia(Midia midia){
        this.midiaList.add(midia);
    }
}
