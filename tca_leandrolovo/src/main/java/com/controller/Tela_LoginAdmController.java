package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.App;
import com.model.Administrador;
import com.repositories.AdministradorRepository;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;


public class Tela_LoginAdmController {
    
    @FXML 
    private TextField TF_nome_admin;
    @FXML 
    private TextField TF_senha_admin;

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
        
        Administrador administrador = new Administrador(nome, senha);

        boolean Login_Sucesso = administradorRepository.realizarLoginAdministrador(administrador);

        if (Login_Sucesso) {
            App.setRoot("Tela_Admin");
            JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
        } else {
            // Caso o login falhe, exibe uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Login MAU-sucedido!");
        }

    }
}