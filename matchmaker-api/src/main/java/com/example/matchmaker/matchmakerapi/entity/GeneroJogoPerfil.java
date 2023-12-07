package com.example.matchmaker.matchmakerapi.entity;

import com.example.matchmaker.matchmakerapi.entity.embeddable.GeneroJogoPerfilId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "genero_jogos_perfil")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeneroJogoPerfil {
    @EmbeddedId
    private GeneroJogoPerfilId id;
    @ManyToOne
    @JoinColumn(name = "idPerfil", insertable = false, updatable = false)
    private Perfil perfil;
    @ManyToOne
    @JoinColumn(name = "idGeneroJogos", insertable = false, updatable = false)
    private GeneroJogo generoJogos;
    private boolean isVisivel = true;
}
