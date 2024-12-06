package com.controller;

import com.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class Tela_LoginFuncionarioController {

    @FXML
    private void switchToTela_Principal() throws IOException {
        App.setRoot("Tela_Principal");
    }
}