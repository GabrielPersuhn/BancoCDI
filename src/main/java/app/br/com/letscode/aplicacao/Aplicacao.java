package app.br.com.letscode.aplicacao;

import app.br.com.letscode.dominio.ContaEnum;
import app.br.com.letscode.view.ContaView;
import app.br.com.letscode.view.UsuarioView;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

@Getter
@Setter
public class Aplicacao {


    @Inject
    private UsuarioView usuarioView;

    @Inject
    private ContaView contaView;


    public void creatConta(Scanner sc, String cpf) {
        contaView.createConta(sc, cpf);
    }

    public void deposito(String conta) {
        contaView.depositar(conta);
    }

    public void saldo(String conta) {
        contaView.saldo(conta);
    }

    public void sacar(String conta) {
        contaView.sacar(conta);
    }

    public void init(Aplicacao aplicacao) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("Bem Vindo");
            System.out.println("O que gostaria de fazer?" +
                    "\n1- Cadastar Usuario" +
                    "\n2- Realizar Login" +
                    "\n3- Sair");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    usuarioView.create(sc);
                case 2:
                    aplicacao.login(aplicacao);
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Escolha uma opção válida");
            }
        } while (opcao > 0);
    }

    public String getNomeDeUsuario(String cpf) {
        final var caminhoDoArquivo = "C:\\Users\\HP\\Desktop\\Github\\Banco\\BancoCDI\\src\\main\\java\\Arquivos/usuarios.txt";
        try (var arquivo = new BufferedReader(new FileReader(caminhoDoArquivo))) {
            var validacao = arquivo.readLine();
            while (validacao != null) {
//                arquivo.readLine();
//                validacao = arquivo.readLine();
                if (cpf.equalsIgnoreCase(validacao)) {
                    return cpf;
                }
                validacao = arquivo.readLine();
            }
        } catch (Exception ex) {
            System.out.println("Não foi possivel encontrar o usuario");
        }
        return null;
    }

    public boolean getConta(String cpf, ContaEnum contaEnum) {
        final var caminhoDoArquivo = "C:\\Users\\HP\\Desktop\\Github\\Banco\\BancoCDI\\src\\main\\java\\Arquivos/" +
                 cpf + contaEnum + ".txt";
        try (var arquivo = new BufferedReader(new FileReader(caminhoDoArquivo))) {
            String busca = arquivo.readLine();
            return  contaView.logar(busca);
        } catch (Exception ex) {
            System.out.println("Desculpe, você não tem uma conta do tipo " +
                    contaEnum);
            return false;
        }
    }

    public String getUsuario(Scanner sc){
        return usuarioView.getUsuario(sc);
    }

    public void login(Aplicacao aplicacao) {
        Scanner sc = new Scanner(System.in);
        String usuario = aplicacao.getUsuario(sc);
        if (usuario != null) {
            usuarioAutenticado(aplicacao, usuario);
        } else {
            System.err.println("Usuario é nulo");
            login(aplicacao);
        }
    }

    private static void usuarioAutenticado(Aplicacao aplicacao, String cpf) {
        System.out.println("Usúario logado com sucesso ");
        int escolha;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("O que deseja fazer?" +
                    "\n1- Cadastar nova conta" +
                    "\n2- Entrar na sua conta" +
                    "\n3- Sair");
            escolha = input.nextInt();
            switch (escolha) {
                case 1:
                    aplicacao.creatConta(input, cpf);
                case 2:
                    String conta = contaLogin(aplicacao, cpf);
                    if (conta != null) {
                        contaAutenticada(aplicacao, conta);
                    }
                    break;
                case 3:
                    System.exit(0);
                    return;
                default:
                    System.out.println("Selecione uma opção valida");
            }
        } while (escolha > 0);
    }

    private static void contaAutenticada(Aplicacao aplicacao, String conta) {
        int escolha;
        do {
            System.out.println("Qual operação você deseja realizar?" +
                    "\n1- Visualizar seu saldo" +
                    "\n2- Depositar" +
                    "\n3- Sacar" +
                    "\n0- Sair");
            var input = new Scanner(System.in);
            escolha = input.nextInt();
            switch (escolha) {
                case 1:
                    aplicacao.saldo(conta);
                    contaAutenticada(aplicacao, conta);
                    break;
                case 2:
                    aplicacao.deposito(conta);
                    contaAutenticada(aplicacao, conta);
                    break;
                case 3:
                    aplicacao.sacar(conta);
                    contaAutenticada(aplicacao, conta);
                    break;
                case 0:
                    System.exit(0);
            }
        } while (escolha > 0);
    }

    private static String contaLogin(Aplicacao aplicacao, String cpf) {

        System.out.println("Em qual conta deseja logar?" +
                "\n1- Conta Especial" +
                "\n2- Conta Poupança");
        Scanner input = new Scanner(System.in);
        int tipoConta = input.nextInt();

        switch (tipoConta) {
            case 1:
                if (aplicacao.getConta(cpf, ContaEnum.CONTA_ESPECIAL) == true) {
                    return cpf + ContaEnum.CONTA_ESPECIAL;
                }
                break;
            case 2:
                if (aplicacao.getConta(cpf, ContaEnum.CONTA_POUPANÇA) == true) {
                    return cpf + ContaEnum.CONTA_POUPANÇA;
                }
            default:
                System.out.println("Selecine uma opção válida");
        }
        return null;
    }
}



