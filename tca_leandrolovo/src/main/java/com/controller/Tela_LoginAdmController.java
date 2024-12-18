package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.App;
import com.db.FabricaConexao;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;


public class Tela_LoginAdmController {
    
    @FXML 
    private TextField TF_nome_admin;
    @FXML 
    private TextField TF_senha_admin;

    @FXML
    private void switchToTela_Principal() throws IOException {
        App.setRoot("Tela_Principal");
    }
    
    @FXML
    private void realizar_LoginAdm() throws IOException {
        String sql = "select * from Administrador where nome = ? and senha = ?";
        
        try(Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            
            
            stmt.setString(1, TF_nome_admin.getText());
            stmt.setString(2, TF_senha_admin.getText());
            
            
            try (ResultSet rs = stmt.executeQuery();){
                if (rs.next()) {
                    App.setRoot("Tela_Admin");
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                }else{
                    JOptionPane.showMessageDialog(null, "Login mau-sucedido!");
                }  
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();

        }

    }
}