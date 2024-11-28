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
    public String tp_bicicleta;
    public Integer nu_tranca;
    public String ds_statuss;
    public Double nu_preco;
    public Boolean ativo;
    public String ds_totem; // New field to relate with Totem

    public Integer getCd_bicicleta() {
        return cd_bicicleta;
    }

    public void setCd_bicicleta(Integer cd_bicicleta) {
        this.cd_bicicleta = cd_bicicleta;
    }

    public String getDs_bicicleta() {
        return ds_bicicleta;
    }

    public void setDs_bicicleta(String ds_bicicleta) {
        this.ds_bicicleta = ds_bicicleta;
    }

    public String getTp_bicicleta() {
        return tp_bicicleta;
    }

    public void setTp_bicicleta(String tp_bicicleta) {
        this.tp_bicicleta = tp_bicicleta;
    }

    public Integer getNu_tranca() {
        return nu_tranca;
    }

    public void setNu_tranca(Integer nu_tranca) {
        this.nu_tranca = nu_tranca;
    }

    public String getDs_statuss() {
        return ds_statuss;
    }

    public void setDs_statuss(String ds_statuss) {
        this.ds_statuss = ds_statuss;
    }

    public Double getNu_preco() {
        return nu_preco;
    }

    public void setNu_preco(Double nu_preco) {
        this.nu_preco = nu_preco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getDs_totem() {
        return ds_totem;
    }

    public void setDs_totem(String ds_totem) {
        this.ds_totem = ds_totem;
    }

}
