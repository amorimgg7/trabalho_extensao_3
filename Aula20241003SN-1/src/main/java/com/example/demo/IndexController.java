package com.example.demo;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import retrofit2.Response;

@Controller
public class IndexController {

    private final PessoaNet pnet = new LojaCliente().getPessoaNet();

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/index2")
    public String index2(Model model) {
        try {
            model.addAttribute("pessoas", pnet.obterTodos().execute().body());
        } catch (IOException e) {
            System.out.println("Erro");
        }
        return "index2";
    }

    @GetMapping("/index2/{codigo}")
    public String index2(@PathVariable("codigo") Integer codigo, Model model) {
        try {
            model.addAttribute("pessoas", pnet.obter(codigo).execute().body());
        } catch (IOException e) {
        }
        return "index2";
    }

    @GetMapping("{nome}/{senha}")
    public String index2(@PathVariable("nome") String nome, @PathVariable("senha") String senha, Model model, HttpSession session) {
        try {
            Pessoa pessoa = pnet.login(nome, senha).execute().body();
            if (pessoa != null) {
                model.addAttribute("pessoas", pessoa);
                session.setAttribute("codigo", pessoa.getCodigo());
                session.setAttribute("nome", pessoa.getNome());
                if (!senha.equals(pessoa.getSenha())) {
                    model.addAttribute("pessoas", null);
                    session.removeAttribute("codigo");
                    session.removeAttribute("nome");
                }
                if (pessoa.getNivelAcesso() == 5) {
                    return "/gestor";
                }
            } else {
                model.addAttribute("pessoas", null);
                session.removeAttribute("codigo");
                session.removeAttribute("nome");
            }
        } catch (IOException e) {
        }
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        try {
            model.addAttribute("pessoas", pnet.obterTodos().execute().body());
        } catch (IOException e) {
            System.out.println("Erro");
        }
        return "dashboard";
    }

    @GetMapping("/dashboard/{nome}/{senha}")
    public String dashboard(@PathVariable("nome") String nome, @PathVariable("senha") String senha, Model model) {
        try {
            model.addAttribute("pessoas", pnet.login(nome, senha).execute().body());
        } catch (IOException e) {
        }
        return "dashboard";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@RequestBody Pessoa pessoa, Model model) {
        try {
            // Envia a pessoa para inclusão via PessoaNet
            Response<Void> response = pnet.incluir(pessoa).execute();
            if (response.isSuccessful()) {
                return "redirect:/";
            } else {
                model.addAttribute("erro", "Erro ao cadastrar pessoa: " + response.message());
                return "erro";
            }
        } catch (IOException e) {
            model.addAttribute("erro", "Erro ao cadastrar pessoa");
            return "erro";
        }
    }

    @RequestMapping("/erro")
    public String handleError() {
        return "erro";
    }
}