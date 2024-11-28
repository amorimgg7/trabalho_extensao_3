package com.example.demo;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

                    Aluguel aluguel = anet.obter(pessoa.cd_pessoa.toString()).execute().body();

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
        if (ds_nome == null || ds_nome.isEmpty() || ds_senha == null || ds_senha.isEmpty()) {
            model.addAttribute("mensagem", "Por favor, preencha todos os campos.");
            return "redirect:/erro/1";
        }

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

                    Aluguel aluguel = anet.obter(pessoa.cd_pessoa.toString()).execute().body();

                    if (aluguel == null || aluguel.cd_aluguel > 0) {
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
    public String cadastrarPessoa(@RequestParam("cd_totem") String cd_totem, Model model) {
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
        if (ds_nome == null || ds_nome.isEmpty() || nu_cpf == null || nu_cpf.isEmpty() ||
            dt_nascimento == null || dt_nascimento.isEmpty() || ds_email == null || ds_email.isEmpty() ||
            ds_senha == null || ds_senha.isEmpty() || nu_telefone == null || nu_telefone.isEmpty()) {
            model.addAttribute("mensagem", "Por favor, preencha todos os campos.");
            return "redirect:/erro/2";
        }

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
    public String escolherBicicleta(@RequestParam("cd_totem") String cd_totem, Model model) {
        try {
            List<Bicicleta> bicicletas = bnet.obterTodos().execute().body();
            if (bicicletas != null) {
                List<Bicicleta> bicicletasFiltradas = bicicletas.stream()
                        .filter(b -> b.getDs_totem().equals(cd_totem))
                        .collect(Collectors.toList());
                model.addAttribute("bicicletas", bicicletasFiltradas);
            } else {
                model.addAttribute("bicicletas", List.of());
            }
            model.addAttribute("totemSelecionado", cd_totem);
            return "dashboard";
        } catch (IOException e) {
            model.addAttribute("mensagem", "Erro ao carregar bicicletas: " + e.getMessage());
            return "redirect:/erro/3";
        }
    }

    @GetMapping("/bicicletasPorLocal/{cd_totem}")
    public String bicicletasPorLocal(@PathVariable("cd_totem") String cd_totem, Model model) {
        try {
            List<Bicicleta> bicicletas = bnet.obterTodos().execute().body();
            if (bicicletas != null) {
                List<Bicicleta> bicicletasFiltradas = bicicletas.stream()
                        .filter(b -> b.getDs_totem().equals(cd_totem))
                        .collect(Collectors.toList());
                model.addAttribute("bicicletas", bicicletasFiltradas);
            } else {
                model.addAttribute("bicicletas", List.of());
            }
            model.addAttribute("totemSelecionado", cd_totem);
            return "dashboard";
        } catch (IOException e) {
            model.addAttribute("mensagem", "Erro ao carregar bicicletas: " + e.getMessage());
            return "redirect:/erro/3";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        try {
            List<Bicicleta> bicicletas = bnet.obterTodos().execute().body();
            System.out.println("API Response - Raw bicicletas: " + bicicletas);

            if (bicicletas != null) {
                for (Bicicleta b : bicicletas) {
                    System.out.println("Bicicleta: " + b.getDs_bicicleta() + ", Totem: " + b.getDs_totem());
                }
                List<Bicicleta> bicicletasBarra = bicicletas.stream()
                        .filter(b -> "Barra da Tijuca".equals(b.getDs_totem()))
                        .collect(Collectors.toList());
                List<Bicicleta> bicicletasIpanema = bicicletas.stream()
                        .filter(b -> "Ipanema".equals(b.getDs_totem()))
                        .collect(Collectors.toList());
                List<Bicicleta> bicicletasLeblon = bicicletas.stream()
                        .filter(b -> "Leblon".equals(b.getDs_totem()))
                        .collect(Collectors.toList());

                System.out.println("Bicicletas Barra: " + bicicletasBarra.size());
                System.out.println("Bicicletas Ipanema: " + bicicletasIpanema.size());
                System.out.println("Bicicletas Leblon: " + bicicletasLeblon.size());

                model.addAttribute("bicicletasBarra", bicicletasBarra);
                model.addAttribute("bicicletasIpanema", bicicletasIpanema);
                model.addAttribute("bicicletasLeblon", bicicletasLeblon);
            }
        } catch (IOException e) {
            System.err.println("API Error: " + e.getMessage());
            e.printStackTrace();
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

    @PostMapping("/detalhesBicicleta")
    public String detalhesBicicleta(@RequestParam("cd_bicicleta") String cd_bicicleta, Model model) {
        try {
            Bicicleta bicicleta = bnet.obter(cd_bicicleta).execute().body();
            if (bicicleta != null) {
                model.addAttribute("bicicleta", bicicleta);
            } else {
                model.addAttribute("mensagem", "Bicicleta não encontrada.");
            }
        } catch (IOException e) {
            model.addAttribute("mensagem", "Erro ao carregar detalhes da bicicleta: " + e.getMessage());
        }
        return "detalhesBicicleta";
    }

    @PostMapping("/alugarBicicleta")
    public String alugarBicicleta(@RequestParam("cd_bicicleta") String cd_bicicleta, jakarta.servlet.http.HttpSession session, Model model) {
        if (cd_bicicleta == null || cd_bicicleta.isEmpty()) {
            model.addAttribute("mensagem", "Por favor, selecione uma bicicleta.");
            return "redirect:/erro/3";
        }

        try {
            Integer cd_pessoa = (Integer) session.getAttribute("codigoPessoa");
            if (cd_pessoa == null) {
                model.addAttribute("mensagem", "Usuário não está logado.");
                return "redirect:/erro/1";
            }

            Bicicleta bicicleta = bnet.obter(cd_bicicleta).execute().body();
            if (bicicleta == null) {
                model.addAttribute("mensagem", "Bicicleta não encontrada.");
                return "redirect:/erro/3";
            }

            // Verificar se ds_totem não é nulo
            String ds_totem = bicicleta.getDs_totem();
            if (ds_totem == null) {
                model.addAttribute("mensagem", "Totem não encontrado.");
                return "redirect:/erro/3";
            }

            Totem totem = tnet.obterTodos().execute().body().stream()
                .filter(t -> t.getDs_totem().equals(ds_totem))
                .findFirst()
                .orElse(null);
            if (totem == null) {
                model.addAttribute("mensagem", "Totem não encontrado.");
                return "redirect:/erro/3";
            }

            Aluguel aluguel = new Aluguel();
            aluguel.setCd_pessoa(cd_pessoa.toString());
            aluguel.setCd_bicicleta(cd_bicicleta);
            aluguel.setCd_totem_retirada(totem.getCd_totem().toString());
            aluguel.setDt_retirada(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            aluguel.setPago(false);

            anet.incluir(aluguel).execute();

            session.setAttribute("codigoBicicleta", cd_bicicleta);
            session.setAttribute("codigoAluguel", aluguel.getCd_aluguel());
            session.setAttribute("descricaoBicicleta", bicicleta.getDs_bicicleta());

            return "redirect:/dashboard";
        } catch (IOException e) {
            model.addAttribute("mensagem", "Erro ao alugar bicicleta: " + e.getMessage());
            return "redirect:/erro/3";
        }
    }

    @PostMapping("/devolverBicicleta")
    public String devolverBicicleta(@RequestParam("cd_aluguel") Integer cd_aluguel, @RequestParam("cd_totem_devolucao") String cd_totem_devolucao, jakarta.servlet.http.HttpSession session, Model model) {
        if (cd_aluguel == null || cd_totem_devolucao == null || cd_totem_devolucao.isEmpty()) {
            model.addAttribute("mensagem", "Por favor, preencha todos os campos.");
            return "redirect:/erro/3";
        }

        try {
            Aluguel aluguel = anet.obter(cd_aluguel.toString()).execute().body();
            if (aluguel == null) {
                model.addAttribute("mensagem", "Aluguel não encontrado.");
                return "redirect:/erro/3";
            }

            LocalDateTime dtDevolucao = LocalDateTime.now();
            aluguel.setDt_devolucao(dtDevolucao.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            aluguel.setCd_totem_devolucao(cd_totem_devolucao);

            LocalDateTime dtRetirada = LocalDateTime.parse(aluguel.getDt_retirada(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Duration duration = Duration.between(dtRetirada, dtDevolucao);
            double hoursUsed = duration.toHours() + (duration.toMinutesPart() / 60.0);

            Bicicleta bicicleta = bnet.obter(aluguel.getCd_bicicleta()).execute().body();
            if (bicicleta == null) {
                model.addAttribute("mensagem", "Bicicleta não encontrada.");
                return "redirect:/erro/3";
            }

            double cost = hoursUsed * Double.parseDouble(bicicleta.getNu_preco());
            aluguel.setNu_valor_aluguel(String.valueOf(cost));
            aluguel.setPago(true);

            anet.atualizar(aluguel).execute();

            session.setAttribute("codigoBicicleta", "0");
            session.setAttribute("codigoAluguel", "0");
            session.setAttribute("descricaoBicicleta", "Nenhuma");

            model.addAttribute("mensagem", "Bicicleta devolvida com sucesso. Custo total: R$ " + cost);
            return "redirect:/dashboard";
        } catch (IOException e) {
            model.addAttribute("mensagem", "Erro ao devolver bicicleta: " + e.getMessage());
            return "redirect:/erro/3";
        }
    }
}
