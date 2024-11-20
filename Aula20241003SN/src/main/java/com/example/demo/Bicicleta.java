package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Bicicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer cd_bicicleta;
    public String ds_bicicleta;
    public String cd_cliente;
    public Integer nu_tranca;
    public String ds_status;
    public Boolean ativo;

    public Integer getBicicleta() {
        return cd_bicicleta;
    }

    public void setBicicleta(Integer cd_bicicleta) {
        this.cd_bicicleta = cd_bicicleta;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
