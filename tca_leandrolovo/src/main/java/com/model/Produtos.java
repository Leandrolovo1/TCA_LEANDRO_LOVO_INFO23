package com.model;

public class Produtos {
    private int id_produto;
    private String nome_produto;
    private String categoria_produto;
    private String marca_produto;
    public Produtos(int id_produto) {
        this.id_produto = id_produto;
    }

    private float preco_produto;
    private int quantidade;

    @Override
    public String toString() {
        return "Produtos [id_produto =" + id_produto + ", nome_produto =" + nome_produto + ", categoria_produto ="
                + categoria_produto + ", marca_produto =" + marca_produto + ", preco_produto =" + preco_produto
                + ", quantidade =" + quantidade + "]";
    }

    public Produtos(int id_produto, int quantidade) {
        this.id_produto = id_produto;
        this.quantidade = quantidade;
    }

    public Produtos(int id_produto, float preco_produto) {
        this.id_produto = id_produto;
        this.preco_produto = preco_produto;
    }

    public Produtos(int id_produto, String nome_produto) {
        this.id_produto = id_produto;
        this.nome_produto = nome_produto;
    }

    public Produtos(int id_produto, String nome_produto, String categoria_produto, String marca_produto,
            float preco_produto, int quantidade) {
        this.id_produto = id_produto;
        this.nome_produto = nome_produto;
        this.categoria_produto = categoria_produto;
        this.marca_produto = marca_produto;
        this.preco_produto = preco_produto;
        this.quantidade = quantidade;
    }

    public Produtos(String marca_produto, String nome_produto, String categoria_produto, float preco_produto) {
        this.marca_produto = marca_produto;
        this.nome_produto = nome_produto;
        this.categoria_produto = categoria_produto;
        this.preco_produto = preco_produto;
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

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }
    
    public String getMarca_produto() {
        return marca_produto;
    }

    public void setMarca_produto(String marca_produto) {
        this.marca_produto = marca_produto;
    }

    public String getCategoria_produto() {
        return categoria_produto;
    }

    public void setCategoria_produto(String categoria_produto) {
        this.categoria_produto = categoria_produto;
    }

    public float getPreco_produto() {
        return preco_produto;
    }

    public void setPreco_produto(float preco_produto) {
        this.preco_produto = preco_produto;
    }
}
