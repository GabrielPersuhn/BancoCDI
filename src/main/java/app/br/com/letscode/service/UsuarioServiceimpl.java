package app.br.com.letscode.service;

import app.br.com.letscode.dao.UsuarioDAO;
import app.br.com.letscode.dominio.Usuario;

import javax.inject.Inject;


public class UsuarioServiceimpl implements UsuarioService {

    @Inject
    private UsuarioDAO usuarioDAO;

    @Override
    public Usuario create(Usuario usuario)  {
        return usuarioDAO.criar(usuario);
    }
}

