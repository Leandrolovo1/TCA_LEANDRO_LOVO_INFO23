package com.model;

public class ItensVenda {
    int id_item;
    int id_venda;
    int id_produto;
    int quantidade;
    float preco_unidade;

    
    public ItensVenda(int id_item, int id_venda, int id_produto, int quantidade, float preco_unidade) {
        this.id_item = id_item;
        this.id_venda = id_venda;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco_unidade = preco_unidade;
    }
    public int getId_item() {
        return id_item;
    }
    public void setId_item(int id_item) {
        this.id_item = id_item;
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
