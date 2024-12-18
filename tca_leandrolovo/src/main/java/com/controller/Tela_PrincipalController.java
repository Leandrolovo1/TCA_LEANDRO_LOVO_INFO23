package com.controller;

import java.io.IOException;
import com.App;
import javafx.fxml.FXML;

public class Tela_PrincipalController {

    @FXML
    private void switchToTela_LoginFuncionario() throws IOException {
        App.setRoot("Tela_LoginFuncionario");
    }
    @FXML
    private void switchToTela_LoginAdm() throws IOException {
        App.setRoot("Tela_LoginAdm");
    }
    
    
}
