package com.repositories;

import java.sql.SQLException;

import com.DAO.ProdutoDAOImpl;
import com.model.Estoque;
import com.model.Produtos;

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
}
