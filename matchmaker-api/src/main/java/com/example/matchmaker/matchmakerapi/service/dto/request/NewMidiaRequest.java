package com.example.matchmaker.matchmakerapi.service.dto.request;

import com.example.matchmaker.matchmakerapi.entity.Perfil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewMidiaRequest {
    private String link;
    private boolean isVisible;
}
