package app.br.com.letscode.service;

import app.br.com.letscode.dominio.Conta;

import java.math.BigDecimal;

public interface ContaService {

    Conta criarconta(Conta conta);

    void depositar(String conta, BigDecimal depositar);

    void sacar(BigDecimal sacar, String conta);

    boolean logar(String autorizado, String senha);


}
