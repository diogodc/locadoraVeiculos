
package ManageBean;

import dao.ClienteDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Cliente;


@Named(value = "clienteBean")
@RequestScoped
public class ClienteManageBean {
    
    
//////////////////////////////////////////////////    
    
private ClienteDAO clDAO = new ClienteDAO() ;
private Cliente cliente = new Cliente() ;
 private ArrayList<Cliente> listaClienteManage = null;
 
    @PostConstruct
    public void innit() {
        if (listaClienteManage == null) {
            
            listaClienteManage = clDAO.listarClienteDAO();
        } else {
        } // end if 
    } // end innit
//////////////////////////////////// get e set
    public ClienteDAO getClDAO() {
        return clDAO;
    }

    public void setClDAO(ClienteDAO clDAO) {
        this.clDAO = clDAO;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Cliente> getListaClienteManage() {
        return listaClienteManage;
    }

    public void setListaClienteManage(ArrayList<Cliente> listaClienteManage) {
        this.listaClienteManage = listaClienteManage;
    }

    
    
    ///////////////////////////////////////////////////////////////////
    
    
public String cadastrarClienteManage (){

    try{
        if (clDAO.cadastrarClienteDAO(cliente) )  {
        
            return mensagemPositiva();
        }  // end if
        else{
            return mensagemErro();   
        } // end else

    
     
    } catch(Exception e){ 
         return mensagemErro();
    } // catch
    
} // end cadastrar     
      
public Cliente pesquisarContato(Integer id){    

       
   for(int i = 0; i < listaClienteManage.size(); i ++){    
      if(listaClienteManage.get(i).getId_cliente() == id){    
         return listaClienteManage.get(i);     
      }  
   }    
    return null;
}      
      
      
      
      
////////////////////////////////////////////////////////////////////////////
   public String mensagemPositiva(){
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage (FacesMessage.SEVERITY_INFO, 
               "Cadastro do cliente confirmado!","encaminhado")  );
       return "paginaInicial";
       }  // mensagem positiva
   
   public String mensagemErro(){
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage (FacesMessage.SEVERITY_ERROR, 
               "Falho no cadastro!!!","tente novamente")  );
       return null;
   } // mensagem erro

        
        
        
        

   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
} // class ClienteManageBean 
