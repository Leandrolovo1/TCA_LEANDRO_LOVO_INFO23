package com.db;

import java.sql.Connection; // Importa a interface Connection, que representa uma conexão com o banco de dados
import java.sql.DriverManager; // Importa a classe DriverManager, que gerencia as conexões com o banco de dados

import java.sql.SQLException;

public class FabricaConexao {
    //private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://wagnerweinert.com.br:3306/info23_LEANDRO";
    private static final String USER = "info23_LEANDRO";
    private static final String PASSWORD = "info23_LEANDRO";

    public static Connection faz_Conexao() throws SQLException {
        try {

            //Class.forName(DRIVER);
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO: handle exception
            throw new RuntimeException("Erro na conexão!", e);
        }
    }

    /*public static void closeConnection(Connection con) { /* retirei isso pois descobri que o trycatchresouces encerra a conexão ao banco de dados
                                                            *
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, e);

        }
    } */

}
