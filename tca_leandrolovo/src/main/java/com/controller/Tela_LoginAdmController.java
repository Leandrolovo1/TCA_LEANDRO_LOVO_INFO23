package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import com.App;
import com.model.Administrador;
import com.repositories.AdministradorRepository;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

public class Tela_LoginAdmController {

    @FXML
    private TextField TF_nome_admin;
    @FXML
    private TextField TF_senha_admin;
    @FXML
    private Button btnEntrar;

    AdministradorRepository administradorRepository;

    @FXML
    private void switchToTela_Principal() throws IOException {
        App.setRoot("Tela_Principal");
    }

    @FXML
    private void realizar_LoginAdm() throws IOException, SQLException {

        administradorRepository = new AdministradorRepository();

        String nome = TF_nome_admin.getText();
        String senha = TF_senha_admin.getText();

        // verifica se os campos estao vazio!
        if (nome.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }
        // verifica se o nome contém números
        //a expressão regular ".*\\d.*" verifica se há pelo menos um dígito em qualquer posição dentro da string
        if (nome.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "O nome não pode conter números.");
            return;
        }

        Administrador administrador = new Administrador(nome, senha);

        boolean Login_Sucesso = administradorRepository.realizarLoginAdministrador(administrador);

        if (Login_Sucesso) {
            App.setRoot("Tela_Admin");
        } else {
            // Caso o login falhe, exibe uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Login MAU-sucedido!");
        }

    }
}