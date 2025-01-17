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

            stmt.setString(1, produto.getNome_produto()); // Define o nome do funcionário
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

        } catch (Exception e) {
            // Caso ocorra algum erro, é registrado no log e uma mensagem de erro é exibida
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
            return false;

        }
    }

    public ObservableList<Produtos> preencher_Tabela_Produtos(){

        //*view*//
        String sql = "SELECT * FROM view_produtos_estoque_total";

        try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                listaProdutos.add(new Produtos(
                    rs.getInt("id_produto"), 
                    rs.getString("nome_produto"),
                    rs.getString("marca"),
                    rs.getString("categoria"),
                    rs.getFloat("preco_produto"),
                    rs.getInt("quantidade_total")));
            }
            
            //tableViewProdutos.setItems(listaProdutos);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProdutos;
    }
}
