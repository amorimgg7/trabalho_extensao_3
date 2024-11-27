package com.example.demo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    private final PessoaNet pnet = new LojaCliente().getPessoaNet();
    private final BicicletaNet bnet = new LojaCliente().getBicicletaNet();
    private final TotemNet tnet = new LojaCliente().getTotemNet();
    private final AluguelNet anet = new LojaCliente().getAluguelNet();

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/index2")
    public String index2(Model model) {
        try {
            model.addAttribute("pessoas", pnet.obterTodos().execute().body());
        } catch (IOException e) {
            System.out.println("Erro Pessoa");
            System.err.println("Erro ao carregar dados de pessoas: " + e.getMessage());
        }

        try {
            model.addAttribute("bicicletas", bnet.obterTodos().execute().body());
        } catch (IOException e) {
            System.out.println("Erro Bicicleta");
            System.err.println("Erro ao carregar dados de bicicletas: " + e.getMessage());
        }

        try {
            model.addAttribute("totems", tnet.obterTodos().execute().body());
        } catch (IOException e) {
            System.out.println("Erro Totem");
            System.err.println("Erro ao carregar dados de totems: " + e.getMessage());
        }
        try {
            model.addAttribute("aluguels", anet.obterTodos().execute().body());
        } catch (IOException e) {
            System.out.println("Erro Aluguel");
            System.err.println("Erro ao carregar dados de aluguels: " + e.getMessage());
        }

        return "index2";
    }

    @GetMapping("/index2/{cd_pessoa}")
    @SuppressWarnings("CallToPrintStackTrace")
    public String index2(@PathVariable("cd_pessoa") Integer cd_pessoa, Model model) {
        try {
            model.addAttribute("pessoas", pnet.obter(cd_pessoa).execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index2";
    }

    @GetMapping("{ds_nome}/{ds_senha}")
    @SuppressWarnings("CallToPrintStackTrace")
    public String index2(@PathVariable("ds_nome") String ds_nome, @PathVariable("ds_senha") String ds_senha, Model model, jakarta.servlet.http.HttpSession session) {
        try {
            Pessoa pessoa = pnet.login(ds_nome, ds_senha).execute().body();

            if (pessoa == null) {
                session.setAttribute("codigoErro", 1);
                return "/index";
            } else {
                if (pessoa.nivelAcesso == 1) {
                    model.addAttribute("pessoas", pessoa);
                    session.setAttribute("codigoPessoa", pessoa.cd_pessoa);
                    session.setAttribute("nome", pessoa.ds_nome);

                    Aluguel aluguel = anet.obter(pessoa.cd_pessoa, false).execute().body();

                    if (aluguel == null) {
                        session.setAttribute("codigoBicicleta", "0");
                        session.setAttribute("codigoAluguel", "0");
                    } else {
                        Bicicleta bicicleta = bnet.obter(aluguel.cd_bicicleta).execute().body();
                        session.setAttribute("codigoBicicleta", aluguel.cd_bicicleta);

                        session.setAttribute("codigoAluguel", aluguel.cd_aluguel);

                        if (bicicleta != null) {
                            session.setAttribute("descricaoBicicleta", bicicleta.ds_bicicleta);
                        } else {
                            session.setAttribute("descricaoBicicleta", "Desconhecida");
                        }
                    }

                    session.setAttribute("codigoCarteira", "1");
                }
            }

            if (pessoa.nivelAcesso == 5) {
                return "/gestor";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("ds_nome") String ds_nome, @RequestParam("ds_senha") String ds_senha, Model model, jakarta.servlet.http.HttpSession session) {
        try {
            Pessoa pessoa = pnet.login(ds_nome, ds_senha).execute().body();

            if (pessoa == null) {
                session.setAttribute("codigoErro", 1);
                return "redirect:/erro/1";
            } else {
                if (pessoa.nivelAcesso == 1) {
                    model.addAttribute("pessoas", pessoa);
                    
                    session.setAttribute("codigoPessoa", pessoa.cd_pessoa);
                    session.setAttribute("nomePessoa", pessoa.ds_nome);

                    Aluguel aluguel = anet.obter(pessoa.cd_pessoa, false).execute().body();

                    if (aluguel == null) {
                    	session.setAttribute("bicicletas", bnet.obterTodos().execute().body());
                    	session.setAttribute("totems", tnet.obterTodos().execute().body());
                        session.setAttribute("codigoBicicleta", "0");
                        session.setAttribute("codigoAluguel", "0");
                        System.out.println(session.getAttribute("bicicletas"));

                    } else {
                        Bicicleta bicicleta = bnet.obter(aluguel.cd_bicicleta).execute().body();
                        session.setAttribute("codigoBicicleta", aluguel.cd_bicicleta);

                        session.setAttribute("codigoAluguel", aluguel.cd_aluguel);

                        if (bicicleta != null) {
                            session.setAttribute("descricaoBicicleta", bicicleta.ds_bicicleta);
                        } else {
                            session.setAttribute("descricaoBicicleta", "Desconhecida");
                        }
                    }

                    session.setAttribute("codigoCarteira", "1");
                }
            }

            if (pessoa.nivelAcesso == 5) {
                return "redirect:/index2";
            }
        } catch (IOException e) {
        }
        return "redirect:/dashboard";
    }

    
    
    
    
    @PostMapping("/selectTotem")
    public String cadastrarPessoa(@RequestParam("cd_totem") String cd_totem,  Model model) {
        try {
        	Totem totem = tnet.obter(Integer.valueOf(cd_totem)).execute().body();

        	
        	model.addAttribute("totemselecionado", totem);
        	return "redirect:/erro/2";
    
        } catch (IOException e) {
            model.addAttribute("mensagem", "Erro ao cadastrar: " + e.getMessage());
            return "redirect:/erro/2";
        }
        
    }
    @PostMapping("/finalizarCadastro")
    public String cadastrarPessoa(@RequestParam("ds_nome") String ds_nome, @RequestParam("nu_cpf") String nu_cpf,
            @RequestParam("dt_nascimento") String dt_nascimento, @RequestParam("ds_email") String ds_email,
            @RequestParam("ds_senha") String ds_senha, @RequestParam("nu_telefone") String nu_telefone, 
            Model model) {
        try {
        	Pessoa pessoa = new Pessoa();
        	pessoa.setNome(ds_nome);
        	pessoa.setDtNascimento(dt_nascimento);
        	pessoa.setEmail(ds_email);
        	pessoa.setSenha(ds_senha);
        	pessoa.setNivelAcesso(1);
        	pessoa.setAtivo(true);
            
        	
        	pessoa.setCPF(nu_cpf);
            pessoa.setTelefone(nu_telefone);
            
            
        	pnet.incluir(pessoa).execute();
            model.addAttribute("mensagem", "Cadastro realizado com sucesso!");
            return "redirect:/cadastro";
        } catch (IOException e) {
            model.addAttribute("mensagem", "Erro ao cadastrar: " + e.getMessage());
            return "redirect:/erro/2";
        }
    }
    
    @PostMapping("/escolherBicicleta")
    public String escolherBicicleta(@RequestParam("cd_bicicleta") String cd_bicicleta, @RequestParam("cd_pessoa") String cd_pessoa, @RequestParam("cd_totem") String cd_totem, @RequestParam("vl_aluguel") String vl_aluguel, Model model) {
        try {
        	Aluguel aluguel = new Aluguel();
        	aluguel.setBicicleta(cd_bicicleta);
        	aluguel.setPessoa(cd_pessoa);
        	aluguel.setTotemRetirada(cd_totem);
        	LocalDateTime agora = LocalDateTime.now();
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        	String dt_retirada = agora.format(formatter);
        	aluguel.setDataRetirada(dt_retirada);
        	aluguel.setDataRetirada(dt_retirada);
        	aluguel.setValorAluguel(vl_aluguel);
        	aluguel.setPago(false);
        	anet.incluir(aluguel).execute();
            model.addAttribute("mensagem", "Aluguel realizado com sucesso!");
            return "redirect:/";
        } catch (IOException e) {
            model.addAttribute("mensagem", "Erro ao cadastrar: " + e.getMessage());
            return "redirect:/erro/3";
        }
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

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @GetMapping("/cadastro2")
    public String cadastro2() {
        return "cadastro2";
    }
    
    @GetMapping("/sobre")
    public String sobre() {
        return "sobre";
    }

    @RequestMapping("/erro")
    public String handleError() {
        return "erro";
    }

    @GetMapping("erro/{cod}")
    @SuppressWarnings("CallToPrintStackTrace")
    public String erro(@PathVariable("cod") String cod, Model model, jakarta.servlet.http.HttpSession session) {
        switch (cod) {
            case "1" ->
                session.setAttribute("codErro", "Não foi possível fazer Login");
            case "2" ->
                session.setAttribute("codErro", "Não foi possível Cadastrar");
            case "3" ->
                session.setAttribute("codErro", "Não foi possível Alugar");
            case "4" ->
                session.setAttribute("codErro", "Não foi possível Pagar");
            case "5" ->
                session.setAttribute("codErro", "Não foi possível Devolver");
            default ->
                session.setAttribute("codErro", "Código (" + cod + "), Desconhecido");
        }

        return "erro";
    }
}
