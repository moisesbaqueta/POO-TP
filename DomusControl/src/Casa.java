
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Casa implements Serializable {

    private String morada;
    private String dono;
    private Map<String, Divisao> divisoes;

    // construtor por omissão
    public Casa() {
        this.morada = "";
        this.dono = "";
        this.divisoes = new HashMap<>();
    }

    // construtor parametrizado
    public Casa(String morada, String dono, Map<String, Divisao> divisoes) {
        this.morada = morada;
        this.dono = dono;
        this.divisoes = new HashMap<>();
        for (Map.Entry<String, Divisao> entry : divisoes.entrySet()) {
            this.divisoes.put(entry.getKey(), entry.getValue().clone());
        }
    }

    // construtor de cópia
    public Casa(Casa c) {
        this.morada = c.getMorada();
        this.dono = c.getDono();
        this.divisoes = c.getDivisoes();
    }

    // getters e setters
    public String getMorada() {
        return this.morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getDono() {
        return this.dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public Map<String, Divisao> getDivisoes() {
        Map<String, Divisao> copia = new HashMap<>();
        for (Map.Entry<String, Divisao> entry : this.divisoes.entrySet()) {
            copia.put(entry.getKey(), entry.getValue().clone());
        }
        return copia;
    }

    public void setDivisoes(Map<String, Divisao> divisoes) {
        this.divisoes = new HashMap<>();
        if (divisoes != null) {
            for (Map.Entry<String, Divisao> entry : divisoes.entrySet()) {
                this.divisoes.put(entry.getKey(), entry.getValue().clone());
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
        Casa casa = (Casa) o;
        return morada.equals(casa.morada)
                && dono.equals(casa.dono)
                && divisoes.equals(casa.divisoes);
    }

    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n  [Casa] Morada: ").append(morada).append(" | Dono: ").append(dono);

        if (divisoes.isEmpty()) {
            sb.append("\n     -> (Sem divisoes registadas)");
        } else {
            for (Divisao d : divisoes.values()) {
                sb.append("\n     -> ").append(d.toString());
            }
        }
        return sb.toString();
    }

    // clone
    public Casa clone() {
        return new Casa(this);
    }

    // metodo que adiciona uma divisão à casa
    public void addDivisao(Divisao d) {
        this.divisoes.put(d.getNome(), d.clone());
    }

    // metodo que remove uma divisão da casa pelo nome
    public void removeDivisao(String nome) {
        this.divisoes.remove(nome);
    }

    // metodo que verifica se a divisao existe
    public boolean existeDivisao(String nome) {
        return this.divisoes.containsKey(nome);
    }

    // metodo que adiciona um dispositivo a uma divisão da casa
    // fizemos com boolean p poder retornar false caso a divisão nao exista, e true
    // caso exista e o dispositivo seja adicionado
    public boolean adicionarDispositivoNaDivisao(String nomeDivisao, Dispositivo d) {
        if (this.divisoes.containsKey(nomeDivisao)) {
            return this.divisoes.get(nomeDivisao).addDispositivo(d);
        }
        return false;
    }

    // Método para enviar um comando diretamente à divisão original sem usar clones
    public boolean interagirDispositivoNaDivisao(String nomeDivisao, String idDispositivo, String comando,
            double valor) {
        Divisao d = this.divisoes.get(nomeDivisao); // Vai buscar a original
        if (d != null) {
            return d.interagirComDispositivo(idDispositivo, comando, valor);
        }
        return false;
    }

    public boolean removerDispositivoNaDivisao(String nomeDivisao, String idDispositivo) {
        Divisao d = this.divisoes.get(nomeDivisao);
        if (d != null) {
            return d.removeDispositivo(idDispositivo);
        }
        return false;
    }

    public boolean existeDispositivo(String idDispositivo) {
        for (Divisao d : this.divisoes.values()) {
            if (d.getDispositivos().containsKey(idDispositivo)) {
                return true;
            }
        }
        return false;
    }

    public void simularTempo(double horas) {
        for (Divisao d : this.divisoes.values()) {
            d.simularTempo(horas);
        }
    }

    public double consumoTotalCasa() {
        double total = 0.0;
        for (Divisao d : this.divisoes.values()) {
            total += d.consumoTotalDivisao();
        }
        return total;
    }

    public List<Dispositivo> todosDispositivos() {
        List<Dispositivo> d = new ArrayList<>();
        for (Divisao div : this.divisoes.values()) {
            d.addAll(div.getDispositivos().values());
        }
        return d;
    }

    public List<Dispositivo> top3Tempo() {
        List<Dispositivo> disps = this.todosDispositivos();

        disps.sort((a, b) -> Double.compare(b.getTempoUso(), a.getTempoUso()));

        List<Dispositivo> top3 = new ArrayList<>();
        for (int i = 0; i < Math.min(3, disps.size()); i++) {
            top3.add(disps.get(i).clone());
        }
        return top3;
    }

    public List<Dispositivo> top3Activacoes() {
        List<Dispositivo> todos = this.todosDispositivos();

        todos.sort((a, b) -> Integer.compare(b.getNumeroAtivacoes(), a.getNumeroAtivacoes()));

        List<Dispositivo> top3 = new ArrayList<>();
        for (int i = 0; i < Math.min(3, todos.size()); i++) {
            top3.add(todos.get(i).clone());
        }
        return top3;
    }

    public void sairDeCasa() { // desliga tudo
        for (Divisao d : this.divisoes.values()) {
            d.desligarDispositivos();
        }
    }

    public void jantarComAmigos() { // ligar luzes da sala e desliga o resto
        for (Divisao d : this.divisoes.values()) {
            String nome = d.getNome().toLowerCase();
            if (nome.contains("sala")) {
                d.ligarLuzesECortinas();
            } else {
                d.desligarDispositivos();
            }
        }
    }

    public void deitar() { // desliga tudo
        for (Divisao d : this.divisoes.values()) {
            d.desligarDispositivos();
        }
    }

    public void acordar() { // liga as luzes do quarto e a cortina abre
        for (Divisao d : this.divisoes.values()) {
            String nome = d.getNome().toLowerCase();
            if (nome.contains("quarto")) {
                d.ligarLuzesECortinas();
            } else {
                d.desligarDispositivos();
            }
        }
    }
}
