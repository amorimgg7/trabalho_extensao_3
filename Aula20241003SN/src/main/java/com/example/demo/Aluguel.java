package com.example.demo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
    public Integer cd_aluguel;
    public Integer cd_pessoa;
    public Integer cd_bicicleta;
    public Integer cd_totem_retirada;
    public LocalDateTime dt_retirada;
    public Integer cd_totem_devolucao;
    public LocalDateTime dt_devolucao;
    public Double nu_valor_aluguel;
    public Boolean pago;
    
    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

}
