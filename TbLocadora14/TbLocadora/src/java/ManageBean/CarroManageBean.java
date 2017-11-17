package ManageBean;

import java.util.List;
import dao.CarroDAO;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
//import java.util.Objects;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Carro;
import modelo.Upload;

@Named(value = "carroBean")
@RequestScoped
public class CarroManageBean {

    private CarroDAO cDAO = new CarroDAO();
    private Carro carro = new Carro();
    private List<Carro> listaCarrosManage = null;

    ///////////////////// 
    @PostConstruct
    public void innit() {
        if (listaCarrosManage == null) {
            listaCarrosManage = cDAO.listarCarroDAO();  // listarCarroManage = 
        } // end if 
    } // end innit
    ///////////////////// get e set
    public CarroDAO getcDAO() {
        return cDAO;
    }

    public void setcDAO(CarroDAO cDAO) {
        this.cDAO = cDAO;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public List<Carro> getListaCarrosManage() {
        return listaCarrosManage;
    }

    public void setlistaCarrosManage(List<Carro> listaCarrosManage) {
        this.listaCarrosManage = listaCarrosManage;
    }

    ///////////////////////////////////////////////////////////
    // Metodos
    public String cadastrarCarroManage() {
        try {
            if (cDAO.cadastrarCarroDAO(carro)) {

                return mensagemPositiva();
            } // end if
            else {
                return mensagemErro();
            } // end else



        } catch (Exception e) {
            return mensagemErro();
        } // catch





    } // cadastrar

    public void listarCarroManage() {

        listaCarrosManage = cDAO.listarCarroDAO();;
        //return "ok";

    }  // end  listarCarroManage
    ////////////////////////////////////////////////////////////////////////////
    //Mensagens
    public String mensagemPositiva() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Cadastro confirmado!", "encaminhado"));
        return "paginaInicial";
    }  // mensagem positiva

    public String mensagemErro() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Falha no cadastro!!!", "tente novamente"));
        return null;
    } // mensagem erro
}  // class CarroManageBean
