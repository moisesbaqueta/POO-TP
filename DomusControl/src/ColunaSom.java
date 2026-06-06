
import java.io.Serializable;

public class ColunaSom extends Dispositivo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int volume;

    public ColunaSom(String id, String marca, String modelo, double consumoHora, int volume) {
        super(id, marca, modelo, consumoHora);
        this.volume = volume;
    }

    //getters e setters
    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    //construtor por omissão
    public ColunaSom() {
        super();
        this.volume = 0;
    }

    //construtor de cópia
    public ColunaSom(ColunaSom c) {
        super(c);
        this.volume = c.getVolume();
    }

    //construtor parametrizado
    public ColunaSom(String id, String marca, String modelo, double consumoHora, int volume, Estado estado) {
        super(id, marca, modelo, consumoHora);
        this.volume = volume;
        setEstado(estado);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        ColunaSom colunaSom = (ColunaSom) o;
        return volume == colunaSom.volume;
    }

    //toString
    @Override
    public String toString() {
        return "[ColunaSom] " + super.toString()
                + "| Volume: " + this.volume + "%";
    }

    //clone
    @Override
    public ColunaSom clone() {
        return new ColunaSom(this);
    }

    @Override
    public boolean executarComando(String comando, double valor) {
        if (comando.equalsIgnoreCase("AUMENTARVOLUME")) {
            int novoVolume = getVolume() + (int) valor;
            setVolume(Math.min(novoVolume, 100));
            return true;
        } else if (comando.equalsIgnoreCase("DIMINUIRVOLUME")) {
            int novoVolume = getVolume() - (int) valor;
            setVolume(Math.max(novoVolume, 0));
            return true;
        }
        return super.executarComando(comando, valor);
    }
}
