package com.example.demo;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
            }else {
            	if (pessoa.nivelAcesso == 1) {
            		model.addAttribute("pessoas", pessoa);
            		session.setAttribute("codigoPessoa", pessoa.cd_pessoa);
            		session.setAttribute("nome", pessoa.ds_nome);
            		
            		Aluguel aluguel = anet.obter(pessoa.cd_pessoa, false).execute().body();
            		
            		if(aluguel == null) {
            			session.setAttribute("codigoBicicleta", "0");
            			session.setAttribute("codigoAluguel", "0");
            		}else {
            			Bicicleta bicicleta = bnet.obter(aluguel.cd_bicicleta).execute().body();
            			session.setAttribute("codigoBicicleta", aluguel.cd_bicicleta);
            			
            			session.setAttribute("codigoAluguel", aluguel.cd_aluguel);
            			
            			if(bicicleta != null) {
            				session.setAttribute("descricaoBicicleta", bicicleta.ds_bicicleta);
            			}else {
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

    /*
	@GetMapping("/index2/{codigo}")
	public String index2(@PathVariable("codigo") Integer codigo, Model model) {
	    try {
	        Object pessoas = pnet.obter(codigo).execute().body();
	        if (pessoas == null) {
	            throw new RuntimeException("Nenhuma pessoa encontrada para o código: " + codigo);
	        }
	        model.addAttribute("pessoas", pessoas);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "erro"; 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "erro"; 
	    }
	    return "index2";
	}
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        try {
            model.addAttribute("pessoas", pnet.obterTodos().execute().body());

        } catch (IOException e) {
            System.out.println("Erro");
        }
        return "dashboard";
    }

    

    @RequestMapping("/erro")
    public String handleError() {
        return "erro";
    }
    
    
    @GetMapping("erro/{cod}")
    @SuppressWarnings("CallToPrintStackTrace")
    public String erro(@PathVariable("cod") String cod, Model model, jakarta.servlet.http.HttpSession session) {
    	switch (cod) {
    		case "1":
    			session.setAttribute("codErro", "Não foi possível fazer Login");
    			break;
    		case "2":
    			session.setAttribute("codErro", "Não foi possível Cadastrar");
    			break;
    		case "3":
    			session.setAttribute("codErro", "Não foi possível Alugar");
    			break;
    		case "4":
    			session.setAttribute("codErro", "Não foi possível Pagar");
    			break;
    		case "5":
    			session.setAttribute("codErro", "Não foi possível Devolver");
    			break;
    		default:
    			session.setAttribute("codErro", "Código ("+cod+ "), Desconhecido");
    			break;
    	}

        return "erro";
    }
    

}
