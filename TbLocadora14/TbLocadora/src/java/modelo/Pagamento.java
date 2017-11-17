
package modelo;

public class Pagamento {
private long id_pagamento;   // PK
private long id_alugel;      // FK
private int forma_pg;        // código da forma  de pagamento.
private double vlr_pago;     // valor pago desta forma de pagamento.

    /////////////////////////////////////////////////
    public long getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(long id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public long getId_alugel() {
        return id_alugel;
    }

    public void setId_alugel(long id_alugel) {
        this.id_alugel = id_alugel;
    }

    public int getForma_pg() {
        return forma_pg;
    }

    public void setForma_pg(int forma_pg) {
        this.forma_pg = forma_pg;
    }

    public double getVlr_pago() {
        return vlr_pago;
    }

    public void setVlr_pago(double vlr_pago) {
        this.vlr_pago = vlr_pago;
    }

    @Override
    public String toString() {
        return "Pagamento{" + "id_pagamento=" + id_pagamento + ", id_alugel=" + id_alugel + ", forma_pg=" + forma_pg + ", vlr_pago=" + vlr_pago + '}';
    }







    
    
    
    
    
    
    
    

}  // class Pagamento
