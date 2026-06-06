
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomusControl implements Serializable {

    private Map<String, Casa> casas;
    private Map<String, Utilizador> utilizadores;

    //construtor por omissão
    public DomusControl() {
        this.casas = new HashMap<>();
        this.utilizadores = new HashMap<>();
    }

    //construtor parametrizado
    public DomusControl(Map<String, Casa> casas, Map<String, Utilizador> utilizadores) {
        this.casas = new HashMap<>();
        for (Map.Entry<String, Casa> entry : casas.entrySet()) {
            this.casas.put(entry.getKey(), entry.getValue().clone());
        }
        this.utilizadores = new HashMap<>();
        for (Map.Entry<String, Utilizador> entry : utilizadores.entrySet()) {
            this.utilizadores.put(entry.getKey(), entry.getValue().clone());
        }
    }

    //construtor de cópia
    public DomusControl(DomusControl dc) {
        this.casas = new HashMap<>();
        for (Map.Entry<String, Casa> entry : dc.getCasas().entrySet()) {
            this.casas.put(entry.getKey(), entry.getValue().clone());
        }
        this.utilizadores = new HashMap<>();
        for (Map.Entry<String, Utilizador> entry : dc.getUtilizadores().entrySet()) {
            this.utilizadores.put(entry.getKey(), entry.getValue().clone());
        }
    }

    //getters e setters
    public Map<String, Casa> getCasas() {
        Map<String, Casa> copia = new HashMap<>();
        for (Map.Entry<String, Casa> entry : this.casas.entrySet()) {
            copia.put(entry.getKey(), entry.getValue().clone());
        }
        return copia;
    }

    public void setCasas(Map<String, Casa> casas) {
        this.casas = new HashMap<>();
        for (Map.Entry<String, Casa> entry : casas.entrySet()) {
            this.casas.put(entry.getKey(), entry.getValue().clone());
        }
    }

    public Map<String, Utilizador> getUtilizadores() {
        Map<String, Utilizador> copia = new HashMap<>();
        for (Map.Entry<String, Utilizador> entry : this.utilizadores.entrySet()) {
            copia.put(entry.getKey(), entry.getValue().clone());
        }
        return copia;
    }

    public void setUtilizadores(Map<String, Utilizador> utilizadores) {
        this.utilizadores = new HashMap<>();
        for (Map.Entry<String, Utilizador> entry : utilizadores.entrySet()) {
            this.utilizadores.put(entry.getKey(), entry.getValue().clone());
        }
    }

    //toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n LISTA DE UTILIZADORES");
        if (utilizadores.isEmpty()) {
            sb.append("\n  (Nenhum utilizador registado)");
        }
        for (Utilizador u : utilizadores.values()) {
            sb.append("\n").append(u.toString());
        }

        sb.append("\n\n LISTA DE CASAS");
        if (casas.isEmpty()) {
            sb.append("\n  (Nenhuma casa registada)");
        }
        for (Casa c : casas.values()) {
            sb.append(c.toString());
        }

        return sb.toString();
    }

    //clone
    @Override
    public DomusControl clone() {
        return new DomusControl(this);
    }

    //metodo que adiciona um utilizador ao sistema
    public void adicionarUtilizador(Utilizador u) {
        this.utilizadores.put(u.getNome(), u.clone());
    }

    public boolean removerUtilizador(String nome) {
        if (this.utilizadores.containsKey(nome)) {
            this.utilizadores.remove(nome);
            return true;
        }
        return false;
    }

    //metodo que adiciona uma casa ao sistema
    public void adicionarCasa(Casa c) {
        this.casas.put(c.getMorada(), c.clone());
    }

    //metodo que adiciona um dispositivo a uma divisao de uma casa
    //ou seja, o DomusControl manda colocar o dispositivo X em tal divisao se a tiver e quem verifica se tem ou nao é 
    //a casa, com a funçao adicionarDispositivoNaDivisao.
    //fizemos cmo boolean p poder retornar false caso a casa ou a divisão nao exista, e true caso exista e o dispositivo seja adicionado
    public boolean addDispDiv(String morada, String nomeDivisao, Dispositivo d) {
        Casa c = this.casas.get(morada);
        if (c != null) {
            return c.adicionarDispositivoNaDivisao(nomeDivisao, d);
        }
        return false;
    }

    //metodo que adiciona uma divisao a uma casa
    public boolean adicionarDivisaoCasa(String morada, Divisao d) {
        Casa c = this.casas.get(morada);
        if (c != null) {
            if (c.existeDivisao(d.getNome())) {
                return false;
            }
            c.addDivisao(d);
            return true;
        }
        return false;
    }

    public boolean removerDivisaoCasa(String morada, String nomeDivisao) {
        Casa c = this.casas.get(morada);
        if (c != null && c.existeDivisao(nomeDivisao)) {
            c.removeDivisao(nomeDivisao);
            return true;
        }
        return false;
    }

    public boolean removerDispositivo(String morada, String nomeDivisao, String idDispositivo) {
        Casa c = this.casas.get(morada);
        if (c != null) {
            return c.removerDispositivoNaDivisao(nomeDivisao, idDispositivo);
        }
        return false;
    }

    public boolean enviarComando(String morada, String nomeDivisao, String idDispositivo, String comando, double valor) {
        Casa c = this.casas.get(morada);
        if (c != null) {
            return c.interagirDispositivoNaDivisao(nomeDivisao, idDispositivo, comando, valor);
        }
        return false;
    }

    public void avancarTempo(double horas) {
        for (Casa c : this.casas.values()) {
            c.simularTempo(horas);
        }
    }

    public Casa casaQueMaisConsome() {
        if (this.casas.isEmpty()) {
            return null;
        }

        Casa maisConsumidora = null;
        double maxConsumo = -1.0;

        for (Casa c : this.casas.values()) {
            double consumo = c.consumoTotalCasa();
            if (consumo > maxConsumo) {
                maxConsumo = consumo;
                maisConsumidora = c.clone();
            }
        }
        return maisConsumidora;
    }

    public List<Dispositivo> top3TempoCasa(String morada) {
        Casa c = this.casas.get(morada);
        if (c != null) {
            return c.top3Tempo();
        }
        return null;
    }

    public List<Dispositivo> top3ActivacoesCasa(String morada) {
        Casa c = this.casas.get(morada);
        if (c != null) {
            return c.top3Activacoes();
        }
        return null;
    }

    public List<String> top3Divisoes() {
        List<InfoDivisao> divisoes = new ArrayList<>();

        for (Casa c : this.casas.values()) {
            for (Divisao d : c.getDivisoes().values()) {
                int quantidade = d.getDispositivos().size();
                divisoes.add(new InfoDivisao(c.getMorada(), d.getNome(), quantidade));
            }
        }

        divisoes.sort((a, b) -> Integer.compare(b.getQuantidade(), a.getQuantidade()));

        List<String> top3 = new ArrayList<>();
        for (int i = 0; i < Math.min(3, divisoes.size()); i++) {
            InfoDivisao info = divisoes.get(i);
            top3.add("Casa: " + info.getMorada() + " | Divisão: " + info.getDivisao() + " (" + info.getQuantidade() + " disp.)");
        }
        return top3;
    }

    public void gravaBinario(String fileName) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public DomusControl lerBinario(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        DomusControl a = (DomusControl) ois.readObject();
        ois.close();
        return a;
    }

    public boolean cenarioSairDeCasa(String morada) {
        Casa c = this.casas.get(morada);
        if (c != null) {
            c.sairDeCasa();
            return true;
        }
        return false;
    }

    public boolean cenarioJantarComAmigos(String morada) {
        Casa c = this.casas.get(morada);
        if (c != null) {
            c.jantarComAmigos();
            return true;
        }
        return false;
    }

    public boolean cenarioDeitar(String morada) {
        Casa c = this.casas.get(morada);
        if (c != null) {
            c.deitar();
            return true;
        }
        return false;
    }

    public boolean cenarioAcordar(String morada) {
        Casa c = this.casas.get(morada);
        if (c != null) {
            c.acordar();
            return true;
        }
        return false;
    }
}
