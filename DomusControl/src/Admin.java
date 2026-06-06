public class Admin implements TipoUtilizador  {

    //construtor por omissão
    public Admin() {}

    @Override
    public String getDesignacao() {
        return "Administrador";
    }

    @Override
    public Admin clone(){
        return new Admin(); //como n temos variaveis, o clone é sempre igual a um novo Admin
    }

    @Override
    public String toString(){
        return "Admin";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true; //todos os Admins são iguais, pois não têm atributos
    }
    
}
    
    
    

