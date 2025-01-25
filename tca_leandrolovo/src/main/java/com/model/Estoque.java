package com.model;

import java.sql.Date;

public class Estoque {
    int id_estoque;
    int fk_id_produto;
    int quantidade;

    public Estoque(int quantidade) {
        this.quantidade = quantidade;
    }

    String tipo_movimentacao;
    Date Data_movimentacao;

    public int getFk_id_produto() {
        return fk_id_produto;
    }

    public Estoque(int quantidade, String tipo_movimentacao) {
        this.quantidade = quantidade;
        this.tipo_movimentacao = tipo_movimentacao;
    }

    public Estoque(int id_estoque, int fk_id_produto, int quantidade, String tipo_movimentacao,
            Date data_movimentacao) {
        this.id_estoque = id_estoque;
        this.fk_id_produto = fk_id_produto;
        this.quantidade = quantidade;
        this.tipo_movimentacao = tipo_movimentacao;
        Data_movimentacao = data_movimentacao;
    }

    // Getters e Setters
    public int getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(int id_estoque) {
        this.id_estoque = id_estoque;
    }

    public int getId_produto() {
        return fk_id_produto;
    }

    public void setId_produto(int fk_id_produto) {
        this.fk_id_produto = fk_id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setFk_id_produto(int fk_id_produto) {
        this.fk_id_produto = fk_id_produto;
    }

    public String getTipo_movimentacao() {
        return tipo_movimentacao;
    }

    public void setTipo_movimentacao(String tipo_movimentacao) {
        this.tipo_movimentacao = tipo_movimentacao;
    }

    public Date getData_movimentacao() {
        return Data_movimentacao;
    }

    public void setData_movimentacao(Date data_movimentacao) {
        Data_movimentacao = data_movimentacao;
    }
}
