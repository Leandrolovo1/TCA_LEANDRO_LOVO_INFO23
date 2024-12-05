package com.controller;

import com.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class Tela_VendasController {

    @FXML
    private void switchToTela_Login() throws IOException {
        App.setRoot("Tela_Login");
    }
}