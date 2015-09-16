/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.graficador;

/**
 *
 * @author davif
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
