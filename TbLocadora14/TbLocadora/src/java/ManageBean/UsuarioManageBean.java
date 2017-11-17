package ManageBean;

import dao.UsuarioDAO;    // DAO.IDToolsBDUsuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
@ManagedBean(name="usuarioBean")
@RequestScoped

public class UsuarioManageBean {  //IDEscopoRequisicaoUsuario
                              
    @ManagedProperty(value="#{sessaoLoginUsuario}")
    private IDEscopoSessaoUsuario sessaoLoginUsuario;
                                  
    private String login;
    private String senha;
    
    private UsuarioDAO idToolsBDUsuario = new UsuarioDAO();  // antigo uDAO

    //Usuario usuarioLogin = new Usuario();
    
    
    ////////////////////////
    public UsuarioDAO getIdToolsBDUsuario() {
        return idToolsBDUsuario;
    }

    public void setIdToolsBDUsuario(UsuarioDAO idToolsBDUsuario) {
        this.idToolsBDUsuario = idToolsBDUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public IDEscopoSessaoUsuario getSessaoLoginUsuario() {
        return sessaoLoginUsuario;
    }
    
    public void setSessaoLoginUsuario(IDEscopoSessaoUsuario sessaoLoginUsuario) {
        this.sessaoLoginUsuario = sessaoLoginUsuario;
    }
    
    
    
////////////////////////////////////////////////////////////////////////////
    public String valida() {   //IDEscopoRequisicaoUsuario

        String navegacao = "";
        Usuario usuarioComparacao = idToolsBDUsuario.consultarUsuarioDAO(login, senha);
         
        if (usuarioComparacao != null) {
            sessaoLoginUsuario.setLoginSessao(usuarioComparacao.getLogin());
            sessaoLoginUsuario.setSenhaSessao(usuarioComparacao.getSenha());
            navegacao = mensagemPositiva();
        } else {
            navegacao = mensagemErro();
            
        }// end else
        
        
     return navegacao;   
        
        ////////
        //Usuario usuarioComparacao = new Usuario();
        // usuarioComparacao = idToolsBDUsuario.consultarUsuarioDAO(this.getLogin(), this.getSenha());
        //System.out.println(usuarioComparacao.getLogin() );

        /* try {
            
            
            ///////////////////////////
            
            if (usuarioComparacao.getLogin().equals(this.getLogin()) && usuarioComparacao.getSenha().equals(this.getSenha())) {
                return mensagemPositiva();
            } // end if
            else {
                return mensagemErro();
            } // end else
        } catch (Exception e) {
            return mensagemErro();
        } // catch
*/

    }  // end valida    

    
    
    
////////////////////////////////////////////////////////////////////////////
    public String mensagemPositiva() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Bem vindo!", "Bem vindo ao sistema TbLocadora"));
        return "sucesso";
    }

    public String mensagemErro() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Usuario ou senha incorretos!!!", "Tente novamente"));
        return "invasao";
    }
    
    
    
    
} //  class UsuarioManageBean