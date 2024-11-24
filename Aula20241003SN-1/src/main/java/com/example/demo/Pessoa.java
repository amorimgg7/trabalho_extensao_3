package com.example.demo;

import java.time.LocalDate;

public class Pessoa {

	
	public Integer cd_pessoa;
	public String ds_nome;
    public Integer nu_cpf;
    public String ds_email;
    public Integer nu_telefone;
    public LocalDate dt_nascimento;
    public String ds_senha;

    public Integer nivelAcesso;
    public Boolean ativo;


    public Integer getCodigo() {
        return cd_pessoa;
    }
    
    public String getNome() {
        return ds_nome;
    }

    public void setNome(String ds_nome) {
        this.ds_nome = ds_nome;
    }  
    
    public void setCPF(Integer nu_cpf) {
    	this.nu_cpf = nu_cpf;
    }
    public void setDtNascimento(LocalDate dt_nascimento) {
    	this.dt_nascimento = dt_nascimento;
    }
    
    public void setEmail(String ds_email) {
    	this.ds_email = ds_email;
    }
    public void setTelefone(Integer nu_telefone) {
    	this.nu_telefone = nu_telefone;
    }
    public void setSenha(String ds_senha) {
    	this.ds_senha = ds_senha;
    }

	
	
	
    /*
    public Pessoa(ds_nome, String nu_cpf, LocalDate dt_nascimento, String ds_email, String ds_senha,
			String nu_telefone, String ds_emailString) {
		// TODO Auto-generated constructor stub
	}*/
    

}