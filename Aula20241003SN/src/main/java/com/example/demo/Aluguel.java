package com.example.demo;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Aluguel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	public String Usuario;
	public Integer bicicleta_id;
	public LocalDateTime data_aluguel;
	public LocalDateTime data_devolucao;
	public Boolean devolvido; 
}
