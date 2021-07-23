package app.br.com.letscode.dominio;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Conta {
    private String numeroConta;
    private String senha;
    private ContaEnum contaEnum;
    protected BigDecimal saldo;

}
