
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Divisao implements Serializable {

    private String nome;
    private Map<String, Dispositivo> dispositivos; // o map vai guardar o Id do dispositivo e o próprio
    // dispositivo<chave, valor>

    // construtor por omissão
    public Divisao() {
        this.nome = "";
        this.dispositivos = new HashMap<>();
    }

    // construtor parametrizado
    public Divisao(String nome, Map<String, Dispositivo> dispositivos) {
        this.nome = nome;
        this.dispositivos = new HashMap<>();
        if (dispositivos != null) {
            for (Map.Entry<String, Dispositivo> entry : dispositivos.entrySet()) {
                this.dispositivos.put(entry.getKey(), entry.getValue().clone());
            }
        }
    }

    // construtor de cópia
    public Divisao(Divisao d) {
        this.nome = d.getNome();
        this.dispositivos = d.getDispositivos();
    }

    // getters e setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, Dispositivo> getDispositivos() {
        Map<String, Dispositivo> copia = new HashMap<>();
        for (Map.Entry<String, Dispositivo> entry : this.dispositivos.entrySet()) {
            copia.put(entry.getKey(), entry.getValue().clone());
        }
        return copia;
    }

    public void setDispositivos(Map<String, Dispositivo> dispositivos) {
        this.dispositivos = new HashMap<>();
        if (dispositivos != null) {
            for (Map.Entry<String, Dispositivo> entry : dispositivos.entrySet()) {
                this.dispositivos.put(entry.getKey(), entry.getValue().clone());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Divisao divisao = (Divisao) o;
        return nome.equals(divisao.nome)
                && dispositivos.equals(divisao.dispositivos);
    }

    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Divisao: ").append(nome);

        if (dispositivos.isEmpty()) {
            sb.append("\n        (Sem dispositivos)");
        } else {
            for (Dispositivo disp : dispositivos.values()) {
                sb.append("\n        - ").append(disp.toString());
            }
        }
        return sb.toString();
    }

    // clone
    public Divisao clone() {
        return new Divisao(this);
    }

    // metodo que adiciona um dispositivo à divisão
    public boolean addDispositivo(Dispositivo d) {
        if (this.dispositivos.containsKey(d.getId())) {
            return false;
        }

        this.dispositivos.put(d.getId(), d.clone());
        return true;
    }

    // metodo que remove um dispositivo da divisao
    public boolean removeDispositivo(String idDispositivo) {
        if (this.dispositivos.containsKey(idDispositivo)) {
            this.dispositivos.remove(idDispositivo);
            return true;
        }
        return false;
    }

    public boolean interagirComDispositivo(String idDispositivo, String comando, double valor) {
        Dispositivo d = this.dispositivos.get(idDispositivo);

        if (d != null) {
            return d.executarComando(comando, valor);
        }
        return false;
    }

    public void simularTempo(double horas) {
        for (Dispositivo d : this.dispositivos.values()) {
            d.simularTempo(horas);
        }
    }

    public double consumoTotalDivisao() {
        double total = 0.0;
        for (Dispositivo d : this.dispositivos.values()) {
            total += d.getEnergia();
        }
        return total;
    }

    public void desligarDispositivos() {
        for (Dispositivo d : this.dispositivos.values()) {
            d.desligar();
            if (d instanceof Cortina) {
                ((Cortina) d).setGrauAbertura(0);
            } else if (d instanceof PortaoGaragem) {
                ((PortaoGaragem) d).setGrauAbertura(0);
            }
        }
    }

    public void ligarDispositivos() {
        for (Dispositivo d : this.dispositivos.values()) {
            d.ligar();
            if (d instanceof Cortina) {
                ((Cortina) d).setGrauAbertura(100);
            } else if (d instanceof PortaoGaragem) {
                ((PortaoGaragem) d).setGrauAbertura(100);
            }
        }
    }

    public void ligarLuzesECortinas() {
        for (Dispositivo d : this.dispositivos.values()) {
            if (d instanceof Lampada || d instanceof Cortina) {
                d.ligar();
                if (d instanceof Cortina) {
                    ((Cortina) d).setGrauAbertura(100);
                }
            }
        }
    }
}
