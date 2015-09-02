package proyecto1.graficador;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//import java.lang.String;

/**
 * Clase que analiza sintacticamente las strings recibidas.
 */
public class Fichador{

	private LinkedList<TipoFicha> tiposFichas; // Lista para la gramatica.
	private LinkedList<Ficha> fichas; // Lista de fichas.


	/**
	* Clase interna privada que nos va a ayudar a separar la cadena recibida
	* en "tokens"
	*/
	private class TipoFicha{

		public final Pattern regex;// Un objeto de tipo pattern.
		public final int ficha;

		public TipoFicha(Pattern regex, int ficha){
			this.regex = regex;
			this.ficha = ficha;
		}
	}

	/**
	* Constructor que crea una lista con los tipos de fichas que puede haber y la lista de Fichas.
	*/
	public Fichador() {
  		tiposFichas = new LinkedList<TipoFicha>();
  		fichas = new LinkedList<Ficha>();
  		agregaGramatica();
	}

	public LinkedList<Ficha> getFichas(){
		return this.fichas;
	}

	/**
  	 *	Separa la String de entrada en Fichas.
  	 * @param la String de entrada.
  	 */ 
  	public void hazFichas(String entrada){
  		String entradaCopia = new String(entrada);
  		fichas.clear();
  		while(!entradaCopia.equals("")){
  			boolean encontrado = false;
  			for(TipoFicha tf : tiposFichas){
  				Matcher m = tf.regex.matcher(entradaCopia);
  				if(m.find()){
  					encontrado = true;
  					String ficha1 = m.group().trim();
  					fichas.add(new Ficha(tf.ficha,ficha1));
  					entradaCopia = m.replaceFirst("");
  					break; 
  				}
  			}
  			if(!encontrado) 
  				throw new ExcepcionCadenaInvalida("Caracter no esperado. " + entradaCopia);
  		}
  	}

	/**
	* Agregar la informacion a Lista de tipos de ficha, de acuerdo a nuestra Gramatica.
	*/
	private void agrega(String regex, int ficha) {
		TipoFicha tf = new TipoFicha(Pattern.compile("^("+regex+")"), ficha);
		tiposFichas.add(tf);
  	}

  	private void agregaGramatica(){
  		agrega("sin|cos|tan|cot|sec|csc|sqrt", 1);
  		agrega("\\(", 2);
  		agrega("\\)", 3);
  		agrega("[+-]", 4);
  		agrega("[*/]", 5);
  		agrega("\\^", 6);
  		agrega("[0-9]+", 7);
  		agrega("^[a-z]", 8);
  	}

}