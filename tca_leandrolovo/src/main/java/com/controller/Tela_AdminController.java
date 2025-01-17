package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.App;
import com.model.Produtos;
import com.model.Funcionario;
import com.repositories.FuncionarioRepository;
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
    private TableColumn<Produtos, Integer> CL_ID_produto;
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

    FuncionarioRepository funcionarioRepository;
    @FXML
    private TableView<Funcionario> Tb_Funcionarios;
    @FXML
    private TableColumn<Funcionario, Integer> CL_ID_funcionario;
    @FXML
    private TableColumn<Funcionario, String> CL_nome_funcionario;
    @FXML
    private TableColumn<Funcionario, String> CL_email_funcionario;
    @FXML
    private TableColumn<Funcionario, String> CL_telefone_funcionario;
    @FXML
    private TableColumn<Funcionario, Integer> CL_numero_vendas_produto;

    private ObservableList<Funcionario> listaFuncionarios = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws IOException, SQLException {
        produtoRepository = new ProdutoRepository();
        configurarColunas_produtos();
        listaProdutos = produtoRepository.preencher_Tabela_Produtos();
        Tb_Produtos.setItems(listaProdutos);
        /////////////////////////////////////////
        funcionarioRepository = new FuncionarioRepository();
        configurarColunas_funcionarios();
        listaFuncionarios = funcionarioRepository.preencher_Tabela_Funcionarios();
        Tb_Funcionarios.setItems(listaFuncionarios);
    }

    private void configurarColunas_produtos() {
        CL_ID_produto.setCellValueFactory(new PropertyValueFactory<>("id_produto"));
        CL_nome_produto.setCellValueFactory(new PropertyValueFactory<>("nome_produto"));
        CL_marca_produto.setCellValueFactory(new PropertyValueFactory<>("marca_produto"));
        CL_categoria_produto.setCellValueFactory(new PropertyValueFactory<>("categoria_produto"));
        CL_preco_produto.setCellValueFactory(new PropertyValueFactory<>("Preco_produto"));
        CL_quantidade_produto.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    }

    private void configurarColunas_funcionarios() {
        CL_ID_funcionario.setCellValueFactory(new PropertyValueFactory<>("id_funcionario"));
        CL_nome_funcionario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        CL_email_funcionario.setCellValueFactory(new PropertyValueFactory<>("email"));
        CL_telefone_funcionario.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        CL_numero_vendas_produto.setCellValueFactory(new PropertyValueFactory<>("numero_vendas"));
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
