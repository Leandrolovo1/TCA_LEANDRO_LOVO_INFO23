package com.repositories;

import java.sql.SQLException;

import com.DAO.FuncionarioDAOImpl;
import com.model.Funcionario;

import javafx.collections.ObservableList;

public class FuncionarioRepository {

    // Instância da classe FuncionarioDAOImpl que irá realizar operações no banco de
    // dados
    FuncionarioDAOImpl FuncionarioDAOimpl;

    // Construtor da classe FuncionarioRepository
    public FuncionarioRepository() throws SQLException {
        // Inicializa a instância de FuncionarioDAOImpl, que gerencia a interação com o
        // banco de dados
        this.FuncionarioDAOimpl = new FuncionarioDAOImpl();
    }

    public boolean realizarLoginFuncionario(Funcionario funcionario) throws SQLException 
    {
        // Chama o método de login da classe FuncionarioDAOImpl e retorna o resultado
        // (true/false)
        return FuncionarioDAOimpl.realizar_Login_Funcionario(funcionario);
    }

    public boolean cadastrarFuncionario(Funcionario funcionario) throws SQLException {
        return FuncionarioDAOimpl.cadastrarFuncionario(funcionario);
    }

    public ObservableList<Funcionario> preencher_Tabela_Funcionarios() {
        return FuncionarioDAOimpl.preencher_Tabela_Funcionarios();
    }

    public boolean deletarFuncionario(Funcionario funcionario) throws SQLException {
        return FuncionarioDAOimpl.deletarFuncionario(funcionario);
    }

    public boolean atualizarFuncionario(Funcionario funcionario, String coluna) throws SQLException {
        return FuncionarioDAOimpl.atualizarFuncionario(funcionario, coluna);
    }
}
