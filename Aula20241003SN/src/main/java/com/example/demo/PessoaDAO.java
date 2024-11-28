package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PessoaDAO extends JpaRepository<Pessoa, Integer> {

    @Query("select p from Pessoa p where p.ds_nome = :nome and p.ds_senha= :senha")
    Optional<Pessoa> findByNomeAndSenha(@Param("nome") String nome, @Param("senha") String senha);
}
