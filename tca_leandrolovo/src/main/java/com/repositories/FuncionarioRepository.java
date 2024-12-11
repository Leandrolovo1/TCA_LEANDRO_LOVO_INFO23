package com.repositories;

import java.sql.SQLException;

import com.DAO.FuncionarioDAOimpl;
import com.model.Funcionario;


public class FuncionarioRepository {
    FuncionarioDAOimpl FuncionarioDAOimpl;

    public FuncionarioRepository() throws SQLException
    {
        this.FuncionarioDAOimpl = new FuncionarioDAOimpl();
    }

    public void realizarLoginFuncionario(Funcionario funcionario) throws SQLException
    {
        FuncionarioDAOimpl.realizar_Login_Funcionario(funcionario);
    }

}
