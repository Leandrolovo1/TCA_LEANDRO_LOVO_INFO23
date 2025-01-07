package com.model;

public class Estoque {
    int id_estoque;
    int id_produto;
    int quantidade;
    
    
    public Estoque(int id_estoque, int id_produto, int quantidade) {
        this.id_estoque = id_estoque;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(int id_estoque) {
        this.id_estoque = id_estoque;
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
    public void adicionarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    public void removerQuantidade(int quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
        } else {
            System.out.println("Estoque insuficiente!");
        }
    }
}


