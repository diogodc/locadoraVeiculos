package ManageBean;

//import java.util.Date;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "sessaoLoginUsuario")
@SessionScoped
public class IDEscopoSessaoUsuario implements Serializable {

    public String loginSessao;
    public String senhaSessao; 
    //public Date tempoSessao;

    public String getLoginSessao() {
        return loginSessao;
    }

    public void setLoginSessao(String loginSessao) {
        this.loginSessao = loginSessao;
    }

    public String getSenhaSessao() {
        return senhaSessao;
    }

    public void setSenhaSessao(String senhaSessao) {
        this.senhaSessao = senhaSessao;
    }
 /*
    public Date getTempoSessao() {
        return tempoSessao;
    }

    public void setTempoSessao(Date tempoSessao) {
        this.tempoSessao = tempoSessao;
    }*/
    
    
    
    
    
    
    
    
} // classssssssssssssss
