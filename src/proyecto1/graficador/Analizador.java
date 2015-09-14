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
	private LinkedList<Ficha> s;

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
		this.s = new LinkedList<Ficha>();
		Ficha f = new Ficha();
		int tamaño = entrada.size();
		for(int i = 0; i < tamaño;i++){
			f = entrada.remove();
			if(f.ficha == LETRA || f.ficha == NUM){ // si la ficha es numero o literal.
				this.salida.add(f);
				continue;
			}
			if(f.ficha == FUNC || f.ficha == PARE_AB){// si la ficha es un parentesis que abre o una funcion.
				this.s.addFirst(f);
				parentesis++;
				continue;
			}
			if(f.ficha == PARE_CER){ // si la ficha es un parentesis que cierra.
				parentesis--;
				Ficha f3 = null;
				while(this.s.peek().ficha > FUNC){
					if(this.s.size() == 0)
						throw new ExcepcionCadenaInvalida("Parentesis desemparejados.");
					if(this.s.peek().ficha == PARE_AB){
						f3 = this.s.removeFirst();
						break;
					}
					this.salida.add(this.s.removeFirst());
				}
				if(this.s.size() != 0 && this.s.peek().ficha == FUNC && f3 == null)
					this.salida.add(this.s.removeFirst());
				continue;
			}
			if(f.ficha == SUM_RES){ //Este caso es exclusivamente para operadores unarios.
				if(i==0 || (this.s.size() != 0 && (this.s.peek().ficha == PARE_AB ||  this.s.peek().ficha == MULT_DIV))){
					f.setFicha(0);// se le cambia el valor de precedencia para poder reconocerlo mas adelante.
					this.s.add(f);
					if(entrada.peek().ficha == PARE_AB || entrada.peek().ficha == FUNC){// casos especiales para los parentesis despues de un signo unario.
						parentesis++;
						if(entrada.peek().ficha == FUNC){
							this.salida.add(entrada.remove());
							i++;
						}
						else{
							entrada.remove();
							i++;
						}
						while(entrada.peek().ficha != PARE_CER){
							salida.add(entrada.remove());
							i++;
							if(entrada.size() == 0)
								throw new ExcepcionCadenaInvalida("Parentesis desemparejados.");
						}
						if(entrada.peek().ficha == PARE_CER){
							parentesis--;
							entrada.remove();
							i++;
						}
					}else{
						this.salida.add(entrada.remove());
						this.salida.add(this.s.removeFirst());
						i++;
					}
					continue;
				}
			}
			if(f.ficha > PARE_CER){ // si es cualquier operacion binaria.
				if(this.s.size() != 0){
					switch(f.ficha){
						case POW:
							this.salida.add(f);
							break;
						case SUM_RES:
						case MULT_DIV:
							if(f.ficha <= this.s.peek().ficha){
								this.salida.add(this.s.removeFirst());
								this.s.addFirst(f);
							}else{
								this.s.addFirst(f);
							}
							break;
					}
				}else{
					this.s.addFirst(f);
				}
			}
		}
		if(parentesis != 0)//checa si hay parentesis sin pareja.
				throw new ExcepcionCadenaInvalida("Parentesis desemparejados.");
		while(this.s.size() != 0){
			this.salida.add(this.s.removeLast());
		}
	}


}