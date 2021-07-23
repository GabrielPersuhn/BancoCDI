package app.br.com.letscode.view;

import app.br.com.letscode.dominio.Usuario;

import java.util.Scanner;

public interface UsuarioView {

    Usuario create(Scanner input);

    String getUsuario(Scanner input);

}
