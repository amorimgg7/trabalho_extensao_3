package com.example.demo;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public class Aluguel {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer codigoAluguel;
	
    public LocalDateTime data_aluguel;
    public LocalDateTime data_devolucao;
    public String local_retirada;
    public String local_devolucao;
    public Boolean devolvido;

    public LocalDateTime getData_aluguel() {
        return data_aluguel;
    }

    public void setData_aluguel(LocalDateTime data_aluguel) {
        this.data_aluguel = data_aluguel;
    }

    public LocalDateTime getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(LocalDateTime data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public String getLocal_retirada() {
        return local_retirada;
    }

    public void setLocal_retirada(String local_retirada) {
        this.local_retirada = local_retirada;
    }

    public String getLocal_devolucao() {
        return local_devolucao;
    }

    public void setLocal_devolucao(String local_devolucao) {
        this.local_devolucao = local_devolucao;
    }

    public Boolean getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(Boolean devolvido) {
        this.devolvido = devolvido;
    }
}
