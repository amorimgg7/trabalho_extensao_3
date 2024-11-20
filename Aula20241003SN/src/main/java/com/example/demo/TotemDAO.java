package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TotemDAO extends JpaRepository<Totem, Integer> {

    /*
 * Aqui deve ter as funcoes.
 * ConsultarStatus
 * Alugar
 * Liberar
 * Desativar
 * */
	@Query("select t from Totem t where t.cd_totem = :codigo")
    Optional<Totem> findByCodigo(@Param("codigo") Integer codigo);//ConsultarStatus

}
