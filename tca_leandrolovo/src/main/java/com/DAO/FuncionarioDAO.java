package com.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import java.sql.Connection;
import com.db.FabricaConexao;
import com.model.Funcionario;

public class FuncionarioDAO {
    public void cadastrarFuncionario(Funcionario Funcionario) throws SQLException {
        String sql = "INSERT INTO Funcionario_Login (nome, senha, email, telefone, nascimento) VALUES (? ? ? ? ?)";
        PreparedStatement stmt = null;
        Connection con = FabricaConexao.faz_Conexao() ;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(2, Funcionario.getNome());
            stmt.setString(3, Funcionario.getSenha());
            stmt.setString(4, Funcionario.getEmail());
            stmt.setString(5, Funcionario.getTelefone());

            // Converte java.util.Date para java.sql.Date
            java.sql.Date nascimentoSql = new java.sql.Date(Funcionario.getNascimento().getTime());
            stmt.setDate(6, nascimentoSql);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "salvo com sucesso");
        } catch (Exception e) {
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE,null,e);
            JOptionPane.showMessageDialog(null, "Erro no salvar" +e);
        }finally
        {
            FabricaConexao.closeConnection(con,stmt);
        }
    }
}
