package proyecto1.graficador;

import java.util.*;


/**
* Clase que analiza las fichas y las prepapra para el Arbol Sintactico.
*/
public class Analizador{

	public final static int LETRA = 8;
	public final static int NUM = 7;	
	public final static int POW = 6;
	public final static int MULT_DIV = 5;
	public final static int SUM_RES = 4;
	public final static int PARE_CER = 3;
	public final static int PARE_AB = 2;
	public final static int FUNC = 1;

	private LinkedList<Ficha> salida;
	private LinkedList<Ficha> operadores;

	/**
	* Constructor sin parametros.
	*/ 
	public Analizador(){
		salida = new LinkedList<Ficha>();
		operadores = new LinkedList<Ficha>();
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
	public LinkedList<Ficha> analizar(LinkedList<Ficha> entrada1){
		LinkedList<Ficha> salida = new LinkedList<Ficha>();
		LinkedList<Ficha> entrada = (LinkedList<Ficha>)entrada1.clone();
		salida.clear();
		operadores.clear();
		Ficha f = new Ficha();
		int i = 0;
		while(entrada.size() != 0){
			f = entrada.remove();
			if(f.ficha == NUM || f.ficha == LETRA){
				salida.add(f);
			}
			if(f.ficha == FUNC){
				operadores.push(f);
			}
			if(f.ficha == MULT_DIV || f.ficha == SUM_RES || f.ficha == POW){
				if(f.ficha == SUM_RES){
					Ficha f1 = entrada1.get(-i);
					if(f1.ficha == NUM || f1.ficha == PARE_CER || f1.ficha == LETRA){
						while((operadores.size() != 0 &&(f.ficha <= operadores.peek().ficha && f.ficha != POW) || (f.ficha == POW && f.ficha < operadores.peek().ficha))){
							salida.add(operadores.pop());
						}
						operadores.push(f);
					}else{
						f.setFicha(0);
						operadores.push(f);
						if(entrada.peek().ficha == NUM || entrada.peek().ficha == LETRA){
							salida.add(entrada.remove());
							salida.add(operadores.pop());
							i++;
							continue;
						}
						if(entrada.peek().ficha == PARE_AB){
							
						}
					}
				}else{
					while((operadores.size() != 0 &&(f.ficha <= operadores.peek().ficha && f.ficha != POW) || (f.ficha == POW && f.ficha < operadores.peek().ficha))){
						salida.add(operadores.pop());
					}
					operadores.push(f);
				}
			}
			if(f.ficha == PARE_AB){
				operadores.push(f);
			}
			if(f.ficha == PARE_CER){
				operadores.pop();
				while(operadores.peek().ficha != PARE_AB && operadores.size() != 0)
					salida.add(operadores.pop());
				if(operadores.size() == 0)
						throw new ExcepcionCadenaInvalida("Parentesis desemparejados.");
				if(operadores.peek().ficha == PARE_AB){
					operadores.pop();
					if(operadores.peek().ficha == FUNC)
						salida.add(operadores.pop());
				}
			}
			i++;
		}
		while(operadores.size() != 0){
			if(operadores.peek().ficha == PARE_AB)
				throw new ExcepcionCadenaInvalida("Parentesis desemparejados.");
			salida.add(operadores.pop());
		}		
		return salida;
	}
}