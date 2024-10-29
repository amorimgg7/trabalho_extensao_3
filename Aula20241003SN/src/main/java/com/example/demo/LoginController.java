package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	PessoaDAO dao;
	
	@GetMapping
	public List<Pessoa> obterTodos(){
		return dao.findAll();
	}
	
	@PostMapping
	public void login(@RequestBody Pessoa p) {
		
	}
	
	@GetMapping("{nome}/{senha}")
	public Pessoa obter(@PathVariable("nome") String nome, @PathVariable("senha") String senha, HttpServletResponse resp) {
	    Optional<Pessoa> p = dao.findByNomeAndSenha(nome, senha);
	    if (p.isPresent()) {
	        return p.get();
	    } else {
	        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        return null;
	    }
	}
	


}
