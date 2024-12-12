package com.model.interfaces;

import java.sql.SQLException;

import com.model.Funcionario;

public interface FuncionarioDAO {
    public void cadastrarFuncionario(Funcionario funcionario) throws SQLException;
}
