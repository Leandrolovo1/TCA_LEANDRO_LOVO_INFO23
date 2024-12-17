package com.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import com.controller.Tela_LoginFuncionarioController;
import java.sql.Connection;
import com.db.FabricaConexao;
import com.model.Funcionario;
import com.model.interfaces.FuncionarioDAO;

public class FuncionarioDAOImpl implements FuncionarioDAO {

    private Connection con;

    public FuncionarioDAOImpl() throws SQLException {
        this.con = FabricaConexao.faz_Conexao();
    }

    public void cadastrarFuncionario(Funcionario Funcionario) throws SQLException {
        String sql = "INSERT INTO Funcionario_Login (nome, senha, email, telefoneo) VALUES (? ? ? ? )";
        PreparedStatement stmt = null;
        // Connection con = FabricaConexao.faz_Conexao() ;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(2, Funcionario.getNome());
            stmt.setString(3, Funcionario.getSenha());
            stmt.setString(4, Funcionario.getEmail());
            stmt.setString(5, Funcionario.getTelefone());

            JOptionPane.showMessageDialog(null, "salvo com sucesso");
        } catch (Exception e) {
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro no salvar" + e);
        } finally {
            FabricaConexao.closeConnection(con, stmt);
        }
    }

    public void realizar_Login_Funcionario(Funcionario funcionario) throws SQLException // fazer
    {
        try {
            // Connection con = FabricaConexao.faz_Conexao();//conecta ao banco de dados
            String sql = "select * from Funcionario where nome = ? and senha = ?"; // verifica na tabela
                                                                                         // funcionarios nome e senha
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSenha());
            // Executa a consulta SQL e obtém os resultados na variável rs
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                // switchToTela_Funcionario(); arrumar isso, colocar um jeito de ir para proxima
                // tela (tela_funcionario)

            } else {
                JOptionPane.showMessageDialog(null, "Login MAU-sucedido!");
            }
            FabricaConexao.closeConnection(con, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
