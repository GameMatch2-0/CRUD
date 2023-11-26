package com.example.matchmaker.matchmakerapi.entity.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsolePerfilId implements Serializable {
    private Long perfilId;
    private Long consoleId;
}
