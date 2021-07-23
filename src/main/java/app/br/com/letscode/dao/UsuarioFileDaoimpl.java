package app.br.com.letscode.dao;

import app.br.com.letscode.dominio.Usuario;

import java.io.FileWriter;
import java.io.PrintWriter;

public class UsuarioFileDaoimpl implements UsuarioDAO {

    @Override
    public Usuario criar(Usuario usuario) {
        final var caminhoDoArquivo = "C:\\Users\\HP\\Desktop\\Github\\Banco\\BancoCDI\\src\\main\\java\\Arquivos/usuarios.txt";
        try (var arquivo = new FileWriter(caminhoDoArquivo, true)) {
            var escreverArquivo = new PrintWriter(arquivo);
            escreverArquivo.printf("%s%n%s%n%s%n", usuario.getNome(), usuario.getCpf(), usuario.getIdade());
        } catch (Exception ex) {
            System.err.println("Não foi possivel criar o arquivo");
        }
        return usuario;
    }


}
