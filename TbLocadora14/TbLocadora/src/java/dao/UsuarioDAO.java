
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import tools.DAOBD;

public class UsuarioDAO {    // antigo IDToolsBDUsuario


    public Usuario consultarUsuarioDAO(String login, String senha) {  // verificar()
        Usuario usuarioSelect = null;

        try {
            PreparedStatement stmt = DAOBD.conn.prepareStatement(
                    "SELECT * FROM usuario WHERE login = ? and senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rset = stmt.executeQuery();

            if (rset.next()) {

                usuarioSelect = new Usuario();
                usuarioSelect.setLogin(rset.getString("login"));
                usuarioSelect.setSenha(rset.getString("senha"));
                //usuarioSelect.setNome  (rset.getString("nome") );

                stmt.close();

            } // end if
            else {
                System.out.println("Falha no SELECT !!!!!!");
                stmt.close();
                return null;

            }  // end else


        } catch (SQLException ex) {
            // System.out.println("catch falha na consulta!!!");
            //Logger.getLogger(UsuarioManageBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "catch falha na consulta!!!", "SQLException"));
            return null;
        } // end catch   



        return usuarioSelect;  // retorna o usuario do BD para o BEAN  


    }   // consulta!!!!!!!!
} // class usuariodao
