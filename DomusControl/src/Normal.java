public class Normal implements TipoUtilizador {

    public Normal() {}

    @Override
    public String getDesignacao() {
        return "Utilizador Normal";
    }

    @Override
    public Normal clone(){
        return new Normal(); //como n temos variaveis, o clone é sempre igual a um novo Normal
    }

    @Override
    public String toString(){
        return "Normal";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true; //todos os Normais são iguais, pois não têm atributos
    }
    
}
