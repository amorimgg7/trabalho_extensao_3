package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AluguelDAO extends JpaRepository<Aluguel, Integer> {

    /*
 * Aqui deve ter as funcoes.
 * ConsultarStatus
 * Alugar
 * Liberar
 * Desativar
 * */

	@Query("select a from Aluguel a where a.cd_pessoa = :codigo and a.pago= :pago")
    Optional<Aluguel> findByCodigoAndPago(@Param("codigo") Integer codigo, @Param("pago") Boolean pago);

}
