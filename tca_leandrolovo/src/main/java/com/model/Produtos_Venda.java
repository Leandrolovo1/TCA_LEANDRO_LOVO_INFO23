package com.model;

public class Produtos_Venda {
    int id_produto;
    int id_venda;
    int quantidade;
    float preco_unidade;
    float subtotal = quantidade * preco_unidade;

    public Produtos_Venda(int id_venda, int id_produto, int quantidade, float preco_unidade) {
        this.id_venda = id_venda;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco_unidade = preco_unidade;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco_unidade() {
        return preco_unidade;
    }

    public void setPreco_unidade(float preco_unidade) {
        this.preco_unidade = preco_unidade;
    }
}
