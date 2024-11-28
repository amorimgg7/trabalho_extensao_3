package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    public Integer cd_aluguel;
    public String cd_pessoa;
    public String cd_bicicleta;
    public String cd_totem_retirada;
    public String dt_retirada;
    public String cd_totem_devolucao;
    public String dt_devolucao;
    public String nu_valor_aluguel;
    public Boolean pago;

    @ManyToOne
    @JoinColumn(name = "cd_pessoa", insertable = false, updatable = false)
    public Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "cd_bicicleta", insertable = false, updatable = false)
    public Bicicleta bicicleta;

    @ManyToOne
    @JoinColumn(name = "cd_totem_retirada", insertable = false, updatable = false)
    public Totem totemRetirada;

    @ManyToOne
    @JoinColumn(name = "cd_totem_devolucao", insertable = false, updatable = false)
    public Totem totemDevolucao;

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

}
