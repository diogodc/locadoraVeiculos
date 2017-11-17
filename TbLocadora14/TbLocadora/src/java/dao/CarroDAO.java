package dao;

import ManageBean.CarroManageBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Carro;
import tools.DAOBD;

public class CarroDAO {

    private static List<Carro> listaCarroSelect = new ArrayList<Carro>();

    public boolean cadastrarCarroDAO(Carro carro) throws SQLException {

        Carro carroSelect = null;

        try {
            PreparedStatement stmt = DAOBD.conn.prepareStatement(
                    "SELECT id_carro FROM carro WHERE placa = ?");
            stmt.setString(1, carro.getPlaca());

            ResultSet rset = stmt.executeQuery();
            carroSelect = new Carro();

            if (rset.next()) {

                carroSelect.setId_carro(rset.getInt("id_carro")); // achou no BD, logo ele já existe .

                stmt.close();
            } // end if
            else {
                System.out.println("Falha no SELECT !!!!!!");
                stmt.close();
                carroSelect.setId_carro(-1);   // igual a -1 quer dizer que não achou o cadastrado.
            }  // end else


        } catch (SQLException ex) {   // 1º end TRY
            // System.out.println("catch falha na consulta!!!");
            //Logger.getLogger(UsuarioManageBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "catch falha na consulta!!!", "."));
            return false;
        } // end catch   




        ///////////////////////////////////////////////////////////



        try {
            if (carroSelect.getId_carro() != -1) {     // achou  no BD, logo ele já existe .
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "já está cadastrado!!!", "."));
                return false;

            } else {    // igual a -1 quer dizer que não achou  cadastrado.
                PreparedStatement stmt;

                stmt = DAOBD.conn.prepareStatement(
                        "INSERT INTO carro (placa, descricao, ano, km_atual, vlr_diaria, img, obs) VALUES (?,?,?,?,?,?,?)");

                stmt.setString(1, carro.getPlaca());
                stmt.setString(2, carro.getDescricao());
                stmt.setInt(3, carro.getAno());
                stmt.setInt(4, carro.getKm_atual());
                stmt.setDouble(5, carro.getVlr_diaria());
                stmt.setString(6, carro.getImg());
                stmt.setString(7, carro.getObs());

                stmt.executeUpdate();

                stmt.close();
                //DAOBD.conn.close();

                return true;

            }  // end else


        } catch (SQLException ex) {   // 2º end TRY
            //Logger.getLogger(UsuarioManageBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "catch falha no cadastro!!!", "."));
            return false;
        } // end catch   




    }// cadastrarCarroDAO

    public double consultarValorDiaria(long id_carro) throws SQLException {

        Carro carroSelect = null;

        try {
            PreparedStatement stmt = DAOBD.conn.prepareStatement(
                    "SELECT vlr_diaria FROM carro WHERE id_carro = ?");

            stmt.setLong(1, id_carro);

            ResultSet rset = stmt.executeQuery();

            if (rset.next()) {

                carroSelect = new Carro();
                carroSelect.setVlr_diaria(rset.getDouble("vlr_diaria"));

                stmt.close();

            } // end if
            else {
                System.out.println("Falha no SELECT !!!!!!");
                stmt.close();
                return 0;
            }  // end else


        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "catch falha na consulta!!!", "SQLException"));
            return 0;
        } // end catch   



        return carroSelect.getVlr_diaria();  // retorna valor da Diaria do carro para o BEAN  


    }  // consultarValorDiaria

    public List<Carro> listarCarroDAO() {
        // Listar os carros que não estão em uma locação atual.
        Carro carroSelect = null;
        listaCarroSelect.clear();
        try {  // editar de acor com a data
            PreparedStatement stmt = DAOBD.conn.prepareStatement(
                    "SELECT distinct C.id_carro, C.descricao, C.ano, C.vlr_diaria FROM carro C, aluguel A where C.id_carro not in (select id_carro from aluguel A where A.dt_fim is null)");
            // mostrar todos os carros que não estão no aluguel ou que já foram devolvido (com dt_fim).
            
            //stmt.setLong(1, id_carro);

            ResultSet rset = stmt.executeQuery();

            //carroSelect = new Carro();

            while (rset.next()) {  // antigo if

                carroSelect = new Carro();
                carroSelect.setId_carro(rset.getInt("id_carro"));
                carroSelect.setDescricao(rset.getString("descricao"));
                carroSelect.setAno(rset.getInt("ano"));
                carroSelect.setVlr_diaria(rset.getDouble("vlr_diaria"));

                
                listaCarroSelect.add(carroSelect);

                //carroSelect = null;

            }   // end while 

            stmt.close();


       
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "catch falha na listagem!!!", "."));
            return null;
        } // end catch   



        return listaCarroSelect;  // retorna a lsita de carros para o managebean.



    }   // end listarCarroDAO
    
    
}  // end Class CarroDAO

