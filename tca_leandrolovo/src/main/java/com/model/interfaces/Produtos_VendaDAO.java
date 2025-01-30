package com.model.interfaces;

import com.model.Produtos_Venda;

import javafx.collections.ObservableList;

public interface Produtos_VendaDAO {
    public ObservableList<Produtos_Venda> preencher_Tabela_Produtos_Vendas();
}
