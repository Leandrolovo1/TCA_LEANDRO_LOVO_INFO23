package com.controller;

import java.io.IOException;
import com.App;
import javafx.fxml.FXML;

public class Tela_Cadastrar_FuncionarioController {
    @FXML
    private void switchToTela_LoginFuncionario() throws IOException {
        App.setRoot("Tela_LoginFuncionario");
    }

    @FXML
    private void switchToTela_Principal() throws IOException {
        App.setRoot("Tela_Principal");
    }
}
