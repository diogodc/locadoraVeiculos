package ManageBean;

// ALTER TABLE `mydb`.`aluguel` CHANGE COLUMN `dt_fim` `dt_fim` DATETIME NULL  ;
import dao.AluguelDAO;
import dao.CarroDAO;
import dao.PagamentoDAO;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Aluguel;
import modelo.Cliente;
import modelo.Pagamento;

@Named(value = "aluguelBean")
@RequestScoped
public class AluguelManageBean {

    private AluguelDAO aDAO = new AluguelDAO();
    private Aluguel aluguel = new Aluguel();
    private List<Aluguel> listaAluguelManage = null;
    private CarroDAO carroDAO = new CarroDAO();
    private int id_aluguel;
    private double vlr_aluguel;
    private PagamentoDAO pagDAO = new PagamentoDAO();
    private Pagamento pagamento = new Pagamento();
    
    @PostConstruct
    public void innit() {
        if (listaAluguelManage == null) {
            listaAluguelManage = aDAO.listarAluguelDAO();

        } // end if 

    } // innit

    ///////////// get e set
    public AluguelDAO getaDAO() {
        return aDAO;
    }

    public void setaDAO(AluguelDAO aDAO) {
        this.aDAO = aDAO;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setaluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public List<Aluguel> getListaAluguelManage() {
        return listaAluguelManage;
    }

    public void setListaAluguelManage(List<Aluguel> listaAluguelManage) {
        this.listaAluguelManage = listaAluguelManage;
    }

    public int getId_aluguel() {
        return id_aluguel;
    }

    public void setId_aluguel(int id_aluguel) {
        this.id_aluguel = id_aluguel;
    }

    public double getVlr_aluguel() {
        return vlr_aluguel;
    }

    public void setVlr_aluguel(double vlr_aluguel) {
        this.vlr_aluguel = vlr_aluguel;
    }

    public CarroDAO getCarroDAO() {
        return carroDAO;
    }

    public void setCarroDAO(CarroDAO carroDAO) {
        this.carroDAO = carroDAO;
    }

    public PagamentoDAO getPagDAO() {
        return pagDAO;
    }

    public void setPagDAO(PagamentoDAO pagDAO) {
        this.pagDAO = pagDAO;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    
    
    //////////////////////////////////////////////////
    // metodos:
    public String cadastrarAluguelManage() throws SQLException {

        long qtdDias = aluguel.calcularDiferencaDias(aluguel.getDt_inicio(), aluguel.getDt_devolucao());  // calculo da quantidade de dias da locação.

        double valorDiariaCarro = carroDAO.consultarValorDiaria(aluguel.getId_carro());  // consulta do valor da diaria o carro selecionado na locação.

        double valorAluguelCalculado = qtdDias * valorDiariaCarro;  // calculo do valro do aluguel.

        aluguel.setVlr_aluguel(valorAluguelCalculado);  // setando o valor do aluguel.

        pagamento.setVlr_pago(valorAluguelCalculado);
        
        ///////////////////////////////
            pagamento.setId_alugel(aDAO.cadastrarAluguelDAO(aluguel));  // retorna o id do novo aluguel cadastrado.
            
            if ( pagamento.getId_alugel() != 0) {  // se achar o id do aluguel quer dizer que deu certo, então pode contnuar
                
                pagDAO.cadastrarPagamentoDAO(pagamento);
                mensagemPositiva();
                return "cadastroDevolucao.xhtml";
            }
        return null;



    } // cadastrar Aluguel

    public String cadastrarDevolucaoManage() throws SQLException {

        aluguel.setId_aluguel(this.id_aluguel);
        aluguel.setVlr_aluguel(this.vlr_aluguel);

        double calculoValorTotal = aluguel.getVlr_aluguel() + aluguel.getVlr_taxa();

        aluguel.setVlr_total(calculoValorTotal);

        pagamento.setVlr_pago(aluguel.getVlr_taxa());
        pagamento.setId_alugel(aluguel.getId_aluguel());
        
        try {
            if (aDAO.cadastrarDevolucaoDAO(aluguel)) {  // passar o ID
                
                 pagDAO.cadastrarPagamentoDAO(pagamento);
                 
                return mensagemPositiva();
            } // end if
            else {
                return mensagemErro();
            } // end else

        } catch (Exception e) {
            return mensagemErro();
        }

    } // end cadastrar Devolução

    public void listaAluguelManage() {
        listaAluguelManage = aDAO.listarAluguelDAO();
        //return "listar"; // nome da página com a listagem !!!

    }

    public String escolherAluguel(Aluguel aluguelEscolhido) {
        this.id_aluguel = aluguelEscolhido.getId_aluguel();
        this.vlr_aluguel = aluguelEscolhido.getVlr_aluguel();

        return "cadastroDevolucao2.xhtml";

    }  // end escolherAluguel

    ////////////////////////////////////////////////////////////////////////////
    public String mensagemPositiva() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Operação realizada com sucesso!", ""));
        return "cadastroDevolucao.xhtml";
    }  // mensagem positiva

    public String mensagemErro() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Alguma coisa deu errado!", "Tente novamente"));
        return null;
    } // mensagem erro
    
} //class AluguelManageBean
