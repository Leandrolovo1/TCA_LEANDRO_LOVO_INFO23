package com.controller;

import java.io.IOException;

import com.App;

import javafx.fxml.FXML;

public class Tela_LoginController {

    @FXML
    private void switchToTela_Vendas() throws IOException {
        App.setRoot("Tela_Vendas");
    }
    @FXML
    private void switchToTela_Sistema() throws IOException {
        App.setRoot("Tela_Sistema");
    }
}
