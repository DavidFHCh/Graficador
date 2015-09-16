package proyecto1.graficador;

import java.util.*;


/**
* Clase que analiza las fichas y las prepapra para el Arbol Sintactico.
*/
public class Analizador extends Fichador{

	public final static int LETRA = 8;
	public final static int NUM = 7;	
	public final static int POW = 6;
	public final static int MULT_DIV = 5;
	public final static int SUM_RES = 4;
	public final static int PARE_CER = 3;
	public final static int PARE_AB = 2;
	public final static int FUNC = 1;

	private LinkedList<Ficha> salida1;

	/**
	* Constructor sin parametros.
	*/ 
	public Analizador(){
		super();
		salida1 = new LinkedList<Ficha>();
	}

	/**
	* Metodo para obtener la Lista Salida.
	* @return LinkedList -  llamada Salida.
	*/
	public LinkedList<Ficha> getSalida(){
		return this.salida1;
	}

	public void setSalida(LinkedList<Ficha> sal){
		this.salida1 = sal;
	}

	/**
     * Metodo que analiza las Fichas, con ayuda de la gramatica.
     * @param entrada1 - llamada entrada, es la lista que da despues de usar el Fichador.
     * @return LinkedList - las Fichas en postorden.
     */
	@SuppressWarnings("unchecked") public LinkedList<Ficha> analizar(LinkedList<Ficha> entrada1){
		LinkedList<Ficha> operadores = new LinkedList<Ficha>();
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
					Ficha f1;
					if(i == 0)
						f1 = null;
					else
					 	f1 = entrada1.get(i-1);
					if(f1 != null && (f1.ficha == NUM || f1.ficha == PARE_CER || f1.ficha == LETRA)){
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
						}else{
							if(entrada.peek().ficha == PARE_AB || entrada.peek().ficha == FUNC){
								LinkedList<Ficha> particion = new LinkedList<Ficha>();
								while(entrada.peek().ficha != PARE_CER){
									particion.add(entrada.remove());
									i++;
									if(entrada.size() == 0)
										throw new ExcepcionCadenaInvalida("Parentesis desemparejados.");
								}
								particion.add(entrada.remove());
								i++;
								LinkedList<Ficha> l = analizar(particion);
								while(l.size() != 0)
									salida.add(l.removeFirst());
								salida.add(operadores.pop());
							}
						}
					}
				}else{
					if(operadores.size() == 0){
						operadores.push(f);
					}else{
						while((operadores.size() != 0 &&(f.ficha <= operadores.peek().ficha && f.ficha != POW) || (f.ficha == POW && f.ficha < operadores.peek().ficha))){
							salida.add(operadores.pop());
						}
						operadores.push(f);
					}
				}
			}
			if(f.ficha == PARE_AB){
				operadores.push(f);
			}
			if(f.ficha == PARE_CER){
				while(operadores.size() != 0 && operadores.peek().ficha != PARE_AB)
					salida.add(operadores.pop());
				if(operadores.size() == 0)
						throw new ExcepcionCadenaInvalida("Parentesis desemparejados.");
				if(operadores.peek().ficha == PARE_AB){
					operadores.pop();
					if(operadores.size() != 0 && operadores.peek().ficha == FUNC)
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