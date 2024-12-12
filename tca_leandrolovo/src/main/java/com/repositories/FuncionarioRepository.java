package com.repositories;

import java.sql.SQLException;

import com.DAO.FuncionarioDAOImpl;
import com.model.Funcionario;


public class FuncionarioRepository {
    FuncionarioDAOImpl FuncionarioDAOimpl;

    public FuncionarioRepository() throws SQLException
    {
        this.FuncionarioDAOimpl = new FuncionarioDAOImpl();
    }

    public void realizarLoginFuncionario(Funcionario funcionario) throws SQLException
    {
        FuncionarioDAOimpl.realizar_Login_Funcionario(funcionario);
    }

}
