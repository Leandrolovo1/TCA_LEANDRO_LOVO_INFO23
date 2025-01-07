package com.model;

import java.sql.Date;

public class Venda {
    int id_venda;
    Date data_venda;
    float total;

    public Venda(int id_venda, Date data_venda, float total) {
        this.id_venda = id_venda;
        this.data_venda = data_venda;
        this.total = total;
    }
    
    public int getId_venda() {
        return id_venda;
    }
    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }
    public Date getData_venda() {
        return data_venda;
    }
    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }

}
