package com.model;

public enum TipoProdutoEnum {
    Acessorios(1),
    Calcados(2),
    Skate(3),
    Vestuario(4);

    private Integer code;
    
    TipoProdutoEnum(Integer code)
    {
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }
    public static String getDescricaoPorCodigo(Integer code) {
        for (TipoProdutoEnum tipo : TipoProdutoEnum.values()) {
            if (tipo.getCode().equals(code)) {
                return tipo.name();
            }
            
        }
        return "Tipo de produto não encontrado";
    }

    
    
}
