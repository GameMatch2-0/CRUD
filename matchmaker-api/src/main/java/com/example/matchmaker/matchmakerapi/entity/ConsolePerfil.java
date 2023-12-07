package com.example.matchmaker.matchmakerapi.entity;

import com.example.matchmaker.matchmakerapi.entity.embeddable.ConsolePerfilId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsolePerfil {
    @EmbeddedId
    private ConsolePerfilId id;
    @ManyToOne
    @JoinColumn(name = "idPerfil",insertable = false, updatable = false)
    private Perfil perfil;
    @ManyToOne
    @JoinColumn(name = "idConsole",insertable = false, updatable = false)
    private Console console;
    private boolean visivel;
}
