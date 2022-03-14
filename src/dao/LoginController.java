/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;
import view.LoginView;
import view.MenuView;

/**
 *
 * @author user
 */
public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public void autenticar() throws SQLException {
        //buscar um Usuario da View
        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordFieldSenha().getText();
        
        Usuario usuarioAutenticado = new Usuario(usuario, senha);
        //Verificar se existe no DB
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
        
       boolean existe = usuarioDao.existeNoBancoPorUsuarioESenha(usuarioAutenticado);
        
        //Se existe direciona para menu
        if (existe) {
            MenuView telamenu = new MenuView();
        telamenu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(view, "Usuario ou senha invalidos!");
        }
       
    }
    
}
