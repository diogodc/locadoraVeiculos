
package ManageBean;


import dao.PagamentoDAO;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import modelo.Pagamento;



@Named(value = "pagamentoBean")
@RequestScoped
public class PagamentoManagedBean {

  private PagamentoDAO pDAO = new PagamentoDAO() ;
  private Pagamento pagamento = new Pagamento() ;
// private List<Pagamento> listaPagamentoManage = null;
  
  
  
  
    ///////////////////// get e set
    public PagamentoDAO getpDAO() {
        return pDAO;
    }

    public void setpDAO(PagamentoDAO pDAO) {
        this.pDAO = pDAO;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    
    
    
    ///////////////////////////////////////////////////////////////////
    
    
     public String cadastrarPagamentoManage () throws SQLException{  
        pDAO.cadastrarPagamentoDAO(pagamento);
        return "paginaInicial";
        
   } // cadastrar
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}  //  class PagamentoManagedBean
