package com.controller;

import java.io.IOException;

import com.App;

import javafx.fxml.FXML;

public class Tela_AdminController {
    @FXML
    private void switchToTela_Principal() throws IOException {
        App.setRoot("Tela_Principal");
    }
    @FXML
    private void switchToTela_Cadastrar_Produto() throws IOException {
        App.setRoot("Tela_Cadastrar_Produto");
    }
    
    /*@FXML
    private void realizar_LoginAdm() throws IOException {
        App.setRoot("Tela_Admin");
    }*/
}
