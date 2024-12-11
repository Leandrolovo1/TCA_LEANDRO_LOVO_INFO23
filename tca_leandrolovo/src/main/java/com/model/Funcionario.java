package com.model;

import java.sql.SQLException;
import java.util.Date;

public class Funcionario {
    
    private int id_funcionario;
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    
    public Funcionario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }
    public Funcionario(String nome, String senha, String email, String telefone) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
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
    
}
