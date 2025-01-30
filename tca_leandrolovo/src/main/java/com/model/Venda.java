package com.model;

import java.sql.Date;

public class Venda {
    int id_venda;
    int fk_id_funcionario;
    
    public Venda(int fk_id_funcionario, float total_venda, float valor_pago, float troco) {
        this.fk_id_funcionario = fk_id_funcionario;
        this.total_venda = total_venda;
        this.valor_pago = valor_pago;
        this.troco = troco;
    }

    Date data_venda;
    float total_venda;
    float valor_pago;
    float troco;

    
    public Venda(int fk_id_funcionario, Date data_venda, float total_venda, float valor_pago, float troco) {
        this.fk_id_funcionario = fk_id_funcionario;
        this.data_venda = data_venda;
        this.total_venda = total_venda;
        this.valor_pago = valor_pago;
        this.troco = troco;
    }

    public Venda(int id_venda, Date data_venda, float total_venda) {
        this.id_venda = id_venda;
        this.data_venda = data_venda;
        this.total_venda = total_venda;
    }
    
    public Venda(int id_venda, int fk_id_funcionario, Date data_venda, float total_venda, float valor_pago,
    float troco) {
        this.id_venda = id_venda;
        this.fk_id_funcionario = fk_id_funcionario;
        this.data_venda = data_venda;
        this.total_venda = total_venda;
        this.valor_pago = valor_pago;
        this.troco = troco;
    }
    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getFk_id_funcionario() {
        return fk_id_funcionario;
    }

    public void setFk_id_funcionario(int fk_id_funcionario) {
        this.fk_id_funcionario = fk_id_funcionario;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public float getTotal_venda() {
        return total_venda;
    }

    public void setTotal_venda(float total_venda) {
        this.total_venda = total_venda;
    }

    public float getValor_pago() {
        return valor_pago;
    }

    public void setValor_pago(float valor_pago) {
        this.valor_pago = valor_pago;
    }

    public float getTroco() {
        return troco;
    }

    public void setTroco(float troco) {
        this.troco = troco;
    }

    
}
