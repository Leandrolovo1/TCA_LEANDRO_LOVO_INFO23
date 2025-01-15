package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.App;
import com.model.Estoque;
import com.model.Produtos;
import com.repositories.ProdutoRepository;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Tela_Cadastrar_Produto_Controller {

    @FXML
    private TextField TF_nome_produto;
    @FXML
    private TextField TF_marca_produto;
    @FXML
    private ComboBox<String> COMBOBOX_tipo_produto;
    @FXML
    private TextField TF_quantidade_produto;
    @FXML
    private TextField TF_preco_produto;
    ProdutoRepository produtoRepository;
    @FXML
    public void initialize() {
        // Preenchendo o ComboBox com as opções
        COMBOBOX_tipo_produto.getItems().add("Acessórios");
        COMBOBOX_tipo_produto.getItems().add("Calçados");
        COMBOBOX_tipo_produto.getItems().add("Confecção");
        COMBOBOX_tipo_produto.getItems().add("Skate");
    }

    @FXML
    private void switchToTela_Admin() throws IOException {
        App.setRoot("Tela_Admin");
    }

    @FXML
    private void cadastrarProduto() throws IOException, SQLException 
    {
        produtoRepository = new ProdutoRepository();
        String nome_produto = TF_nome_produto.getText();
        String marca_produto = TF_marca_produto.getText();
        String categoria_produto = COMBOBOX_tipo_produto.getValue();
        String quantidadeString = TF_quantidade_produto.getText();
        String preco_produtoString = TF_preco_produto.getText();
        String tipo_movimentacao = "Entrada";

        float preco_produto = 0.0f;
        try {
            // Converte o valor para float
            preco_produto = Float.parseFloat(preco_produtoString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "deve ser um PREÇO *FLUTUANTE* válido.");
            return;
        }
        int quantidade = -1;
        try {
            quantidade = Integer.parseInt(quantidadeString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "deve ser uma quantidade *INTEIRO* válido.");
            return;
        }

        Produtos produto = new Produtos(marca_produto, nome_produto, categoria_produto, preco_produto);
        Estoque estoque = new Estoque(quantidade, tipo_movimentacao);

        boolean Cadastro_sucesso = produtoRepository.cadastrarProduto(produto, estoque);
        if (Cadastro_sucesso) {
            App.setRoot("Tela_Admin");
            JOptionPane.showMessageDialog(null, "Cadastro bem-sucedido!");
        } else {
            // Caso o login falhe, exibe uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Cadastro MAU-sucedido!");
        }
    }
}
