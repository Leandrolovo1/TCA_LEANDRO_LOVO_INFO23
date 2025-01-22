package com.model.interfaces;

import java.sql.SQLException;

import com.model.Estoque;
import com.model.Produtos;

import javafx.collections.ObservableList;

public interface ProdutoDAO {
    public boolean cadastrarProduto(Produtos produto, Estoque estoque) throws SQLException;
    public ObservableList<Produtos> preencher_Tabela_Produtos() throws SQLException;
    public boolean atualizarProduto(Produtos produto, String coluna, int tipo) throws SQLException;

}
