package com.example.matchmaker.matchmakerapi.entity.enums;

public enum BuscandoEnum {
    AMIZADE(0,"Buscando amizade"),NAMORO(1,"Busacando namoro"), TEAMMATE(2,"Buscando player 2");

    private Integer codigo;
    private String descricao;

    BuscandoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static BuscandoEnum toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }

        for (BuscandoEnum buscandoEnum: BuscandoEnum.values()){
            if (codigo.equals(buscandoEnum.getCodigo())){
                return buscandoEnum;
            }
        }

        throw new IllegalArgumentException("Buscando não encontrado");
    }
}
