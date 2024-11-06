package com.example.demo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BICICLETA")
public class Bicicleta extends Pessoa{

    public String codigo_tranca;
    public String local_tranca;
    public Boolean disponivel;

    public String getCodigo_tranca() {
        return codigo_tranca;
    }

    public void setCodigo_tranca(String codigo_tranca) {
        this.codigo_tranca = codigo_tranca;
    }

    public String getLocal_tranca() {
        return local_tranca;
    }

    public void setLocal_tranca(String local_tranca) {
        this.local_tranca = local_tranca;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}
