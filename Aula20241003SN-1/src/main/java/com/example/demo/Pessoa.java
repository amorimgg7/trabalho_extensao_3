package com.example.demo;

public class Pessoa {

    public Integer codigo;
    public String nome;
    public Integer idade;
    public String senha;
    public Integer nivelAcesso;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer setNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(Integer nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public Pessoa() {
    }

    public Pessoa(Integer codigo, String nome, Integer idade, String senha, Integer nivelAcesso) {
        this.codigo = codigo;
        this.nome = nome;
        this.idade = idade;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }
}
