package com.repositories;

import com.DAO.Produtos_VendaDAOImpl;
import com.model.Produtos_Venda;

import javafx.collections.ObservableList;

public class Produtos_VendaRepository {
    Produtos_VendaDAOImpl Produtos_VendaDAOimpl;

     public Produtos_VendaRepository() {
        Produtos_VendaDAOimpl = new Produtos_VendaDAOImpl();
    }

    public ObservableList<Produtos_Venda> preencher_Tabela_Produtos_Vendas() {
        return Produtos_VendaDAOimpl.preencher_Tabela_Produtos_Vendas();
    }
}
