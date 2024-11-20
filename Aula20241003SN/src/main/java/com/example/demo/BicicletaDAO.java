package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BicicletaDAO extends JpaRepository<Bicicleta, Integer> {

    /*
 * Aqui deve ter as funcoes.
 * ConsultarStatus
 * Alugar
 * Liberar
 * Desativar
 * */
	@Query("select b.cd_bicicleta, b.ds_status from Bicicleta b where b.cd_bicicleta = :codigo")
    Optional<Bicicleta> findByCodigo(@Param("codigo") Integer codigo);//ConsultarStatus

	@Query("select b from Bicicleta b where b.cd_bicicleta = :codigo and b.ativo = :ativo")
	Optional<Bicicleta> findByCodigoAndAtivo(@Param("codigo") Integer codigo, @Param("ativo") Boolean ativo);//ConsultarAtivo
}
