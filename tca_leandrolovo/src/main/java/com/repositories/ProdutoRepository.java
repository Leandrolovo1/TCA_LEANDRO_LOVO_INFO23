package com.repositories;

import java.sql.SQLException;

import com.DAO.ProdutoDAOImpl;
import com.model.Estoque;
import com.model.Produtos;
import com.model.Venda;

import javafx.collections.ObservableList;

public class ProdutoRepository {
    ProdutoDAOImpl ProdutoDAOimpl;

    public ProdutoRepository() throws SQLException{
        this.ProdutoDAOimpl = new ProdutoDAOImpl();
    }
    
    public boolean cadastrarProduto(Produtos produto, Estoque estoque) throws SQLException{
        return ProdutoDAOimpl.cadastrarProduto(produto, estoque);
    }

    public ObservableList<Produtos> preencher_Tabela_Produtos(){
        return ProdutoDAOimpl.preencher_Tabela_Produtos();
    }
    public boolean editarProduto(Produtos produto, String coluna, int tipo) throws SQLException{
        return ProdutoDAOimpl.editarProduto(produto, coluna, tipo);
    }
    public int registrarVenda(Venda venda) throws SQLException{
        return ProdutoDAOimpl.registrarVenda(venda);
    }

    public boolean registrarProdutoVenda(int idVenda, Produtos produto) throws SQLException{
        return ProdutoDAOimpl.registrarProdutoVenda(idVenda, produto);
    }
    public boolean editarProdutoEstoque(Estoque estoque, int id_funcionario) throws SQLException{
        return ProdutoDAOimpl.editarProdutoEstoque(estoque, id_funcionario);
    }
    public boolean deletarProduto(Produtos produto) throws SQLException{
        return ProdutoDAOimpl.deletarProduto(produto);
    }
}
