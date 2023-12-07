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
public class Midia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMidia;
    @ManyToOne
    @JoinColumn(name = "idPerfil")
    private Perfil perfil;
    private String link;
    private boolean visivel;
}
