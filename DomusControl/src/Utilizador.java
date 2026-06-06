import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Utilizador implements Serializable {
    private String nome;
    private Map<String, TipoUtilizador> casasAcesso;

    // Construtor omissão
    public Utilizador() {
        this.nome = "";
        this.casasAcesso = new HashMap<>();
    }

    // Construtor parametrizado
    public Utilizador(String nome, Map<String, TipoUtilizador> casasAcesso) {
        this.nome = nome;
        this.casasAcesso = new HashMap<>();
        for (Map.Entry<String, TipoUtilizador> entry : casasAcesso.entrySet()) {
            this.casasAcesso.put(entry.getKey(), entry.getValue().clone());
        }
    }

    // Construtor cópia
    public Utilizador(Utilizador u) {
        this.nome = u.getNome();
        this.casasAcesso = new HashMap<>();
        for (Map.Entry<String, TipoUtilizador> entry : u.getCasasAcesso().entrySet()) {
            this.casasAcesso.put(entry.getKey(), entry.getValue().clone());
        }
    }

    // Getters e Setters
    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Map<String, TipoUtilizador> getCasasAcesso() {
        Map<String, TipoUtilizador> copia = new HashMap<>();
        for (Map.Entry<String, TipoUtilizador> entry : this.casasAcesso.entrySet()) {
            copia.put(entry.getKey(), entry.getValue().clone());
        }
        return copia;
    }
    public void setCasasAcesso(Map<String, TipoUtilizador> casasAcesso) {
        this.casasAcesso = new HashMap<>();
        for (Map.Entry<String, TipoUtilizador> entry : casasAcesso.entrySet()) {
            this.casasAcesso.put(entry.getKey(), entry.getValue().clone());
        }
    }

    // toString
    @Override
    public String toString() {
        return "  - Nome: " + this.nome;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizador that = (Utilizador) o;
        return nome.equals(that.nome) && casasAcesso.equals(that.casasAcesso);
    }

    // clone
    @Override
    public Utilizador clone() {
        return new Utilizador(this);
    }

    
    //metodo que adiciona uma casa ao utilizador
    public void adicionarCasa(String morada, TipoUtilizador tipo) {
        this.casasAcesso.put(morada, tipo.clone());
    }
}
