package proyecto1.graficador;

import java.util.*;


/**
* Clase que analiza las fichas y las prepapra para el Arbol Sintactico.
*/
public class Analizador{

	public static int LETRA = 8;
	public static int NUM = 7;	
	public static int POW = 6;
	public static int MULT_DIV = 5;
	public static int SUM_RES = 4;
	public static int PARE_CER = 3;
	public static int PARE_AB = 2;
	public static int FUNC = 1;

	private LinkedList<Ficha> salida;
	private Stack<Ficha> s;

	/**
	* Constructor sin parametros.
	*/ 
	public Analizador(){
		salida = new LinkedList<Ficha>();
		s = null;
	}

	/**
	* Metodo para obtener la Lista Salida.
	* @return LinkedList<Ficha> llamada Salida.
	*/
	public LinkedList<Ficha> getSalida(){
		return this.salida;
	}

	/**
     * Metodo que analiza las Fichas, con ayuda de la gramatica.
     * @param LinkedLista<Ficha> llamada entrada, es la lista que da despues de usar el Fichador.
     */
	public void analizar(LinkedList<Ficha> entrada){
		this.s = new Stack<Ficha>();
		Ficha f = new Ficha();
		for(int i = 0; i < entrada.size();i++){
			f = entrada.pop();
			if(f.ficha == 8 || f.ficha == 7){ // si la ficha es numero o literal.
				this.salida.add(f);
				continue;
			}
			if(f.ficha == 1 || f.ficha == 2){// si la ficha es un parentesis que abre o una funcion.
				this.s.push(f);
				continue;
			}
			if(f.ficha == 3){ // si la ficha es un parentesis que cierra.
				if(!this.s.empty()){
					while(this.s.peek().ficha > 2){
						if(this.s.peek().ficha != 2 || this.s.peek().ficha != 3)
							this.salida.add(this.s.pop());
						if(this.s.empty())
							throw new ExcepcionCadenaInvalida("Parentesis desemparejados.");
					}
					if(this.s.peek().ficha == 1)
						this.salida.add(this.s.pop());
					else
						this.s.pop();
				}
				continue;
			}
			if(f.ficha == 4){
				if(this.s.empty() || this.s.peek().ficha == 2 ||  this.s.peek().ficha == 5){
					this.s.push(f);
					this.salida.add(entrada.pop());
					this.salida.add(this.s.pop());
					i++;
					continue;
				}
			}
			if(f.ficha > 3){ // si es cualquier operacion.
				if(!this.s.empty()){
					if((f.ficha <= this.s.peek().ficha && f.ficha != 6) || f.ficha < this.s.peek().ficha){
						this.salida.add(this.s.pop());
						this.s.push(f);
					}else{
						this.s.push(f);
					}
				}else{
					this.s.push(f);
				}
			}
		}
		while(!this.s.empty())
			this.salida.add(this.s.pop());
	}
}