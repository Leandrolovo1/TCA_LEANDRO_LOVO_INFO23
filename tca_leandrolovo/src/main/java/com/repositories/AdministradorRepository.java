package com.repositories;

//import com.model.interfaces.AdministradorDAO;

import java.sql.SQLException;
import com.model.Administrador;
import com.DAO.AdministradorDAOImpl;

public class AdministradorRepository {

    AdministradorDAOImpl AdministradorDAOimpl;

    public AdministradorRepository()throws SQLException
    {
        this.AdministradorDAOimpl = new AdministradorDAOImpl();
    }

    public boolean realizarLoginAdministrador(Administrador administrador) throws SQLException
    {
        return AdministradorDAOimpl.realizar_Login_Administrador(administrador);
    }

}
