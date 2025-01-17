package com.db;

import java.sql.Connection; // Importa a interface Connection, que representa uma conexão com o banco de dados
import java.sql.DriverManager; // Importa a classe DriverManager, que gerencia as conexões com o banco de dados

import java.sql.SQLException;

public class FabricaConexao 
{
    //private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://wagnerweinert.com.br:3306/info23_LEANDRO";
    private static final String USER = "info23_LEANDRO";
    private static final String PASSWORD = "info23_LEANDRO";

    public static Connection faz_Conexao() throws SQLException 
    {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException | SQLException e) 
        {
            throw new RuntimeException("Erro na conexão!", e);
        }
    }
}
