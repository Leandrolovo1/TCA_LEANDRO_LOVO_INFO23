package com.repositories;

import java.sql.SQLException;

import com.DAO.FuncionarioDAOImpl;
import com.model.Funcionario;

public class FuncionarioRepository {

    // Instância da classe FuncionarioDAOImpl que irá realizar operações no banco de dados
    FuncionarioDAOImpl FuncionarioDAOimpl;

    // Construtor da classe FuncionarioRepository
    public FuncionarioRepository() throws SQLException {
        // Inicializa a instância de FuncionarioDAOImpl, que gerencia a interação com o banco de dados
        this.FuncionarioDAOimpl = new FuncionarioDAOImpl();
    }

    // Método que chama o método 'realizar_Login_Funcionario' de FuncionarioDAOImpl
    // Retorna um booleano indicando se o login foi bem-sucedido (true) ou não (false)
    public boolean realizarLoginFuncionario(Funcionario funcionario) throws SQLException {
        // Chama o método de login da classe FuncionarioDAOImpl e retorna o resultado (true/false)

        return FuncionarioDAOimpl.realizar_Login_Funcionario(funcionario);
    }
    public boolean cadastrarFuncionario(Funcionario funcionario) throws SQLException{
        return FuncionarioDAOimpl.cadastrarFuncionario(funcionario);
    }

}
