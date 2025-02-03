package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.db.FabricaConexao;
import com.model.Estoque;
import com.model.Produtos;
import com.model.Venda;
import com.model.interfaces.ProdutoDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProdutoDAOImpl implements ProdutoDAO {
    private ObservableList<Produtos> listaProdutos = FXCollections.observableArrayList();

    public boolean cadastrarProduto(Produtos produto, Estoque estoque) throws SQLException {
        // Definindo a query SQL para inserir um novo funcionário
        String sql = "CALL inserir_produto_e_estoque(?,?,?,?,?,?)";

        try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            // Prepara a instrução SQL para execução
            stmt.setString(1, produto.getNome_produto()); // Define o nome do produto
            stmt.setString(2, produto.getMarca_produto());
            stmt.setObject(3, produto.getCategoria_produto());
            stmt.setFloat(4, produto.getPreco_produto());
            stmt.setInt(5, estoque.getQuantidade());
            stmt.setString(6, estoque.getTipo_movimentacao());

            int rowsAffected = stmt.executeUpdate();

            // Verifica se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                return true;

            } else {
                return false;

            }

        } catch (SQLException e) {
            // Caso ocorra algum erro, é registrado no log e uma mensagem de erro é exibida
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
            return false;
        }
    }

    public ObservableList<Produtos> preencher_Tabela_Produtos() {
        // *view*//
        String sql = "SELECT * FROM view_produtos_estoque_total";

        try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                listaProdutos.add(new Produtos(
                        rs.getInt("id_produto"),
                        rs.getString("nome_produto"),
                        rs.getString("categoria"),
                        rs.getString("marca"),
                        rs.getFloat("preco_produto"),
                        rs.getInt("quantidade_total")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProdutos;
    }

    public boolean atualizarProduto(Produtos produto, String coluna, int tipo) throws SQLException {
        String sql = null;
        if (tipo == 1) sql = "UPDATE Produto SET " + coluna + " = ? WHERE id_produto = ? ";
        if (tipo == 2) sql = "UPDATE Estoque SET " + coluna + " = " + coluna + " + ?, `tipo_movimentacao` = 'Entrada', `data_movimentacao` = CURRENT_TIMESTAMP WHERE fk_id_produto = ?";
        if (tipo == 3) sql = "UPDATE Estoque SET " + coluna + " = " + coluna + " - ?, `tipo_movimentacao` = 'Entrada', `data_movimentacao` = CURRENT_TIMESTAMP WHERE fk_id_produto = ?";
        try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            if(tipo != 1) stmt.setInt(1, produto.getQuantidade());
            else stmt.setString(1, produto.getNome_produto());
            stmt.setInt(2, produto.getId_produto());
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
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e.getMessage());
            return false;
        }
    }
    
    public int registrarVenda(Venda venda) throws SQLException
    {
       
        String sql = "{CALL registrar_venda(?, ?, ?, ?)}";
        
        try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, venda.getFk_id_funcionario());
            stmt.setDouble(2, venda.getValor_pago());
            stmt.setDouble(3, venda.getTotal_venda());
            stmt.setDouble(4, venda.getTroco());
            
            ResultSet rs = stmt.executeQuery(); // Executa e recebe o ID da venda
            
            if (rs.next()) {
                return rs.getInt("id_venda"); // Retorna o ID da venda gerado
            }
        }
        return -1; // Retorna -1 se algo der errado
    }

    public boolean registrarProdutoVenda(int idVenda, Produtos produto) throws SQLException {
        String sql = "{CALL registrar_produto_venda(?, ?, ?, ?)}";
    
        try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idVenda); // ID da venda
            stmt.setInt(2, produto.getId_produto()); // ID do produto
            stmt.setInt(3, produto.getQuantidade()); // Quantidade
            stmt.setDouble(4, produto.getPreco_produto() * produto.getQuantidade()); // Subtotal
    
            int rowsAffected = stmt.executeUpdate();
            boolean result = rowsAffected > 0;
            Estoque estoque = new Estoque(produto.getQuantidade(), "Saida");
            atualizarEstoque(estoque, produto.getId_produto());
            return result;
        } catch (SQLException e) {
            // Caso ocorra algum erro, é registrado no log e uma mensagem de erro é exibida
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao registrar_produto_venda: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarEstoque(Estoque estoque, int id_funcionario)
    {
        String sql = "INSERT INTO Estoque(fk_id_produto, quantidade, tipo_movimentacao) VALUES (?,?,?)";
        try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id_funcionario);
            stmt.setInt(2, estoque.getQuantidade());
            stmt.setString(3, estoque.getTipo_movimentacao());
            
            int rowsAffected = stmt.executeUpdate();

            // Verifica se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                return true;

            } else {
                return false;

            }
        }catch (SQLException e) {
            // Caso ocorra algum erro, é registrado no log e uma mensagem de erro é exibida
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque: " + e.getMessage());
            return false;
        }

    }

    public boolean deletarProduto(Produtos produto) throws SQLException {
        String sql = "UPDATE Produto SET ativo = 0 WHERE Produto.id_produto = ?";
        try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, produto.getId_produto());

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
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e.getMessage());
            return false;
        }
    }
} 

        
    




