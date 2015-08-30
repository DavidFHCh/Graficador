package proyecto1.graficador;

/**
 * Clase para excepciones de cadenas Invalidas.
 */
public class ExcepcionCadenaInvalida extends Exception {

    /**
     * Constructor vacío.
     */
    public ExcepcionCadenaInvalida() {
        super();
    }

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExcepcionCadenaInvalida(String mensaje) {
        super(mensaje + " no es un caracter soportado.");
    }
}
