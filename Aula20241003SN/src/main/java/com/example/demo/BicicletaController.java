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
@RequestMapping("/bicicleta")
@CrossOrigin(origins = "*")
public class BicicletaController {

    @Autowired
    BicicletaDAO bdao;

    @PostMapping
    public void incluir(@RequestBody Bicicleta b) {
        bdao.save(b);
    }

    @GetMapping
    public List<Bicicleta> obterTodos() {
        return bdao.findAll();
    }

    @GetMapping("{cd_bicicleta}")
    public Bicicleta Consultar(@PathVariable("cd_bicicleta") Integer cd_bicicleta, HttpServletResponse resp) {
        Optional<Bicicleta> b = bdao.findByCodigo(cd_bicicleta);
        if (b.isPresent()) {
            return b.get(); 
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }
}
