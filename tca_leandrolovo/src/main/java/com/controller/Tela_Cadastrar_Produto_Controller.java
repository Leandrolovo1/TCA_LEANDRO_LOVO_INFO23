package com.controller;

import java.io.IOException;

import com.App;

import javafx.fxml.FXML;

public class Tela_Cadastrar_Produto_Controller {
    @FXML
    private void switchToTela_Admin() throws IOException {
        App.setRoot("Tela_Admin");
    }
    
}
