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
public class GeneroJogos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGeneroJogos;
    private String nome;
    private String descricao;
}
