package com.controller;

import com.App;
import com.repositories.FuncionarioRepository;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import com.model.Funcionario;

public class Tela_LoginFuncionarioController {

    // Declaração dos campos de texto onde o nome e a senha serão inseridos
    @FXML
    private TextField TF_nome_funcionario;

    @FXML
    private TextField TF_senha_funcionario;

    // Instância do repositório de funcionários para gerenciar a lógica de login
    FuncionarioRepository funcionarioRepository;

    // Método que alterna para a tela principal
    @FXML
    private void switchToTela_Principal() throws IOException {
        // Muda a tela para "Tela_Principal"
        App.setRoot("Tela_Principal");
    }

    // Método que realiza o login do funcionário
    @FXML
    private void realizar_Login() throws IOException, SQLException {

        // Inicializa o repositório de funcionários
        funcionarioRepository = new FuncionarioRepository();

        // Obtém os dados inseridos pelo usuário nos campos de texto
        String id_funcionarioString = TF_nome_funcionario.getText();
        String senha = TF_senha_funcionario.getText();

        // verifica se os campos estao vazio!
        if (id_funcionarioString.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }

        int id_funcionario = -1; // Valor padrão para erro de conversão
        try {
            id_funcionario = Integer.parseInt(id_funcionarioString); // Converte para inteiro
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID do funcionário deve ser um número inteiro válido.");
            return; // Retorna sem tentar fazer login
        }

        // Cria um objeto Funcionario com os dados fornecidos pelo usuário
        Funcionario funcionario = new Funcionario(id_funcionario, senha);

        // Chama o método de login do repositório, passando o objeto Funcionario
        boolean Login_Sucesso = funcionarioRepository.realizarLoginFuncionario(funcionario);

        // Se o login for bem-sucedido, troca para a tela do funcionário
        if (Login_Sucesso) {
            switchToTela_Funcionario(id_funcionario);
        } else {
            // Caso o login falhe, exibe uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Login MAU-sucedido!");
        }
    }

    // Método que alterna para a tela de cadastro de funcionários
    @FXML
    private void switchToTela_Cadastrar_Funcionario() throws IOException {
        // Muda a tela para "Tela_Cadastrar_Funcionario"
        App.setRoot("Tela_Cadastrar_Funcionario");
    }

    // Método que alterna para a tela do funcionário (após login bem-sucedido)
    @FXML
private void switchToTela_Funcionario(int id_funcionario) throws IOException {
    // Carregar o FXML da Tela_Funcionario
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/Tela_Funcionario.fxml"));
    Parent root = loader.load(); // Carregar o arquivo FXML

    // Obter o controlador da Tela_Funcionario após carregar o FXML
    Tela_Funcionario_Controller controller = loader.getController();

    // Passar o ID do funcionário para o controlador
    controller.setIdFuncionario(id_funcionario);

    // Alterar a cena da aplicação para a nova tela
    Scene scene = new Scene(root);
    App.getPrimaryStage().setScene(scene); // Alterna a cena
    App.getPrimaryStage().show(); // Exibe a janela com a nova tela
}

}
