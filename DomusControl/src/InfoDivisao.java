
public class InfoDivisao {

    private String morada;
    private String divisao;
    private int quantidade;

    public InfoDivisao() {
        this.morada = "";
        this.divisao = "";
        this.quantidade = 0;
    }

    public InfoDivisao(String morada, String divisao, int quantidade) {
        this.morada = morada;
        this.divisao = divisao;
        this.quantidade = quantidade;
    }

    public InfoDivisao(InfoDivisao i) {
        this.morada = i.getMorada();
        this.divisao = i.getDivisao();
        this.quantidade = i.getQuantidade();
    }

    public String getMorada() {
        return this.morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getDivisao() {
        return this.divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Casa: " + this.morada + " | Divisão: " + this.divisao + " (" + this.quantidade + " dispositivos)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InfoDivisao i = (InfoDivisao) o;
        return quantidade == i.quantidade
                && morada.equals(i.morada)
                && divisao.equals(i.divisao);
    }

    @Override
    public InfoDivisao clone() {
        return new InfoDivisao(this);
    }
}
