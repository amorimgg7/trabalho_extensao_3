package com.example.demo;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ALUGUEL")
public class Aluguel extends Pessoa{

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
