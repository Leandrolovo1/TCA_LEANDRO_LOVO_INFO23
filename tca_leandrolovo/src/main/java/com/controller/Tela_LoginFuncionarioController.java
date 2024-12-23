package com.controller;

import com.App;
import com.repositories.FuncionarioRepository;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.model.Funcionario;

public class Tela_LoginFuncionarioController {

    // Declaração dos campos de texto onde o nome e a senha serão inseridos
    @FXML 
    private TextField TF_nome_funcionario;

    @FXML 
    private TextField TF_senha_funcionario;

    // Instância do repositório de funcionários para gerenciar a lógica de login
    FuncionarioRepository funcionarioRepository;

    // Método que alterna para a tela principal
    @FXML
    private void switchToTela_Principal() throws IOException {
        // Muda a tela para "Tela_Principal"
        App.setRoot("Tela_Principal");
    }

    // Método que realiza o login do funcionário
    @FXML
    private void realizar_Login() throws IOException, SQLException {

        // Inicializa o repositório de funcionários
        funcionarioRepository = new FuncionarioRepository();

        // Obtém os dados inseridos pelo usuário nos campos de texto
        String nome = TF_nome_funcionario.getText();
        String senha = TF_senha_funcionario.getText();

        // Cria um objeto Funcionario com os dados fornecidos pelo usuário
        Funcionario funcionario = new Funcionario(nome, senha);

        // Chama o método de login do repositório, passando o objeto Funcionario
        boolean Login_Sucesso = funcionarioRepository.realizarLoginFuncionario(funcionario);

        // Se o login for bem-sucedido, troca para a tela do funcionário
        if (Login_Sucesso) {
            switchToTela_Funcionario();
            JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
        } else {
            // Caso o login falhe, exibe uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Login MAU-sucedido!");
        }
    }

    // Método que alterna para a tela de cadastro de funcionários
    @FXML 
    private void switchToTela_Cadastrar_Funcionario() throws IOException {
        // Muda a tela para "Tela_Cadastrar_Funcionario"
        App.setRoot("Tela_Cadastrar_Funcionario");
    }

    // Método que alterna para a tela do funcionário (após login bem-sucedido)
    @FXML 
    private void switchToTela_Funcionario() throws IOException {
        // Muda a tela para "Tela_Funcionario"
        App.setRoot("Tela_Funcionario");
    }
}
