package com.controller;

import java.io.IOException;

import com.App;

import javafx.fxml.FXML;

public class Tela_SistemaController {

    @FXML
    private void switchToTela_Login() throws IOException {
        App.setRoot("Tela_Login");
    }
    
}