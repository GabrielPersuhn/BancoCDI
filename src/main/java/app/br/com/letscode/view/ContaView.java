package app.br.com.letscode.view;

import app.br.com.letscode.dominio.Conta;

import java.util.Scanner;

public interface ContaView {

    boolean logar(String senha);
    Conta createConta(Scanner input, String cpf);
    void sacar(String conta);
    void saldo(String conta);
    void depositar(String conta);


}
