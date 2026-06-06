import java.io.Serializable;

public interface TipoUtilizador extends Serializable {
    String getDesignacao();
    TipoUtilizador clone();

    
}
