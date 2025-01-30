package com.model.interfaces;

import java.sql.SQLException;

import com.model.Estoque;
import com.model.Produtos;
import com.model.Venda;

import javafx.collections.ObservableList;

public interface ProdutoDAO {
    public boolean cadastrarProduto(Produtos produto, Estoque estoque) throws SQLException;
    public ObservableList<Produtos> preencher_Tabela_Produtos() throws SQLException;
    public boolean atualizarProduto(Produtos produto, String coluna, int tipo) throws SQLException;
    public int registrarVenda(Venda venda) throws SQLException;
    public boolean registrarProdutoVenda(int idVenda, Produtos produto) throws SQLException;
    public boolean atualizarEstoque(Estoque estoque, int id_funcionario);


}
