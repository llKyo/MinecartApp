package cl.kyo.minecartapp.dto;

public class Minecart {
    private int id;
    private String patente;
    private String modelo;
    private int numFlota;
    private int bateria;
    private int cabina;
    private int boveda;
    private int preBoveda;


    public Minecart() {
    }

    public Minecart(String patente, String modelo, int numFlota, int bateria, int cabina, int boveda, int preBoveda) {
        this.patente = patente;
        this.modelo = modelo;
        this.numFlota = numFlota;
        this.bateria = bateria;
        this.cabina = cabina;
        this.boveda = boveda;
        this.preBoveda = preBoveda;
    }

    @Override
    public String toString() {
        return "Minecart{" +
                "id=" + id +
                ", patente='" + patente + '\'' +
                ", modelo='" + modelo + '\'' +
                ", numFlota=" + numFlota +
                ", bateria=" + bateria +
                ", cabina=" + cabina +
                ", boveda=" + boveda +
                ", preBoveda=" + preBoveda +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getNumFlota() {
        return numFlota;
    }

    public void setNumFlota(int numFlota) {
        this.numFlota = numFlota;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public int getCabina() {
        return cabina;
    }

    public void setCabina(int cvabina) {
        this.cabina = cvabina;
    }

    public int getBoveda() {
        return boveda;
    }

    public void setBoveda(int boveda) {
        this.boveda = boveda;
    }

    public int getPreBoveda() {
        return preBoveda;
    }

    public void setPreBoveda(int preBoveda) {
        this.preBoveda = preBoveda;
    }
}
