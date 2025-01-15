package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.App;
import com.model.Produtos;
import com.repositories.ProdutoRepository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Tela_AdminController {
    ProdutoRepository produtoRepository;
    @FXML
    private TableView<Produtos> Tb_Produtos;
    @FXML
    private TableColumn<Produtos, String> CL_nome_produto;
    @FXML
    private TableColumn<Produtos, String> CL_marca_produto;
    @FXML
    private TableColumn<Produtos, String> CL_categoria_produto;
    @FXML
    private TableColumn<Produtos, Float> CL_preco_produto;
    @FXML
    private TableColumn<Produtos, Integer> CL_quantidade_produto;

    private ObservableList<Produtos> listaProdutos = FXCollections.observableArrayList();

    
    @FXML
    public void initialize() throws IOException, SQLException{
        produtoRepository = new ProdutoRepository();
        configurarColunas();
        // Preencher a lista de produtos com dados do banco
        listaProdutos = produtoRepository.preencher_Tabela_Produtos();

        // Associar a lista de produtos ao TableView
        Tb_Produtos.setItems(listaProdutos);
    }

    private void configurarColunas() {
        CL_nome_produto.setCellValueFactory(new PropertyValueFactory<>("Nome_produto"));
        CL_marca_produto.setCellValueFactory(new PropertyValueFactory<>("marca"));
        CL_categoria_produto.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        CL_preco_produto.setCellValueFactory(new PropertyValueFactory<>("Preco_produto"));
        CL_quantidade_produto.setCellValueFactory(new PropertyValueFactory<>("Preco_produto"));
    }

    @FXML
    private void switchToTela_Principal() throws IOException {
        App.setRoot("Tela_Principal");
    }

    @FXML
    private void switchToTela_Cadastrar_Produto() throws IOException {
        App.setRoot("Tela_Cadastrar_Produto");

    }

    @FXML
    private void switchToTela_Cadastrar_Funcionario() throws IOException {
        App.setRoot("Tela_Cadastrar_Funcionario");
    }

}
