package com.model;

public class Produtos_Venda {
    int id_produto;
    int id_venda;
    int quantidade;
    float subtotal;

    
    public Produtos_Venda(int id_produto, int id_venda, int quantidade,float subtotal) {
        this.id_produto = id_produto;
        this.id_venda = id_venda;
        this.subtotal = subtotal;
        this.quantidade = quantidade;
    }

    public Produtos_Venda(int id_venda, int id_produto, int quantidade) {
        this.id_venda = id_venda;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
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
    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
}
