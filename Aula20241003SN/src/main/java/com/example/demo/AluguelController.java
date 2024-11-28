package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/aluguel")
@CrossOrigin(origins = "*")
public class AluguelController {

    @Autowired
    AluguelDAO adao;

    @PostMapping
    public void incluir(@RequestBody Aluguel a) {
        adao.save(a);
    }

    @GetMapping
    public List<Aluguel> obterTodos() {
        return adao.findAll();
    }

    @GetMapping("{cd_pessoa}/{pago}")
    public Aluguel obter(@PathVariable("cd_pessoa") Integer cd_pessoa, @PathVariable("pago") Boolean pago, HttpServletResponse resp) {
        System.out.println(cd_pessoa + " :: " + pago);
        Optional<Aluguel> a = adao.findByCodigoAndPago(cd_pessoa, pago);
        if (a.isPresent()) {
            return a.get();
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @PutMapping
    public void atualizar(@RequestBody Aluguel a) {
        adao.save(a);
    }
}
