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

public class FuncionarioDAOImpl implements FuncionarioDAO {

    // Atributo para armazenar a conexão com o banco de dados
    private Connection con;

    // Construtor que inicializa a conexão com o banco de dados
    public FuncionarioDAOImpl() throws SQLException {
        // Estabelece a conexão com o banco chamando o método 'faz_Conexao' da classe 'FabricaConexao'
        this.con = FabricaConexao.faz_Conexao();
    }

    // Método para cadastrar um novo funcionário no banco de dados
    public void cadastrarFuncionario(Funcionario Funcionario) throws SQLException {

        // Definindo a query SQL para inserir um novo funcionário
        String sql = "INSERT INTO Funcionario_Login (nome, senha, email, telefone) VALUES (?, ?, ?, ? )";

        try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)){
            // Prepara a instrução SQL para execução
            
            // Substitui os parâmetros '?' na query com os dados do objeto 'Funcionario'
            stmt.setString(2, Funcionario.getNome());  // Define o nome do funcionário
            stmt.setString(3, Funcionario.getSenha()); // Define a senha do funcionário
            stmt.setString(4, Funcionario.getEmail()); // Define o email do funcionário
            stmt.setString(5, Funcionario.getTelefone()); // Define o telefone do funcionário

            // Exibe uma mensagem informando que o cadastro foi bem-sucedido
            JOptionPane.showMessageDialog(null, "salvo com sucesso");
        } catch (Exception e) {
            // Caso ocorra algum erro, é registrado no log e uma mensagem de erro é exibida
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
   
        }    
}

    // Método para realizar o login do funcionário
    public boolean realizar_Login_Funcionario(Funcionario funcionario) throws SQLException {

        String sql = "select * from Funcionario where nome = ? and senha = ?";

        try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql) ){

            /*  Define a consulta SQL para verificar se o funcionário existe no banco com o nome e senha fornecidos
             * PreparedStatement stmt = con.prepareStatement(sql);
             * Substitui os parâmetros '?' com os valores do objeto 'Funcionario'*/

            stmt.setString(1, funcionario.getNome());  // Substitui o primeiro '?' com o nome
            stmt.setString(2, funcionario.getSenha()); // Substitui o segundo '?' com a senha


            // Executa a consulta e armazena os resultados
            try (ResultSet rs = stmt.executeQuery();){

                // Se encontrar um funcionário com o nome e senha fornecidos
                if (rs.next()) {
                    // Exibe uma mensagem de sucesso caso o login seja bem-sucedido
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                    // Retorna true, indicando que o login foi bem-sucedido
                    return true;
                } else {
                    // Se o funcionário não for encontrado, retorna false
                    return false;
                }

            } catch (SQLException e) {
            // Caso ocorra algum erro durante o processo de login, exibe a stack trace e retorna false
            e.printStackTrace();
            return false;
            }
       
        } catch(SQLException e) {
            return false;
        }
    }
}
