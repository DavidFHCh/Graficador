package proyecto1.graficador;

/**
 * Clase para excepciones de cadenas Invalidas.
 */
public class ExcepcionOperacionNoSoportada extends RuntimeException {

    /**
     * Constructor vacío.
     */
    public ExcepcionOperacionNoSoportada() {
        super();
    }

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExcepcionOperacionNoSoportada(String mensaje) {
        super(mensaje);
    }
}