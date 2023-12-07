package com.example.matchmaker.matchmakerapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensagem;
    private Integer idConversa;
    @ManyToOne
    private Perfil perfil;
    private LocalDateTime dtEnvio;
    private LocalDateTime dtEdicao;
    private String corpoMensagem;
    private Boolean visivel = true;

    public String getDtEnvio() {
        return dtEnvio.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public void setDtEnvio(LocalDateTime dtEnvio) {
        this.dtEnvio = dtEnvio;
    }

    public String getDtEdicao() {
        return dtEdicao.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public LocalDateTime dtEdicaoSemFormatar() {
        return dtEdicao;
    }

    public void setDtEdicao(LocalDateTime dtEdicao) {
        this.dtEdicao = dtEdicao;
    }
}
