package com.example.demo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/pessoa")
@CrossOrigin(origins = "*")
public class PessoaController {

    @Autowired
    PessoaDAO dao;

    @PostMapping
    public void incluir(@RequestBody Pessoa p) {
        dao.save(p);
    }

    @GetMapping
    public List<Pessoa> obterTodos() {
        return dao.findAll();
    }

    @GetMapping("{cd_pessoa}")
    public Pessoa obter(@PathVariable("cd_pessoa") Integer cd_pessoa, HttpServletResponse resp) {
        Optional<Pessoa> p = dao.findById(cd_pessoa);
        if (p.isPresent()) {
            return p.get();
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping("{ds_nome}/{ds_senha}")
    public Pessoa login(@PathVariable("ds_nome") String ds_nome, @PathVariable("ds_senha") String ds_senha, HttpServletResponse resp) {
        Optional<Pessoa> p = dao.findByNomeAndSenha(ds_nome, ds_senha);
        if (p.isPresent()) {
            return p.get();
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @DeleteMapping("{cd_pessoa}")
    public void excluir(@PathVariable Integer cd_pessoa, HttpServletResponse resp) {
        Optional<Pessoa> p = dao.findById(cd_pessoa);
        if (p.isPresent()) {
            dao.delete(p.get());
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PutMapping("{cd_pessoa}")
    public void alterar(@PathVariable Integer cd_pessoa, @RequestBody Pessoa p,
            HttpServletResponse resp) {
        if (Objects.equals(p.cd_pessoa, cd_pessoa)) {
            dao.save(p);
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
