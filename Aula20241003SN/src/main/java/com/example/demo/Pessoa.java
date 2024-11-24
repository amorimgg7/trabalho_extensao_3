package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer cd_pessoa;
    public Integer nu_cpf;
    public String ds_email;

    public String ds_nome;
    public Integer nu_telefone;
    public LocalDate dt_nascimento;
    public String ds_senha;

    public Integer nivelAcesso;
    public Boolean ativo;

    public Integer getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(Integer nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}