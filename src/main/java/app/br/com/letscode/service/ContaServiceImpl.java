package app.br.com.letscode.service;


import app.br.com.letscode.dao.ContaDao;
import app.br.com.letscode.dominio.Conta;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;

public class ContaServiceImpl implements ContaService {

    @Inject
    private ContaDao contaDao;

    @Override
    public Conta criarconta(Conta conta) {
        return contaDao.criar(conta);
    }

    @Override
    public void depositar(String conta, BigDecimal depositar){
        final var caminhoDoArquivo = "C:\\Users\\HP\\Desktop\\Github\\Banco\\BancoCDI\\src\\main\\java\\Arquivos/"
                + conta + ".txt";
        try (var lerArquivo = new BufferedReader(new FileReader(caminhoDoArquivo))){
            String senha = lerArquivo.readLine();
            String contaEnum = lerArquivo.readLine();
            String saldo = lerArquivo.readLine();
            BigDecimal novoSaldo = new BigDecimal(saldo).add(depositar);
            contaDao.alterar(novoSaldo,senha,conta,contaEnum);
        } catch (Exception ex) {
            System.out.println("Erro interno, não foi possivel concluir a ação" +
                    " contate o suporte.");
        }
    }

    @Override
    public void sacar(BigDecimal sacar, String conta) {
        final var caminhoDoArquivo = "C:\\Users\\HP\\Desktop\\Github\\Banco\\BancoCDI\\src\\main\\java\\Arquivos/"
                + conta + ".txt";
        try (var lerArquivo = new BufferedReader(new FileReader(caminhoDoArquivo))){
            String senha = lerArquivo.readLine();
            String contaEnum = lerArquivo.readLine();
            String saldo = lerArquivo.readLine();
            BigDecimal novoSaldo = new BigDecimal(saldo).subtract(sacar);
            System.out.println("Saque realizado com sucesso.");
                if (contaEnum.equalsIgnoreCase("ESPECIAL")){
                int limite = novoSaldo.compareTo(new BigDecimal("-201"));
                if (limite < 1){
                    System.out.println("Você já ultrapassou seu limite");
                    return;
                }
            }
            contaDao.alterar(novoSaldo,senha,conta,contaEnum);
        } catch (Exception ex) {
            System.out.println("Erro interno, não foi possivel concluir a ação" +
                    " contate o suporte.");
        }
    }

    @Override
    public boolean logar(String autorizado, String senha) {
        if (autorizado.equals(senha)){
            System.out.println("Logado com sucesso");
            return true;
        }else {
            System.out.println("Não autorizado, senha incorreta");
            return false;
        }
    }
}
