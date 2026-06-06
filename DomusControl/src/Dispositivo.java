import java.io.Serializable;

public abstract class Dispositivo implements Serializable {

    private String id;
    private String marca;
    private String modelo;
    private double consumoHora;
    private Estado estado;
    private double energia;
    private double tempoUso;
    private int numeroAtivacoes;

    public enum Estado {
        LIGADO,
        DESLIGADO
    }

    //construtor por omissão
    public Dispositivo(){
        this.id = "";
        this.marca = "";
        this.modelo = "";
        this.consumoHora = 0.0;
        this.estado = Estado.DESLIGADO;
        this.energia = 0.0;
        this.tempoUso = 0.0;
        this.numeroAtivacoes = 0;
    }

    //construtor parametrizado
    public Dispositivo(String id, String marca, String modelo, double consumoHora){
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.consumoHora = consumoHora;
        this.estado = Estado.DESLIGADO;
        this.energia = 0.0;
        this.tempoUso = 0.0;
        this.numeroAtivacoes = 0;
    }

    //construtor de cópia
    public Dispositivo(Dispositivo d){
        this.id = d.getId();
        this.marca = d.getMarca();
        this.modelo = d.getModelo();
        this.consumoHora = d.getConsumoHora();
        this.estado = d.getEstado();
        this.energia = d.getEnergia();
        this.tempoUso = d.getTempoUso();
        this.numeroAtivacoes = d.getNumeroAtivacoes();
    }

    //getters e setters
    public Estado getEstado(){
        return this.estado;
    }
    public void setEstado(Estado estado){
        this.estado = estado;
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getMarca(){
        return this.marca;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public String getModelo(){
        return this.modelo;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public double getConsumoHora(){
        return this.consumoHora;
    }
    public void setConsumoHora(double consumoHora){
        this.consumoHora = consumoHora;
    }

    public double getEnergia(){
        return this.energia;
    }
    public void setEnergia(double energia){
        this.energia = energia;
    }
    public double getTempoUso(){
        return this.tempoUso;
    }
    public void setTempoUso(double tempoUso){
        this.tempoUso = tempoUso;
    }
    public int getNumeroAtivacoes(){
        return this.numeroAtivacoes;
    }
    public void setNumeroAtivacoes(int numeroAtivacoes){
        this.numeroAtivacoes = numeroAtivacoes;
    }
    public void ligar(){
        if(this.estado == Estado.DESLIGADO){
            this.numeroAtivacoes++;
        }
        this.estado = Estado.LIGADO;
    }

    public void desligar(){
        this.estado = Estado.DESLIGADO;
    }

    public void simularTempo(double horas){
        if(this.estado == Estado.LIGADO){
            this.energia += this.consumoHora * horas;
            this.tempoUso += horas;
        }
    }

    public double calcularConsumo(double horas){
        if(this.estado == Estado.LIGADO){
            return this.consumoHora * horas;
        } else {
            return 0.0;
        }
    }


    //método toString    
    @Override
    public String toString() {
        String s = "ID: " + this.id +
                   " | Estado: " + this.estado + 
                   " | Consumo por hora: " + this.consumoHora + " Wh" +
                   " | Energia Consumida: " + this.energia + " Wh";
                   
        if(!this.marca.isEmpty() && !this.modelo.isEmpty()) {
            s += " | Marca: " + this.marca + " | Modelo: " + this.modelo;
        }
        return s;
    }

    //metodo equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dispositivo that = (Dispositivo) o;

        if (Double.compare(that.consumoHora, consumoHora) != 0) return false;
        if (!id.equals(that.id)) return false;
        if (!marca.equals(that.marca)) return false;
        if (!modelo.equals(that.modelo)) return false;
        if (Double.compare(that.energia, energia) != 0) return false;
        return estado == that.estado;
    }

    //método clone
    public abstract Dispositivo clone();
        

    public boolean executarComando(String comando, double valor){
        if (comando.equalsIgnoreCase("LIGAR")) {
            this.ligar();
            return true;
        }
        if (comando.equalsIgnoreCase("DESLIGAR")) {
            this.desligar();
            return true;
        }
        return false;
    }
}
