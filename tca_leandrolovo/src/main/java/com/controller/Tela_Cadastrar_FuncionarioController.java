package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.App;
import com.model.Funcionario;
import com.repositories.FuncionarioRepository;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Tela_Cadastrar_FuncionarioController {
    @FXML
    private TextField TF_nome_funcionario;
    @FXML
    private TextField TF_senha_funcionario;
    @FXML
    private TextField TF_email_funcionario;
    @FXML
    private TextField TF_telefone_funcionario;

    FuncionarioRepository funcionarioRepository;

    @FXML
    private void switchToTela_LoginFuncionario() throws IOException {
        App.setRoot("Tela_LoginFuncionario");
    }

    @FXML
    private void switchToTela_Principal() throws IOException {
        App.setRoot("Tela_Principal");
    }

    @FXML
    private void switchToTela_Admin() throws IOException {
        App.setRoot("Tela_Admin");
    }

    @FXML
    private void cadastrarFuncionario() throws IOException, SQLException {

        funcionarioRepository = new FuncionarioRepository();
        String nome = TF_nome_funcionario.getText();
        String senha = TF_senha_funcionario.getText();
        String email = TF_email_funcionario.getText();
        String telefone = TF_telefone_funcionario.getText();
        if (nome.isEmpty()||senha.isEmpty()||email.isEmpty()||telefone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum Campo pode estar vazio.");
            return;
        }else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(null, "O e-mail deve ter um formato válido.");
            return;
        }else if (!telefone.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(null, "O telefone deve ter 10 ou 11 dígitos.");
            return;
        }
        Funcionario funcionario = new Funcionario(nome, senha, email, telefone);

        boolean Cadastro_sucesso = funcionarioRepository.cadastrarFuncionario(funcionario);
        if (Cadastro_sucesso) {
            App.setRoot("Tela_Admin");
            JOptionPane.showMessageDialog(null, "Cadastro bem-sucedido!");
        } else {
            // Caso o login falhe, exibe uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Cadastro MAU-sucedido!");
        }
    }

}
