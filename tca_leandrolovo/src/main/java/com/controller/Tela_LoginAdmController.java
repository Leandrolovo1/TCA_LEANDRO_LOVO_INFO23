package com.controller;

import java.io.IOException;

import com.App;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;


public class Tela_LoginAdmController {
    
    @FXML 
    private TextField TF_nome_admin;
    @FXML 
    private TextField TF_senha_admin;

    @FXML
    private void switchToTela_Principal() throws IOException {
        App.setRoot("Tela_Principal");
    }
    
    @FXML
    private void realizar_LoginAdm() throws IOException {
        App.setRoot("Tela_Admin");
    }
}