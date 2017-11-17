
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Pagamento;
//import modelo.Cliente;
import tools.DAOBD;


public class PagamentoDAO {
    
     
    public String cadastrarPagamentoDAO (Pagamento pagamento) throws SQLException{
      
           
       PreparedStatement stmt;
       
       stmt = DAOBD.conn.prepareStatement 
       ("INSERT INTO Pagamento (id_aluguel, forma_pg, vlr_pago) VALUES (?,?,?)" );
       
       stmt.setLong(1, pagamento.getId_alugel() );
       stmt.setInt(2, pagamento.getForma_pg() );
       stmt.setDouble(3, pagamento.getVlr_pago() );
       
       
       stmt.executeUpdate();
               
        
       /////////////////
       stmt.close();
     //  DAObd.conn.close();
       
          
   return "paginaInicial";
   
    } // cadastarar

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
} // class pagamentoDAO
