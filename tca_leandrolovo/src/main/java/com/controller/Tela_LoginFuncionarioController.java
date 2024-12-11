package com.controller;

import com.App;
import com.db.FabricaConexao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Tela_LoginFuncionarioController {
    @FXML 
    private TextField TF_nome_funcionario;

    @FXML 
    private TextField TF_senha_funcionario;

    @FXML
    private void switchToTela_Principal() throws IOException {
        App.setRoot("Tela_Principal");
    }
    @FXML
    private void realizar_Login_Funcionario() throws IOException {
        try {
            Connection con = FabricaConexao.faz_Conexao();//conecta ao banco de dados 
            String sql = "select * from Funcionario_Login where nome = ? and senha = ?"; // verifica na tabela funcionarios nome e senha
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, TF_nome_funcionario.getText());
            stmt.setString(2, TF_senha_funcionario.getText());
            // Executa a consulta SQL e obtém os resultados na variável rs
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
            }else{
                JOptionPane.showMessageDialog(null, "Login MAU-sucedido!");
            }
            FabricaConexao.closeConnection(con, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    @FXML 
    private void switchToTela_Login_Cadastrar_Funcionario() throws IOException
    {
        App.setRoot("Tela_Login_Cadastrar_Funcionario");

    }
}