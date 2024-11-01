package com.example.demo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bicicleta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	public String codigo_tranca;
	public Boolean disponivel;
}
