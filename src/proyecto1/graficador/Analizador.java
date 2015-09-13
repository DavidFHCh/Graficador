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
		int parentesis = 0;
		this.salida.clear();
		this.s = new Stack<Ficha>();
		Ficha f = new Ficha();
		for(int i = 0; i <= entrada.size();i++){
			f = entrada.pop();
			if(f.ficha == LETRA || f.ficha == NUM){ // si la ficha es numero o literal.
				this.salida.add(f);
				continue;
			}
			if(f.ficha == FUNC || f.ficha == PARE_AB){// si la ficha es un parentesis que abre o una funcion.
				this.s.push(f);
				parentesis++;
				continue;
			}
			if(f.ficha == PARE_CER){ // si la ficha es un parentesis que cierra.
				parentesis--;
				if(!this.s.empty()){
					while(this.s.peek().ficha > PARE_AB){
						if(this.s.peek().ficha != PARE_AB || this.s.peek().ficha != PARE_CER)
							this.salida.add(this.s.pop());
						if(this.s.empty())
							throw new ExcepcionCadenaInvalida("Parentesis desemparejados.");
					}
					if(this.s.peek().ficha == FUNC)
						this.salida.add(this.s.pop());
					else
						this.s.pop();
				}
				continue;
			}
			if(f.ficha == SUM_RES){ //Este caso es exclusivamente para operadores unarios.
				if(this.s.empty() || this.s.peek().ficha == PARE_AB ||  this.s.peek().ficha == MULT_DIV){
					f.setFicha(0);
					this.s.push(f);
					if(entrada.peek().ficha == PARE_AB || entrada.peek().ficha == FUNC){
						parentesis++;
						if(entrada.peek().ficha == FUNC)
							this.salida.add(entrada.pop());
						else
							entrada.pop();
						while(entrada.peek().ficha != PARE_CER){
							salida.add(entrada.pop());
							i--;
							if(entrada.size() == 0)
								throw new ExcepcionCadenaInvalida("Parentesis desemparejados.");
							if(entrada.peek().ficha == PARE_CER)
								parentesis--;
						}
						entrada.pop();
						i-=1;
					}else{
						this.salida.add(entrada.pop());
						this.salida.add(this.s.pop());
						i--;
						continue;
					}
				}
				continue;
			}
			if(f.ficha > PARE_CER){ // si es cualquier operacion binaria.
				if(!this.s.empty()){
					if((f.ficha <= this.s.peek().ficha && f.ficha != POW) || f.ficha < this.s.peek().ficha){
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
		if(parentesis != 0)
				throw new ExcepcionCadenaInvalida("Parentesis desemparejados.");
		while(!this.s.empty()){
			this.salida.add(this.s.pop());
		}
	}


}