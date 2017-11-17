package modelo;

public class Carro {

    private long id_carro;  // PK
    private String placa;   // exemplo: HHH-1234
    private String descricao;  // nome/modelo
    private int ano;  // exemplo:  2014
    private int km_atual;  // quilometragem atual do veículo
    private double vlr_diaria;  // valor padrão da diária deste veículo.
    private String img;  // url da imagem.
    private String obs;  // observações gerais do veículo.

    /////////////////////////////////////////////////
    public long getId_carro() {
        return id_carro;
    }

    public void setId_carro(long id_carro) {
        this.id_carro = id_carro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getKm_atual() {
        return km_atual;
    }

    public void setKm_atual(int km_atual) {
        this.km_atual = km_atual;
    }

    public double getVlr_diaria() {
        return vlr_diaria;
    }

    public void setVlr_diaria(double vlr_diaria) {
        this.vlr_diaria = vlr_diaria;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public String toString() {
        return "Carro{" + "id_carro=" + id_carro + ", placa=" + placa + ", descricao=" + descricao + ", ano=" + ano + ", km_atual=" + km_atual + ", vlr_diaria=" + vlr_diaria + ", img=" + img + ", obs=" + obs + '}';
    }
} // class carro
