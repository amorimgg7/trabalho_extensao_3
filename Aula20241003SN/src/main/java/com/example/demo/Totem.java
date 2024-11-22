package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Totem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer cd_totem;
    public String ds_totem;
    public String ds_endereco;
    public String local_1;
    public String local_2;
    public String local_3;
    public String local_4;
    public String local_5;
    public String ds_statuss;
    public Boolean ativo;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }


}
