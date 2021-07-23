package app.br.com.letscode.view;

import app.br.com.letscode.aplicacao.Aplicacao;
import app.br.com.letscode.dominio.Usuario;
import app.br.com.letscode.service.UsuarioService;

import javax.inject.Inject;
import java.util.Scanner;

public class UsuarioViewImpl implements UsuarioView {


    @Inject
    private UsuarioService usuarioService;

    @Override
    public Usuario create(Scanner sc) {

        Usuario usuario = new Usuario();
        System.out.println("Informe seu nome de usúario: ");
        usuario.setNome(sc.next());
        System.out.println("Informe o seu CPF: ");
        usuario.setCpf(sc.next());
        System.out.println("Informe a sua idade: ");
        usuario.setIdade(sc.nextInt());
        usuarioService.create(usuario);
        System.out.printf("Usúario %s criado com sucesso. \n", usuario.getNome());
        return new Usuario();
    }

    @Override
    public String getUsuario(Scanner sc) {
        Aplicacao aplicacao = new Aplicacao();
        System.out.println("Digite seu cpf: ");
        String cpf = sc.next();
        return aplicacao.getNomeDeUsuario(cpf);
    }

}
