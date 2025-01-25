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

public class Tela_Funcionario_Controller {

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

    @FXML
    private TableView<Produtos> Tb_Carrinho;
    @FXML
    private TableColumn<Produtos, String> CL_nome_produto_Carrinho;
    @FXML
    private TableColumn<Produtos, Float> CL_preco_produto_carrinho;
    @FXML
    private TableColumn<Produtos, Integer> CL_quantidade_produto_carrinho;

    private ObservableList<Produtos> listaCarrinho = FXCollections.observableArrayList();

    private int idFuncionario;

    // Método chamado para passar o ID do funcionário
    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
        //System.out.println("ID do funcionário recebido: " + idFuncionario);
    }

    @FXML
    public void initialize() {
        try {

            produtoRepository = new ProdutoRepository();
            configurarColunas_produtos();
            configurarColunas_Carrinho();
            listaProdutos = produtoRepository.preencher_Tabela_Produtos();
            Tb_Produtos.setItems(listaProdutos);
            Tb_Carrinho.setItems(listaCarrinho);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar os dados do banco: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }

    @FXML
    private void switchToTela_Principal() throws IOException {
        App.setRoot("Tela_Principal");
    }

    private void configurarColunas_produtos() {
        CL_ID_produto.setCellValueFactory(new PropertyValueFactory<>("id_produto"));
        CL_nome_produto.setCellValueFactory(new PropertyValueFactory<>("nome_produto"));
        CL_marca_produto.setCellValueFactory(new PropertyValueFactory<>("marca_produto"));
        CL_categoria_produto.setCellValueFactory(new PropertyValueFactory<>("categoria_produto"));
        CL_preco_produto.setCellValueFactory(new PropertyValueFactory<>("Preco_produto"));
        CL_quantidade_produto.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    }

    private void configurarColunas_Carrinho() {
        CL_nome_produto_Carrinho.setCellValueFactory(new PropertyValueFactory<>("nome_produto"));
        CL_preco_produto_carrinho.setCellValueFactory(new PropertyValueFactory<>("Preco_produto"));
        CL_quantidade_produto_carrinho.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    }

    public void adicionarCarrinho() {
        Produtos selecionado = Tb_Produtos.getSelectionModel().getSelectedItem();
        selecionado.setQuantidade(1);
        if (selecionado != null) {
            listaCarrinho.add(selecionado);
        }
        System.out.println(selecionado);
    }

    public void removerCarrinho() {
        Produtos selecionado = Tb_Produtos.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            listaCarrinho.remove(selecionado);
        }
    }

}
