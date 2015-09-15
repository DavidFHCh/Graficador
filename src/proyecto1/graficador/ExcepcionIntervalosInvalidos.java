package proyecto1.graficador;

/**
 * Clase para excepciones de Intervalos Invalidos.
 */
public class ExcepcionIntervalosInvalidos extends RuntimeException {

    /**
     * Constructor vacío.
     */
    public ExcepcionIntervalosInvalidos() {
        super();
    }

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExcepcionIntervalosInvalidos(String mensaje) {
        super(mensaje);
    }
}
