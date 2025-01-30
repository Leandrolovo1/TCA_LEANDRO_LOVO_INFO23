package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.db.FabricaConexao;
import com.model.Produtos_Venda;
import com.model.interfaces.Produtos_VendaDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Produtos_VendaDAOImpl implements Produtos_VendaDAO {
    private ObservableList<Produtos_Venda> listaReceita = FXCollections.observableArrayList();

    public ObservableList<Produtos_Venda> preencher_Tabela_Produtos_Vendas() {
        String sql = "SELECT * FROM Produtos_venda";

        try (Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                listaReceita.add(new Produtos_Venda(
                        rs.getInt("id_produto"),
                        rs.getInt("id_venda"),
                        rs.getInt("quantidade"),
                        rs.getFloat("subtotal")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaReceita;
    }
}
