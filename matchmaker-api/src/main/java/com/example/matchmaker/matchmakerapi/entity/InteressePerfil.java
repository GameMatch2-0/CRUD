package com.example.matchmaker.matchmakerapi.entity;

import com.example.matchmaker.matchmakerapi.entity.embeddable.InteressePerfilId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InteressePerfil {
    @EmbeddedId
    private InteressePerfilId id;
    @ManyToOne
    @JoinColumn(name = "idPerfil", insertable = false, updatable = false)
    private Perfil perfil;
    @ManyToOne
    @JoinColumn(name = "idInteresses", insertable = false, updatable = false)
    private Interesse interesse;
    private boolean visivel = true;
}
