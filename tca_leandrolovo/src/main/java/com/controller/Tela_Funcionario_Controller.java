package com.controller;

import java.io.IOException;

import com.App;

import javafx.fxml.FXML;

public class Tela_Funcionario_Controller {
    @FXML
    private void switchToTela_Principal() throws IOException {
        App.setRoot("Tela_Principal");
    }
}
