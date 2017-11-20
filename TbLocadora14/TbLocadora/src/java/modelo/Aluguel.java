package modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Aluguel {

    private int id_aluguel;    // PK
    private int id_cliente;    // FK
    private int id_carro;      // FK
    private Date dt_inicio = new Date();     // data que foi efetuada a locação.
    private Date dt_devolucao = new Date();  // data prevista para a devolução.
    private Date dt_fim = new Date();        // data que realmente foi devolvido.
    private double vlr_aluguel; // valor estipulado para o aluguel no ato da locação.
    private double vlr_taxa;    // valor adicionais de avarias que podem ter no ato da devolução.
    private double vlr_total;   // valor final do aluguel (valor_aluguel + vlr_taxa).

   
    public int getId_aluguel() {
        return id_aluguel;
    }

    public void setId_aluguel(int id_aluguel) {
        this.id_aluguel = id_aluguel;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_carro() {
        return id_carro;
    }

    public void setId_carro(int id_carro) {
        this.id_carro = id_carro;
    }

    public Date getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(Date dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public Date getDt_devolucao() {
        return dt_devolucao;
    }

    public void setDt_devolucao(Date dt_devolucao) {
        this.dt_devolucao = dt_devolucao;
    }

    public Date getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(Date dt_fim) {
        this.dt_fim = dt_fim;
    }

    public double getVlr_aluguel() {
        return vlr_aluguel;
    }

    public void setVlr_aluguel(double vlr_aluguel) {
        this.vlr_aluguel = vlr_aluguel;
    }

    public double getVlr_taxa() {
        return vlr_taxa;
    }

    public void setVlr_taxa(double vlr_taxa) {
        this.vlr_taxa = vlr_taxa;
    }

    public double getVlr_total() {
        return vlr_total;
    }

    public void setVlr_total(double vlr_total) {
        this.vlr_total = vlr_total;
    }

    @Override
    public String toString() {
        return "Aluguel{" + "id_aluguel=" + id_aluguel + ", id_cliente=" + id_cliente + ", id_carro=" + id_carro + ", dt_inicio=" + dt_inicio + ", dt_devolucao=" + dt_devolucao + ", dt_fim=" + dt_fim + ", vlr_aluguel=" + vlr_aluguel + ", vlr_taxa=" + vlr_taxa + ", vlr_total=" + vlr_total + '}';
    }
////////////////////////////////////////////////////////

    public long calcularDiferencaDias(Date d1, Date d2) {
        // Dando um exemplo: quantos dias se passam desde 07/09/1822 até 05/06/2006?  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);

        //d1 = df.(d1);
        //d2 = df.parse(d2);

        long dataTemp = (d2.getTime() - d1.getTime()) + 3600000; // 1 hora para compensar horário de verão  
        long diferenca = (dataTemp / 86400000L); // passaram-se 67111 dias  

        return diferenca;

    }  // end calcularDiferencaDias
} //  end class Aluguel 
