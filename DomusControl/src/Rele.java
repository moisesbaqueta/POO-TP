import java.io.Serializable;
public class Rele extends Dispositivo implements Serializable {

    private static final long serialVersionUID = 1L;

    public Rele(String id, String marca, String modelo, double consumoHora) {
        super(id, marca, modelo, consumoHora);
    }
    
    //construtor por omissão
    public Rele() {
        super();
    }

    //construtor de cópia
    public Rele(Rele r) {
        super(r);
    }
    //construtor parametrizado
    public Rele(String id, String marca, String modelo, double consumoHora, Estado estado) {
        super(id, marca, modelo, consumoHora);
        setEstado(estado);
    }

    //toString
    @Override
    public String toString() {
        return "[Rele] " + super.toString();
    }

    //clone
    public Rele clone() {
        return new Rele(this);
    }
    
    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }
}
