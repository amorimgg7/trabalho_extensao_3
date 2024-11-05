package com.example.demo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaDAO extends JpaRepository<Pessoa, Integer>{
/*
 * Aqui deve ter duas funcoes.
 * Login
 * Cadastro
 * AtualizaCadastro
 * */
	Optional<Pessoa> findByNomeAndSenha(String nome, String senha);
	Optional<Pessoa> findByNomeAndSenha2(String nome, String senha);

}
