package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BicicletaDAO extends JpaRepository<Bicicleta, Integer> {

    @Query("select b from Bicicleta b where b.cd_bicicleta = :codigo")
    Optional<Bicicleta> findByCodigo(@Param("codigo") Integer codigo);
}
