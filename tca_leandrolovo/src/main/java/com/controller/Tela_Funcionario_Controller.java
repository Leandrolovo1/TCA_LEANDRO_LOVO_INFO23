package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.App;
import com.model.Produtos;
import com.repositories.ProdutoRepository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableColumn<Produtos, String> CL_codigo_Carrinho;
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
        // System.out.println("ID do funcionário recebido: " + idFuncionario);
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

    
    private void configurarColunas_produtos() {
        CL_ID_produto.setCellValueFactory(new PropertyValueFactory<>("id_produto"));
        CL_nome_produto.setCellValueFactory(new PropertyValueFactory<>("nome_produto"));
        CL_marca_produto.setCellValueFactory(new PropertyValueFactory<>("marca_produto"));
        CL_categoria_produto.setCellValueFactory(new PropertyValueFactory<>("categoria_produto"));
        CL_preco_produto.setCellValueFactory(new PropertyValueFactory<>("Preco_produto"));
        CL_quantidade_produto.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    }
    
    private void configurarColunas_Carrinho() {
        CL_codigo_Carrinho.setCellValueFactory(new PropertyValueFactory<>("id_produto"));
        CL_nome_produto_Carrinho.setCellValueFactory(new PropertyValueFactory<>("nome_produto"));
        CL_preco_produto_carrinho.setCellValueFactory(new PropertyValueFactory<>("Preco_produto"));
        CL_quantidade_produto_carrinho.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    }
    
    public void adicionarCarrinho() {
        // Obtém o produto selecionado na tabela de produtos
        Produtos selecionado = Tb_Produtos.getSelectionModel().getSelectedItem();
        
        // Verifica se algum produto foi selecionado
        if (selecionado != null) {
            boolean produtoExistente = false;

            // Percorre a lista de produtos no carrinho
            for (Produtos produto : listaCarrinho) {
                // Verifica se o produto já está no carrinho comparando os IDs
                if (produto.getId_produto() == selecionado.getId_produto()) {
                    // Verifica se a quantidade no carrinho mais 1 é maior que a quantidade em
                    // estoque
                    if (produto.getQuantidade() + 1 > selecionado.getQuantidade()) {
                        JOptionPane.showMessageDialog(null,
                        "Quantidade no carrinho não pode ser maior que a quantidade em estoque.");
                        return;
                    }
                    // Incrementa a quantidade do produto no carrinho
                    produto.setQuantidade(produto.getQuantidade() + 1);
                    produtoExistente = true;
                    break;
                }
            }
            
            // Se o produto não estiver no carrinho, adiciona-o com quantidade 1
            if (!produtoExistente) {
                // Verifica se a quantidade em estoque é pelo menos 1
                if (selecionado.getQuantidade() < 1) {
                    JOptionPane.showMessageDialog(null, "Quantidade em estoque insuficiente.");
                    return;
                }
                Produtos novoProduto = new Produtos(selecionado.getId_produto(), selecionado.getNome_produto(),
                selecionado.getCategoria_produto(), selecionado.getMarca_produto(),
                selecionado.getPreco_produto(), 1);
                listaCarrinho.add(novoProduto);
            }
            
            // Atualiza a exibição da tabela
            Tb_Carrinho.refresh();
        }
        Tb_Produtos.getSelectionModel().clearSelection();
    }
    
    public void removerCarrinho() {
        Produtos selecionado = Tb_Produtos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            selecionado = Tb_Carrinho.getSelectionModel().getSelectedItem();

        }
        listaCarrinho.remove(selecionado);
        Tb_Produtos.getSelectionModel().clearSelection();
        Tb_Carrinho.refresh();
    }
    
    @FXML
    private void switchToTela_Principal() throws IOException {
        try {
            // Carrega o novo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/Tela_Principal.fxml"));
        Parent root = fxmlLoader.load();
        // Obtém o Stage atual
        Stage stage = (Stage) Tb_Produtos.getScene().getWindow();
        // Cria uma nova cena com o novo FXML
        Scene scene = new Scene(root);
        // Define a nova cena no Stage atual e mostra
        stage.setScene(scene);
        stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao trocar de tela: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
