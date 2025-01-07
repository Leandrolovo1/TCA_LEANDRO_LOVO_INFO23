package com.model.interfaces;

import java.sql.SQLException;

import com.model.Administrador;

public interface AdministradorDAO 
{
    public boolean realizar_Login_Administrador(Administrador administrador) throws SQLException; 
} 