
import java.io.Serializable;

public class PortaoGaragem extends Dispositivo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int grauAbertura; //0-100%

    public PortaoGaragem(String id, String marca, String modelo, double consumoHora, int grauAbertura) {
        super(id, marca, modelo, consumoHora);
        this.grauAbertura = grauAbertura;
    }

    //getters e setters
    public int getGrauAbertura() {
        return this.grauAbertura;
    }

    public void setGrauAbertura(int grauAbertura) {
        this.grauAbertura = grauAbertura;
    }

    //construtor por omissão
    public PortaoGaragem() {
        super();
        this.grauAbertura = 0;
    }

    //construtor de cópia
    public PortaoGaragem(PortaoGaragem p) {
        super(p);
        this.grauAbertura = p.getGrauAbertura();
    }

    //construtor parametrizado
    public PortaoGaragem(String id, String marca, String modelo, double consumoHora, int grauAbertura, Estado estado) {
        super(id, marca, modelo, consumoHora);
        this.grauAbertura = grauAbertura;
        setEstado(estado);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        PortaoGaragem portaoGaragem = (PortaoGaragem) o;
        return grauAbertura == portaoGaragem.grauAbertura;
    }

    //toString
    @Override
    public String toString() {
        return "[Portão de Garagem] " + super.toString() + 
           " | Abertura: " + this.grauAbertura + "%";

    }

    //clone
    public PortaoGaragem clone() {
        return new PortaoGaragem(this);
    }

    @Override
    public boolean executarComando(String comando, double valor) {
        if (comando.equalsIgnoreCase("GRAU")) {
            if (valor >= 0 && valor <= 100) {
                this.setGrauAbertura((int) valor);

                if (valor > 0) {
                    this.ligar();
                } else {
                    this.desligar();
                }

                return true;
            } else {
                return false;
            }

        }
        return super.executarComando(comando, valor);
    }
}
