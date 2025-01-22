package com.model;

public class Funcionario {
    
    private int id_funcionario;
    private int fk_id_Adm = 1;
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    private int numero_vendas;

    public Funcionario(int id_funcionario, String nome, String email, String telefone, int numero_vendas) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.numero_vendas = numero_vendas;
    }
    
    public Funcionario(int fk_id_Adm, String nome, String senha) {
        this.fk_id_Adm = fk_id_Adm;
        this.nome = nome;
        this.senha = senha;
    }
    
    public Funcionario(String nome, String senha, String email, String telefone) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
    }
    
    public Funcionario(int id_funcionario, String senha) {
        this.id_funcionario = id_funcionario;
        this.senha = senha;
    }
    
    public Funcionario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }
    public int getFk_id_Adm() {
        return fk_id_Adm;
    }
    
    public void setFk_id_Adm(int fk_id_Adm) {
        this.fk_id_Adm = fk_id_Adm;
    }
    
    public int getId_funcionario() {
        return id_funcionario;
    }
    
    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public int getNumero_vendas() {
        return numero_vendas;
    }
    
    public void setNumero_vendas(int numero_vendas) {
        this.numero_vendas = numero_vendas;
    }
}
