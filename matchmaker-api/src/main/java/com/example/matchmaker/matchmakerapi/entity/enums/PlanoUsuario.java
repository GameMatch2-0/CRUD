package com.example.matchmaker.matchmakerapi.entity.enums;

public enum PlanoUsuario {

    FREE(0,"Gratuito"),PREMIUM(1,"Premium");

    private Integer codigo;
    private String descricao;

    PlanoUsuario(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static PlanoUsuario toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }

        for (PlanoUsuario plano : PlanoUsuario.values()){
            if (codigo.equals(plano.getCodigo())){
                return plano;
            }
        }

        throw new IllegalArgumentException("Plano invlaido");
    }
}
