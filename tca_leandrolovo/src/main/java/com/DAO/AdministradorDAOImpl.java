package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.db.FabricaConexao;
import com.model.Administrador;
import com.model.interfaces.AdministradorDAO;


public class AdministradorDAOImpl implements AdministradorDAO{
    public boolean realizar_Login_Administrador(Administrador administrador){
        String sql = "select * from Administrador where nome = ? and senha = ?";

        try(Connection con = FabricaConexao.faz_Conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, administrador.getNome());
            stmt.setString(2, administrador.getSenha());

            try (ResultSet rs = stmt.executeQuery();){

                if (rs.next()) {
                    return true;
                }else{                  
                    return false;
                }  
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }
    
}
