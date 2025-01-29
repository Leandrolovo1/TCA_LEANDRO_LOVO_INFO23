package com;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Stage primaryStage;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        scene = new Scene(loadFXML("Tela_Principal"));
        stage.setTitle("TCA LeandroLovo INFO23");
        stage.setScene(scene);
        stage.show();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/mamonas.png")));
    }

    public static void setRoot(String fxml) throws IOException {
        try {
            Parent root = loadFXML(fxml);
            Scene newScene = new Scene(root);
            primaryStage.setScene(newScene); // Cria uma nova cena
            primaryStage.sizeToScene();
            primaryStage.show(); // Garante que a nova cena será visível
        } catch (IOException e) {
            System.err.println("Erro ao trocar de tela: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    
    @FXML
    private static Parent loadFXML(String fxml) throws IOException {
        try {
            String path = "view/" + fxml + ".fxml";
            
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(path));
            return fxmlLoader.load();
        } catch (IOException e) {
            System.err.println("Erro ao carregar o FXML: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    @FXML
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));
        launch();
    }
}