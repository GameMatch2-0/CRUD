package com.example.matchmaker.matchmakerapi.entity;

import com.example.matchmaker.matchmakerapi.entity.enums.BuscandoEnum;
import com.example.matchmaker.matchmakerapi.entity.enums.PlanoUsuario;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Perfil implements Serializable {
    @Id
    @Column(name = "idUsuario")
    private String idUsuario;
    private String biografia;
    private Integer nota;
    @ElementCollection
    private Set<Integer> buscandoEnum = new HashSet<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Integer> planoUsuarioEnum = new HashSet<>();

    public void addPlano(PlanoUsuario plano) {
        this.planoUsuarioEnum.add(plano.getCodigo());
    }

    public void addBuscando(BuscandoEnum buscandoEnum){
        this.buscandoEnum.add(buscandoEnum.getCodigo());
    }
}
