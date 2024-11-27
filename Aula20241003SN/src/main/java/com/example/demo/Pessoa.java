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
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer cd_pessoa;
    public String nu_cpf;
    public String ds_email;
    public String ds_nome;
    public String nu_telefone;
    public String dt_nascimento;
    public String ds_senha;
    public String nivelAcesso;
    public Boolean ativo;

    @OneToMany(mappedBy = "pessoa")
    public List<Aluguel> alugueis;

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
