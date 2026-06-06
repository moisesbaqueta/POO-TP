
import java.io.Serializable;

public class Lampada extends Dispositivo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int corLuz; //vai de 2700K ate 4000K
    private int intensidadeL;

    public Lampada(String id, String marca, String modelo, double consumoHora, int corLuz, int intensidadeL) {
        super(id, marca, modelo, consumoHora);
        this.corLuz = corLuz;
        this.intensidadeL = intensidadeL;
    }

    //getters e setters
    public int getCorLuz() {
        return this.corLuz;
    }

    public void setCorLuz(int corLuz) {
        this.corLuz = corLuz;
    }

    public int getIntensidadeL() {
        return this.intensidadeL;
    }

    public void setIntensidade(int intensidadeL) {
        this.intensidadeL = intensidadeL;
    }

    //construtor por omissao
    public Lampada() {
        super();
        this.corLuz = 2700;
        this.intensidadeL = 0;
    }

    //construtor de cópia
    public Lampada(Lampada l) {
        super(l);
        this.corLuz = l.getCorLuz();
        this.intensidadeL = l.getIntensidadeL();
    }

    //construtor parametrizado
    public Lampada(String id, String marca, String modelo, double consumoHora, int corLuz, int intensidadeL, Estado estado) {
        super(id, marca, modelo, consumoHora);
        this.corLuz = corLuz;
        this.intensidadeL = intensidadeL;
        setEstado(estado);
    }

    //toString
    @Override
    public String toString() {
        return "[Lampada] " + super.toString()
                + "| Cor: " + this.corLuz + "K"
                + "| Intensidade: " + this.intensidadeL + "%";
    }

    //metodo clone
    @Override
    public Lampada clone() {
        return new Lampada(this);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Lampada lampada = (Lampada) o;
        return corLuz == lampada.corLuz
                && intensidadeL == lampada.intensidadeL;
    }

    //metodo executar comando
    @Override
    public boolean executarComando(String comando, double valor) {
        if (comando.equalsIgnoreCase("INTENSIDADE")) {
            if (valor >= 0 && valor <= 100) {
                this.setIntensidade((int) valor);
                return true;
            } else {
                return false;
            }
        }
        if (comando.equalsIgnoreCase("COR")) {
            if (valor >= 2700 && valor <= 4000) {
                this.setCorLuz((int) valor);
                return true;
            } else {
                return false;
            }
        }

        return super.executarComando(comando, valor);
    }
}
