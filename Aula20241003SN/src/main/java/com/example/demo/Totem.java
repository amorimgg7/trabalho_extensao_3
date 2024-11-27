package com.example.demo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Totem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer cd_totem;
    public String ds_totem;
    public String ds_endereco;
    public String ds_statuss;
    public Boolean ativo;

    @OneToMany(mappedBy = "totemRetirada")
    public List<Aluguel> alugueisRetirada;

    @OneToMany(mappedBy = "totemDevolucao")
    public List<Aluguel> alugueisDevolucao;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
