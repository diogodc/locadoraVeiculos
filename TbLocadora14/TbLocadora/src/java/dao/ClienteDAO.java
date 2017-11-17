package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Carro;
import modelo.Cliente;
import tools.DAOBD;

/////////////////////////////////////////////////////
public class ClienteDAO {

    private static ArrayList<Cliente> listaClienteSelect = new ArrayList<Cliente>();

    public Boolean cadastrarClienteDAO(Cliente cliente) {
        Cliente clienteSelect = null;

        try {
            PreparedStatement stmt = DAOBD.conn.prepareStatement(
                    "SELECT id_cliente FROM cliente WHERE cpf_cnpj = ? or rg_ie = ?");
            stmt.setString(1, cliente.getCpf_cnpj());
            stmt.setString(2, cliente.getRg_ie());

            ResultSet rset = stmt.executeQuery();
            clienteSelect = new Cliente();

            if (rset.next()) {

                clienteSelect.setId_cliente(rset.getInt("id_cliente")); // achou o cliente no BD, logo ele já existe .

                stmt.close();
            } // end if
            else {
                System.out.println("Falha no SELECT !!!!!!");
                stmt.close();
                clienteSelect.setId_cliente(-1);   // igual a -1 quer dizer que não achou o cliente cadastrado.
            }  // end else


        } catch (SQLException ex) {   // 1º end TRY
            // System.out.println("catch falha na consulta!!!");
            //Logger.getLogger(UsuarioManageBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "catch falha na consulta!!!", "."));
            return null;
        } // end catch   




        ///////////////////////////////////////////////////////////



        try {
            if (clienteSelect.getId_cliente() != -1) {     // achou o cliente no BD, logo ele já existe .
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "O usuário já está cadastrado!!!", "SQLException"));
                return false;

            } else {    // igual a -1 quer dizer que não achou o cliente cadastrado.
                PreparedStatement stmt;

                stmt = DAOBD.conn.prepareStatement(
                        "INSERT INTO cliente (cpf_cnpj, rg_ie, nome, telefone, endereco, obs) VALUES (?,?,?,?,?,?)");

                stmt.setString(1, cliente.getCpf_cnpj());
                stmt.setString(2, cliente.getRg_ie());
                stmt.setString(3, cliente.getNome());
                stmt.setString(4, cliente.getTelefone());
                stmt.setString(5, cliente.getEndereco());
                stmt.setString(6, cliente.getObs());


                stmt.executeUpdate();
                stmt.close();
                //DAOBD.conn.close();

                return true;

            }  // end else


        } catch (SQLException ex) {   // 2º end TRY
            //Logger.getLogger(UsuarioManageBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "catch falha no cadastro!!!", "."));
            return null;
        } // end catch   

    } ////////// cadastarar

    public ArrayList<Cliente> listarClienteDAO() {

        Cliente ClienteSelect = null;
        listaClienteSelect.clear();
        try {  // editar de acor com a data
            PreparedStatement stmt = DAOBD.conn.prepareStatement(
                    "SELECT * from cliente Order By nome");
            // mostrar todos os carros que não estão no aluguel ou que já foram devolvido (com dt_fim).

            //stmt.setLong(1, id_carro);

            ResultSet rset = stmt.executeQuery();

            //carroSelect = new Carro();

            while (rset.next()) {  // antigo if

                ClienteSelect = new Cliente();
                ClienteSelect.setId_cliente(rset.getInt("id_cliente"));
                ClienteSelect.setCpf_cnpj(rset.getString("cpf_cnpj"));
                ClienteSelect.setRg_ie(rset.getString("rg_ie"));
                ClienteSelect.setNome(rset.getString("nome"));
                ClienteSelect.setTelefone(rset.getString("telefone"));
                ClienteSelect.setEndereco(rset.getString("endereco"));
                ClienteSelect.setObs(rset.getString("obs"));


                listaClienteSelect.add(ClienteSelect);

                //carroSelect = null;

            }   // end while 

            stmt.close();



        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "catch falha na listagem!!!", "."));
            return null;
        } // end catch   



        return listaClienteSelect;  // retorna a lsita de carros para o managebean.



    }
}  // class ClienteDAO
