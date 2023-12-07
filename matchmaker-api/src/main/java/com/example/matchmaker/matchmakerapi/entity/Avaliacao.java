package com.example.matchmaker.matchmakerapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAvaliacao;
    @ManyToOne
    private Perfil perfilAvaliado;
    @ManyToOne
    private Perfil perfilAvaliador;
    @NonNull
    private Double avaliacao;
    private String descricao;
    private Double notaAnterior;
    private Double notaNova;
    private LocalDate dataHora = LocalDate.now();
    private Boolean isAtiva = true;

}
