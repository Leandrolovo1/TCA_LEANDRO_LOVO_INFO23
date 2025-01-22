package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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
    public void initialize() {
        try {
            produtoRepository = new ProdutoRepository();
            configurarColunas_produtos();
            listaProdutos = produtoRepository.preencher_Tabela_Produtos();
            Tb_Produtos.setItems(listaProdutos);
            /////////////////////////////////////////
            funcionarioRepository = new FuncionarioRepository();
            configurarColunas_funcionarios();
            listaFuncionarios = funcionarioRepository.preencher_Tabela_Funcionarios();
            Tb_Funcionarios.setItems(listaFuncionarios);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar os dados do banco: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }

    @FXML
    public void editarFuncionario() throws SQLException {
        Funcionario funcionario = Tb_Funcionarios.getSelectionModel().getSelectedItem();
        if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "Nenhum Funcionario foi selecionado.");
            return;
        }
        String[] opcaoColunas = { "nome", "email", "telefone", "senha" };
        int opcaoEscolhida = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Coluna:",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcaoColunas, opcaoColunas[0]);
        if (opcaoEscolhida == -1)
            return;
        String Coluna = opcaoColunas[opcaoEscolhida];
        int id = funcionario.getId_funcionario();
        String colunaUpdate = JOptionPane.showInputDialog("Digite a alteração");
        Funcionario funcionarioID = new Funcionario(id, colunaUpdate);
        boolean update_sucesso = funcionarioRepository.atualizarFuncionario(funcionarioID, Coluna);

        if (update_sucesso) {
            // Encontra o índice do funcionário na lista usando o método indexOf
            int index = listaFuncionarios.indexOf(funcionario);
            // Verifica se o funcionário foi encontrado na lista (índice diferente de -1
            // significa que o funcionário existe na lista)
            if (index != -1) {
                // Dependendo da coluna que foi escolhida, o código modifica a propriedade
                // correspondente do funcionário
                // Verifica se a coluna escolhida é "nome" e atualiza o nome do funcionário
                if (Coluna.equals("nome")) {
                    funcionario.setNome(colunaUpdate); // Atualiza o nome do funcionário com o novo valor (colunaUpdate)
                }
                // Verifica se a coluna escolhida é "email" e atualiza o email do funcionário
                else if (Coluna.equals("email")) {
                    funcionario.setEmail(colunaUpdate); // Atualiza o email do funcionário com o novo valor
                }
                // Verifica se a coluna escolhida é "telefone" e atualiza o telefone do
                // funcionário
                else if (Coluna.equals("telefone")) {
                    funcionario.setTelefone(colunaUpdate); // Atualiza o telefone do funcionário com o novo valor
                }
                // Atualiza o item na lista (listaFuncionarios) no índice encontrado, com o
                // funcionário modificado
                listaFuncionarios.set(index, funcionario); // Reflete a mudança na tabela (essa mudança será vista na UI
                                                           // automaticamente)
            }
            Tb_Funcionarios.getSelectionModel().clearSelection(); // Limpar seleção
            JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao atualizado funcionário!");
        }

    }

    @FXML
    public void atualizarProduto() throws SQLException {
        Produtos produto = Tb_Produtos.getSelectionModel().getSelectedItem();
        if (produto == null) {
            JOptionPane.showMessageDialog(null, "Nenhum Produto foi selecionado.");
            return;
        }
        int id = produto.getId_produto();
        boolean update_sucesso = false;
        String colunaUpdate;
        String Coluna;
        Float preco = 0.0f;
        int tipo = 1;
        int quantidade = 0;
        int opcaoCategoriaEscolhida = -1;
        int opcaoEscolhida = -1;
        int operacaoEscolhida = -1;
        String[] opcaoColunas = { "nome_produto", "categoria", "marca", "preco_produto", "quantidade" };
        String[] opcaoCategoria = { "Vestuário", "Acessórios", "Skate", "Calçados" };
        String[] tipoOperacao = { "SOMAR", "SUBTRAIR" };
        opcaoEscolhida = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Coluna:",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcaoColunas, opcaoColunas[0]);
        if (opcaoEscolhida == -1)
            return;
        if (opcaoEscolhida == 1) { // categoria
            opcaoCategoriaEscolhida = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Coluna:",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcaoCategoria, opcaoCategoria[0]);
            Coluna = opcaoColunas[opcaoEscolhida];
            colunaUpdate = opcaoCategoria[opcaoCategoriaEscolhida];
            // pegou
            Produtos produtoID = new Produtos(id, colunaUpdate);
            update_sucesso = produtoRepository.atualizarProduto(produtoID, Coluna, tipo);

        } else if (opcaoEscolhida == 3) {// preco
            Coluna = opcaoColunas[opcaoEscolhida];
            colunaUpdate = JOptionPane.showInputDialog("Digite a alteração");
            try {
                preco = Float.parseFloat(colunaUpdate);
                Produtos produtoID = new Produtos(id, colunaUpdate);
                update_sucesso = produtoRepository.atualizarProduto(produtoID, Coluna, tipo);
                // update_sucesso = produtoRepository.atualizarProduto(produtoID, Coluna, tipo);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um número válido.");
            }
        } else if (opcaoEscolhida == 4) {// quantidade
            Coluna = opcaoColunas[opcaoEscolhida];
            colunaUpdate = JOptionPane.showInputDialog("Digite a quantidade");
            try {
                quantidade = Integer.parseInt(colunaUpdate);// Atualiza a quantidade do produto com o novo valor
                operacaoEscolhida = JOptionPane.showOptionDialog(null, "Escolha uma operacao:", "Operacao:",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, tipoOperacao, tipoOperacao[0]);
                if (operacaoEscolhida == 0)
                    tipo = 2; // somar
                else if (operacaoEscolhida == 1)
                    tipo = 3; // diminuir
                Produtos produtoID = new Produtos(id, quantidade);
                update_sucesso = produtoRepository.atualizarProduto(produtoID, Coluna, tipo);
                // update_sucesso = produtoRepository.atualizarProduto(produtoID, Coluna, tipo);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um número válido.");
            }
        } else {
            Coluna = opcaoColunas[opcaoEscolhida];
            colunaUpdate = JOptionPane.showInputDialog("Digite a alteração");
            Produtos produtoID = new Produtos(id, colunaUpdate);
            update_sucesso = produtoRepository.atualizarProduto(produtoID, Coluna, 1);
        }
        if (update_sucesso) {
            // Encontra o índice do produto na lista usando o método indexOf
            int index = listaProdutos.indexOf(produto);
            // Verifica se o produto foi encontrado na lista (índice diferente de -1
            // significa que o produto existe na lista)
            if (index != -1) {
                // Dependendo da coluna que foi escolhida, o código modifica a propriedade
                // correspondente do produto
                // Verifica se a coluna escolhida é "nome_produto" e atualiza o nome do produto
                if (Coluna.equals("nome_produto")) {
                    produto.setNome_produto(colunaUpdate); // Atualiza o nome do produto com o novo valor (colunaUpdate)
                }
                // Verifica se a coluna escolhida é "categoria" e atualiza a categoria do
                // produto
                else if (Coluna.equals("categoria")) {
                    produto.setCategoria_produto(colunaUpdate); // Atualiza o categoria do produto com o novo valor
                }
                // Verifica se a coluna escolhida é "marca" e atualiza a marca do
                // produto
                else if (Coluna.equals("marca")) {
                    produto.setMarca_produto(colunaUpdate); // Atualiza o marca do produto com o novo valor
                } else if (Coluna.equals("preco_produto")) {
                    produto.setPreco_produto(preco); // Atualiza o preco do produto com o novo valor
                } else if (Coluna.equals("quantidade")) {
                    int quantidadeAtualizada = produto.getQuantidade();
                    if (operacaoEscolhida == 1)
                        quantidadeAtualizada = quantidadeAtualizada - quantidade;
                    else
                        quantidadeAtualizada = quantidadeAtualizada + quantidade;
                    ;
                    produto.setQuantidade(quantidadeAtualizada); // Atualiza o preco do produto com o novo valor
                }
                // Atualiza o item na lista (listaProdutos) no índice encontrado, com o
                // produto modificado
                listaProdutos.set(index, produto); // Reflete a mudança na tabela (essa mudança será vista na UI
                                                   // automaticamente)
            }

            Tb_Produtos.getSelectionModel().clearSelection(); // Limpar seleção
            JOptionPane.showMessageDialog(null, "produto atualizado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao atualizado Produto!");
        }

    }

    @FXML
    public void excluirFuncionario() throws IOException, SQLException {
        Funcionario funcionario = Tb_Funcionarios.getSelectionModel().getSelectedItem();
        if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "Nenhum Funcionario foi selecionado.");
            return;
        }

        int mensagemConfirmacao = JOptionPane.showConfirmDialog(null,
                "Tem certeza que deseja excluir esse funcionario ?", "ATENÇÃO!", JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE);
        if (mensagemConfirmacao == JOptionPane.NO_OPTION || mensagemConfirmacao == JOptionPane.CLOSED_OPTION)
            return;

        int id = funcionario.getId_funcionario();
        Funcionario funcionarioID = new Funcionario(id);
        boolean delete_sucesso = funcionarioRepository.deletarFuncionario(funcionarioID);

        if (delete_sucesso) {
            listaFuncionarios.remove(funcionario);
            Tb_Funcionarios.getSelectionModel().clearSelection(); // Limpar seleção
            JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao excluir funcionário!");
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
