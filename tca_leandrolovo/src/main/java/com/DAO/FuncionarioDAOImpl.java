package com.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Connection;
import com.db.FabricaConexao;
import com.model.Funcionario;
import com.model.interfaces.FuncionarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FuncionarioDAOImpl implements FuncionarioDAO {
    ObservableList<Funcionario> listaFuncionarios = FXCollections.observableArrayList();

    public boolean cadastrarFuncionario(Funcionario Funcionario) throws SQLException {

        // Definindo a query SQL para inserir um novo funcionário
        String sql = "INSERT INTO Funcionario (nome, senha, email, telefone) VALUES (?, ?, ?, ? )";

        try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            // Prepara a instrução SQL para execução

            // Substitui os parâmetros '?' na query com os dados do objeto 'Funcionario'
            stmt.setString(1, Funcionario.getNome()); // Define o nome do funcionário
            stmt.setString(2, Funcionario.getSenha()); // Define a senha do funcionário
            stmt.setString(3, Funcionario.getEmail()); // Define o email do funcionário
            stmt.setString(4, Funcionario.getTelefone()); // Define o telefone do funcionário

            int rowsAffected = stmt.executeUpdate();

            // Verifica se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                return true;

            } else {
                return false;

            }

        } catch (Exception e) {
            // Caso ocorra algum erro, é registrado no log e uma mensagem de erro é exibida
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
            return false;

        }
    }

    // Método para realizar o login do funcionário
    public boolean realizar_Login_Funcionario(Funcionario funcionario) throws SQLException {

        String sql = "select * from Funcionario where id_funcionario = ? and senha = ?";

        try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {

            /*
             * Define a consulta SQL para verificar se o funcionário existe no banco com o
             * nome e senha fornecidos
             * PreparedStatement stmt = con.prepareStatement(sql);
             * Substitui os parâmetros '?' com os valores do objeto 'Funcionario'
             */

            stmt.setInt(1, funcionario.getId_funcionario()); // Substitui o primeiro '?' com o nome
            stmt.setString(2, funcionario.getSenha()); // Substitui o segundo '?' com a senha

            // Executa a consulta e armazena os resultados
            try (ResultSet rs = stmt.executeQuery();) {

                // Se encontrar um funcionário com o nome e senha fornecidos
                if (rs.next()) {
                    // Retorna true, indicando que o login foi bem-sucedido
                    return true;
                } else {
                    // Se o funcionário não for encontrado, retorna false
                    return false;
                }

            } catch (SQLException e) {
                // Caso ocorra algum erro durante o processo de login, exibe a stack trace e
                // retorna false
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            return false;
        }
    }

    public ObservableList<Funcionario> preencher_Tabela_Funcionarios() {
    String sql = "SELECT id_funcionario, nome, email, telefone, Numero_Vendas FROM Funcionario";

    listaFuncionarios.clear();

    try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            listaFuncionarios.add(new Funcionario(
                    rs.getInt("id_funcionario"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    rs.getInt("Numero_vendas")
            ));
        }

    } catch (SQLException e) {
        Logger.getLogger(FuncionarioDAOImpl.class.getName()).log(Level.SEVERE, "Erro ao preencher a tabela", e);
    }

    return listaFuncionarios;
}
}
