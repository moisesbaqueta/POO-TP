
import java.io.Serializable;

public class Cortina extends Dispositivo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int grauAbertura; //0-100%
    private String cor;

    public Cortina(String id, String marca, String modelo, double consumoHora, int grauAbertura, String cor) {
        super(id, marca, modelo, consumoHora);
        this.grauAbertura = grauAbertura;
        this.cor = cor;
    }

    //getters e setters
    public int getGrauAbertura() {
        return this.grauAbertura;
    }

    public void setGrauAbertura(int grauAbertura) {
        this.grauAbertura = grauAbertura;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    //construtor por omissão
    public Cortina() {
        super();
        this.grauAbertura = 0;
        this.cor = "-";
    }

    //construtor de cópia
    public Cortina(Cortina c) {
        super(c);
        this.grauAbertura = c.getGrauAbertura();
        this.cor = c.getCor();
    }

    //construtor parametrizado
    public Cortina(String id, String marca, String modelo, double consumoHora, int grauAbertura, String cor, Estado estado) {
        super(id, marca, modelo, consumoHora);
        this.grauAbertura = grauAbertura;
        this.cor = cor;
        setEstado(estado);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Cortina cortina = (Cortina) o;
        return grauAbertura == cortina.grauAbertura && 
               cor.equals(cortina.cor);
    }

    //toString
    @Override
    public String toString() {
        return "[Cortina] " + super.toString() + 
           " | Abertura: " + this.grauAbertura + "%" + 
           " | Cor: " + this.cor;
    }

    //clone
    public Cortina clone() {
        return new Cortina(this);
    }

    @Override
    public boolean executarComando(String comando, double valor) {
        if (comando.equalsIgnoreCase("GRAU")) {
            if (valor >= 0 && valor <= 100) {
                this.setGrauAbertura((int) valor);

                if (valor > 0) {
                    super.ligar();
                } else {
                    super.desligar();
                }

                return true;
            } else {
                return false;
            }
        }
        return super.executarComando(comando, valor);
    }
}
