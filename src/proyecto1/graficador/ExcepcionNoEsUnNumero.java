package proyecto1.graficador;

/**
 * Clase para excepciones de cadenas Invalidas.
 */
public class ExcepcionNoEsUnNumero extends RuntimeException {

    /**
     * Constructor vacío.
     */
    public ExcepcionNoEsUnNumero() {
        super();
    }

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExcepcionNoEsUnNumero(String mensaje) {
        super(mensaje);
    }
}
