package com.example.demo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaDAO extends JpaRepository<Pessoa, Integer>{

	Optional<Pessoa> findByNomeAndSenha(String nome, String senha);
    boolean existsByNome(String nome);
}
