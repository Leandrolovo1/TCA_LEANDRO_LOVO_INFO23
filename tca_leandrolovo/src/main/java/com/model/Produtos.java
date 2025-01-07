package com.model;

public class Produtos {
    int id_produto;
    String marca_produto;
    String nome_produto;
    TipoProdutoEnum tipo;
    float preco_produto;

    
    public Produtos(String marca_produto, String nome_produto, TipoProdutoEnum tipo, float preco_produto) {
        this.marca_produto = marca_produto;
        this.nome_produto = nome_produto;
        this.tipo = tipo;
        this.preco_produto = preco_produto;
    }
    public String getMarca_produto() {
        return marca_produto;
    }
    public void setMarca_produto(String marca_produto) {
        this.marca_produto = marca_produto;
    }
    public String getNome_produto() {
        return nome_produto;
    }
    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }
    public TipoProdutoEnum getTipo() {
        return tipo;
    }
    public void setTipo(TipoProdutoEnum tipo) {
        this.tipo = tipo;
    }
    public float getPreco_produto() {
        return preco_produto;
    }
    public void setPreco_produto(float preco_produto) {
        this.preco_produto = preco_produto;
    }
}
