package com.example.matchmaker.matchmakerapi.entity.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class GeneroJogoPerfilId implements Serializable {
    private Long idPerfil;
    private Integer idGeneroJogos;
}
