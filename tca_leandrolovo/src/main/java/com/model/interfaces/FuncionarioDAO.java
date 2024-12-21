package com.model.interfaces;

import java.sql.SQLException;

import com.model.Funcionario;

public interface FuncionarioDAO {
    public boolean cadastrarFuncionario(Funcionario funcionario) throws SQLException;
    public boolean realizar_Login_Funcionario(Funcionario funcionario) throws SQLException;
}
