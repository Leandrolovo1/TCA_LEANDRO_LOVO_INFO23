package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.model.Produtos;
import com.model.Venda;
import com.repositories.ProdutoRepository;
import javafx.application.Platform;
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
        
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
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
        });
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
    
    public void irParaPagamento() throws IOException, SQLException {
        float dinheiro = -1;
        float total = calcularTotal();
        String pagamento = "-";
        int operacaoEscolhida = -1;
        String[] tipoOperacao = { "Dinheiro", "PIX", "FIADO" };
        
        if (listaCarrinho.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Carrinho vazio.", "ALERTA", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        operacaoEscolhida = JOptionPane.showOptionDialog(null, total + "R$"+"\nEscolha uma operacao:", "Operacao:",
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, tipoOperacao, tipoOperacao[0]);
        if (operacaoEscolhida == JOptionPane.CLOSED_OPTION) {
            return;
            
        }
        
        switch (operacaoEscolhida) {
            case 0:
            boolean valorValido = false;
            while (!valorValido) {
                pagamento = JOptionPane.showInputDialog(total +"R$" +"\nDigite o valor pago:");
                System.out.println(pagamento);
                if (pagamento == null || pagamento.isEmpty() || pagamento.isBlank() || pagamento.equals("-") || pagamento.equals("+") || pagamento == "") {
                    return;
                    
                }
                try {
                    dinheiro = Float.parseFloat(pagamento);
                    if (dinheiro < 0) {
                        JOptionPane.showMessageDialog(null, "Valor inválido. Por favor, insira um valor positivo.");
                    } else if (dinheiro < total) {
                        JOptionPane.showMessageDialog(null, "Valor insuficiente. Por favor, insira um valor maior ou igual ao total.");
                    } else {
                        valorValido = true;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Valor inválido. Por favor, insira um número.");
                }
            }
            
                float troco = 0;    
                JOptionPane.showMessageDialog(null, "Troco: R$" + (troco = dinheiro - total));
                JOptionPane.showMessageDialog(null, "Compra efetuada com sucesso.", "Pagamento Concluído",JOptionPane.INFORMATION_MESSAGE);
                Venda venda = new Venda(idFuncionario, total, dinheiro, troco);
                int idVenda = produtoRepository.registrarVenda(venda);
                // Agora, registramos os produtos dessa venda
                for (Produtos produto : listaCarrinho) {
                    produtoRepository.registrarProdutoVenda(idVenda, produto); // Método que você deve criar para registrar os produtos na venda
                    for (Produtos p : listaProdutos) {
                        if (p.getId_produto() == produto.getId_produto()) {
                            p.setQuantidade(p.getQuantidade() - produto.getQuantidade());
                            break;
                        }
                    }
                }
                listaCarrinho.clear();
                Tb_Carrinho.refresh();
                Tb_Produtos.refresh();
                //Tb_Produtos.setItems(listaProdutos);    
                /*  // TODO registrar banco de dados
                */
                break;
            case 1:
                int confirmacao = JOptionPane.showConfirmDialog(null,
                "Pagamento via PIX\n chave: 41988605077\n LEANDRO LOVO MACEDO\n BANCO: INTER", "ATENÇÃO!", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

                if (confirmacao != JOptionPane.NO_OPTION && confirmacao != JOptionPane.CLOSED_OPTION && confirmacao != JOptionPane.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(null, "Compra efetuada com sucesso.", "Pagamento Concluído",JOptionPane.INFORMATION_MESSAGE);
                    venda = new Venda(idFuncionario, total, total, 0);
                    idVenda = produtoRepository.registrarVenda(venda);
                    // Agora, registramos os produtos dessa venda
                    for (Produtos produto : listaCarrinho) {
                        produtoRepository.registrarProdutoVenda(idVenda, produto); // Método que você deve criar para registrar os produtos na venda
                        for (Produtos p : listaProdutos) {
                            if (p.getId_produto() == produto.getId_produto()) {
                                p.setQuantidade(p.getQuantidade() - produto.getQuantidade());
                                break;
                            }
                        }
                    }
                    listaCarrinho.clear();
                    Tb_Carrinho.refresh();
                    Tb_Produtos.refresh();
                    //Tb_Produtos.setItems(listaProdutos);

                }else{
                    JOptionPane.showMessageDialog(null, "Pagamento cancelado.");
                    return;
                    
                }
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "FIADO SÓ AMANHÃ.");
                break;
        }
    }

    @FXML
    private void switchToTela_Principal() throws IOException {
        // Cria um novo FXMLLoader para carregar o arquivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/Tela_Principal.fxml"));
        Parent root = loader.load();

        // Obtenha o Stage atual
        Stage stage = (Stage) Tb_Produtos.getScene().getWindow();

        // Configura a nova cena no Stage atual e exibe
        stage.setScene(new Scene(root));
        stage.show();

    }

    private float calcularTotal() {
        float total = 0;
        for (Produtos produto : listaCarrinho) {
            total += produto.getPreco_produto() * produto.getQuantidade();
        }
        return total;
    }


}
