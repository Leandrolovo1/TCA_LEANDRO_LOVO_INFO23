package com.model.interfaces;

import java.sql.SQLException;

import com.model.Funcionario;

import javafx.collections.ObservableList;

public interface FuncionarioDAO 
{
    public boolean cadastrarFuncionario(Funcionario funcionario) throws SQLException;
    public boolean realizar_Login_Funcionario(Funcionario funcionario) throws SQLException;
    public ObservableList<Funcionario> preencher_Tabela_Funcionarios()throws SQLException;
       
}
