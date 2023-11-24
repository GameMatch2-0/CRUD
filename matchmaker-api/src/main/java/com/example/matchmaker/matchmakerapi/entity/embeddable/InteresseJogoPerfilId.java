package com.example.matchmaker.matchmakerapi.entity.embeddable;

import com.example.matchmaker.matchmakerapi.entity.Interesse;
import com.example.matchmaker.matchmakerapi.entity.Perfil;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class InteresseJogoPerfilId {
    private Perfil perfilId;
    private Interesse interesseId;
}
