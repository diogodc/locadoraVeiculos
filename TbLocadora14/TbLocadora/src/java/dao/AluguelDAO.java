package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Aluguel;
import tools.DAOBD;

public class AluguelDAO {

    private static List<Aluguel> listaAluguelSelect = new ArrayList<Aluguel>();
    

    public List<Aluguel> listarAluguelDAO() {


        Aluguel aluguelSelect = null;
        listaAluguelSelect.clear();

        try {
            PreparedStatement stmt = DAOBD.conn.prepareStatement(
                    "SELECT * FROM aluguel WHERE dt_fim is null");
            //stmt.setLong(1, id_aluguel);

            ResultSet rset = stmt.executeQuery();

            //aluguelSelect = new Aluguel();

            while (rset.next()) {  // antigo if

                aluguelSelect = new Aluguel();
                aluguelSelect.setId_aluguel(rset.getInt("id_aluguel"));
                aluguelSelect.setId_cliente(rset.getInt("id_cliente"));
                aluguelSelect.setId_carro(rset.getInt("id_carro"));
                aluguelSelect.setDt_devolucao(rset.getDate("dt_devolucao"));
                aluguelSelect.setVlr_aluguel(rset.getDouble("vlr_alugel"));


                listaAluguelSelect.add(aluguelSelect);

                //aluguelSelect = null;

            }   // end while 

            stmt.close();



        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "catch falha na listagem do aluguel", "!!!"));
            return null;
        } // end catch   



        return listaAluguelSelect;  // retorna a lsita de aluguels para o managebean.




    } // listar

    /////////////////////////////////////////////
    public int cadastrarAluguelDAO(Aluguel aluguel) throws SQLException {

        Aluguel aluguelSelect = null;

        try {
            PreparedStatement stmt = DAOBD.conn.prepareStatement(
                    "SELECT id_aluguel FROM aluguel WHERE id_carro = ? and dt_fim is null"); // seleciona quando existe a Id_carro no aluguel e ainda não foi devolvido.
                        
            stmt.setInt(1, aluguel.getId_carro());

            ResultSet rset = stmt.executeQuery();
            
            aluguelSelect = new Aluguel();

            if (rset.next()) {

                aluguelSelect.setId_aluguel(rset.getInt("id_aluguel")); // retornou a id_carro (achou no BD), logo ele já existe. Então não deixa inserir.

                stmt.close();
            } // end if
            else {
                System.out.println("não achou o aluguel - ok, vai fazer a inserção");
                stmt.close();
                aluguelSelect.setId_aluguel(-1);   // igual a -1 quer dizer que não achou  aluguel.
            }  // end else


        } catch (SQLException ex) {   // 1º end TRY
            //Logger.getLogger(UsuarioManageBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Falha na consulta!!!", "."));
            return 0;
        } // end catch   




        ///////////////////////////////////////////////////////////



        try {
            if (aluguelSelect.getId_aluguel() != -1) {     // achou  no BD, logo ele já existe .
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Já existe aluguel para este Veículo", "!!!"));
                return 0;

            } else {    // igual a -1 quer dizer que não achou  cadastrado.

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                PreparedStatement stmt;

                stmt = DAOBD.conn.prepareStatement(
                        "INSERT INTO aluguel (id_cliente, id_carro, dt_inicio, dt_devolucao, vlr_alugel) VALUES (?,?,?,?,?)" ,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                
                stmt.setInt(1, aluguel.getId_cliente());
                stmt.setInt(2, aluguel.getId_carro());

                Date dataTemp = aluguel.getDt_inicio();   // transfere a data para uma variavel temporaria para conversão.            
                String dataFormatada = df.format(dataTemp);  // converte a dataTemp para o formato igual ao do BD ("yyyy-MM-dd hh:mm:ss").
                stmt.setTimestamp(3, java.sql.Timestamp.valueOf(dataFormatada));    // insert no BD a dataFormadata.
                // stmt.setDate(3, (java.sql.Date) aluguel.getDt_inicio());     // data que foi efetuada a locação.

                dataTemp = aluguel.getDt_devolucao();
                dataFormatada = df.format(dataTemp);
                stmt.setTimestamp(4, java.sql.Timestamp.valueOf(dataFormatada));
                //stmt.setDate(4, (java.sql.Date) aluguel.getDt_devolucao());  // data prevista para a devolução.


                stmt.setDouble(5, aluguel.getVlr_aluguel());   // valor estipulado para o aluguel no ato da locação.


                stmt.executeUpdate();

                int aluguelId = 0;  // temporario, pois está retornando INcorretamente a id do autoincrement.
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) aluguelId = rs.getInt(1);
                        
                stmt.close();
                

                return aluguelId;

            }  // end else


        } catch (SQLException ex) {   // 2º end TRY
            //Logger.getLogger(UsuarioManageBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alguma coisa deu errado!!!", "Tente novamente"));
                return 0;
        } // end catch   








    } // cadastarar

    public boolean cadastrarDevolucaoDAO(Aluguel aluguel) throws SQLException {

        // Aluguel aluguelSelect = null;

        try {

            PreparedStatement stmt;

            stmt = DAOBD.conn.prepareStatement(
                    "update aluguel set dt_fim = ?, vlr_taxa = ?, vlr_total = ? WHERE id_aluguel = ?");

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date dataTemp = aluguel.getDt_fim();   // transfere a data para uma variavel temporaria para conversão.            
            String dataFormatada = df.format(dataTemp);  // converte a dataTemp para o formato igual ao do BD ("yyyy-MM-dd hh:mm:ss").
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(dataFormatada));    // insert no BD a dataFormadata.
//            stmt.setDate(1, (java.sql.Date) aluguel.getDt_fim());   // data prevista para a devolução.
            
            stmt.setDouble(2, aluguel.getVlr_taxa());   // valor adicionais de avarias que podem ter no ato da devolução.
            stmt.setDouble(3, aluguel.getVlr_total() );   // valro total calculado.
            stmt.setInt(4, aluguel.getId_aluguel());   // id_aluguel

            stmt.executeUpdate();

            stmt.close();
            //DAOBD.conn.close();

            return true;




        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioManageBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "catch falha no cadastro!!!", "."));
            return false;
        } // end catch   





    } // end  cadastrarDevolucaoDAO
} // aluguelDAO
