package com.example.demo;

import java.time.LocalDateTime;

public class Aluguel {

	public Integer cd_aluguel;
    public String cd_pessoa;
    public String cd_bicicleta;
    public String cd_totem_retirada;
    public String dt_retirada;
    public String cd_totem_devolucao;
    public String dt_devolucao;
    public String nu_valor_aluguel;
    public Boolean pago;
    
    
    public void setBicicleta(String cd_bicicleta) {
        this.cd_bicicleta = cd_bicicleta;
    } 
    public void setPessoa(String cd_pessoa) {
        this.cd_pessoa = cd_pessoa;
    } 
    public void setTotemRetirada(String cd_totem_retirada) {
        this.cd_totem_retirada = cd_totem_retirada;
    } 
    public void setDataRetirada(String dt_retirada) {
        this.dt_retirada = dt_retirada;
    } 
    public void setValorAluguel(String nu_valor_aluguel) {
        this.nu_valor_aluguel = nu_valor_aluguel;
    } 
    public void setPago(Boolean pago) {
        this.pago = pago;
    } 
    
	
	
	
}
