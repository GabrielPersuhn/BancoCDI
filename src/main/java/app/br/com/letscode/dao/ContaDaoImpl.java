package app.br.com.letscode.dao;

import app.br.com.letscode.dominio.Conta;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class ContaDaoImpl implements ContaDao{
    @Override
    public Conta criar(Conta conta){
        final var caminhoDoArquivo = "C:\\Users\\HP\\Desktop\\Github\\Banco\\BancoCDI\\src\\main\\java\\Arquivos/"  +
                conta.getNumeroConta() + conta.getContaEnum() + ".txt";
        try (var arquivo = new FileWriter(caminhoDoArquivo, false)) {
            var escreverArquivo = new PrintWriter(arquivo);
            escreverArquivo.printf("%s%n%s%n%s%n", conta.getSenha(), conta.getContaEnum(), conta.getSaldo());
        } catch (Exception ex) {
            System.out.println("Não foi possivel criar o arquivo");
        }
        return conta;
    }

    @Override
    public void alterar(BigDecimal saldo, String  senha, String conta, String contaEnum){
        final var caminhoDoArquivo = "C:\\Users\\HP\\Desktop\\Github\\Banco\\BancoCDI\\src\\main\\java\\Arquivos/" +conta+".txt";
        try (var arquivo = new FileWriter(caminhoDoArquivo, false)) {
            var escreverArquivo = new PrintWriter(arquivo);
            escreverArquivo.printf("%s%n%s%n%s%n",senha,contaEnum, saldo);
            System.out.println("Depósito realizado com sucesso.");
        } catch (Exception ex) {
            System.out.println("Não foi possivel criar o arquivo");
        }
    }
}
