
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DAOBD {
    
public static Connection conn;
    static {
        try{
            // Carrega o driver do SQL e conecta.
            Class.forName(Config.NOME_DRIVER);
            conn = DriverManager.getConnection(Config.BD_URL, Config.BD_LOGIN, Config.BD_SENHA);
           } catch (ClassNotFoundException e){
           System.out.println("erro: drive nao encontrado");
           System.exit(1);
           } catch (SQLException e) {                 
             //   Logger.getLogger(DAOBD.class.getName()).log(Level.SEVERE, null, ex);
              System.out.println("Erro SQL: " + e.getMessage() ); 
              System.exit(1);
              }                 
           }
    

    
    
} // class DAOBD
