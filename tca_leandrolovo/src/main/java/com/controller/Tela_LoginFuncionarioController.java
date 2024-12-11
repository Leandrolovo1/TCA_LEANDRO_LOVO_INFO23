package com.controller;

import com.App;
//import com.db.FabricaConexao;
import com.repositories.FuncionarioRepository;
import java.io.IOException;
import java.sql.SQLException;

/*import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;*/
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.model.Funcionario;
import com.repositories.FuncionarioRepository;
import com.DAO.FuncionarioDAOImpl;


public class Tela_LoginFuncionarioController {
    @FXML 
    private TextField TF_nome_funcionario;

    @FXML 
    private TextField TF_senha_funcionario;

    FuncionarioRepository funcionarioRepository;

    @FXML
    private void switchToTela_Principal() throws IOException {
        App.setRoot("Tela_Principal");
    }
    @FXML
    private void realizar_Login() throws IOException, SQLException {
        
        funcionarioRepository = new FuncionarioRepository();

        String nome = TF_nome_funcionario.getText();
        String senha = TF_senha_funcionario.getText();

        Funcionario funcionario = new Funcionario(nome, senha);
        
        funcionarioRepository.realizarLoginFuncionario(funcionario);

    }
    @FXML 
    private void switchToTela_Login_Cadastrar_Funcionario() throws IOException
    {
        App.setRoot("Tela_Login_Cadastrar_Funcionario");

    }
    @FXML 
    private void switchToTela_Funcionario() throws IOException
    {
        App.setRoot("Tela_Funcionario");

    }
}