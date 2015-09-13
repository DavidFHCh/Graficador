package proyecto1.graficador;

import java.util.*;

/**
 * Clase que realiza el Arobl Sintactico.
 */
public class Evaluador extends Analizador{

	/**
	* Crea un ArbolSintactico.
	* @param String s - La String que se analizara.
	*/
	public Evaluador(){
		super();
	}

	/**
	* Metodo que evalua la expresion.
	* @param Una Linkedlist de fichas, lista para evaluarse.
	* @return El resultado de la operacion.
	*/
	public double evalua(LinkedList<Fichas> aEvualar,double x){
		double izq = 0;
		double der = 0;
		if(aEvaluar.size() == 0)
			return 0;
		LinkedList<Ficha> aEvaluar1 = aEvaluar;
		if(aEvaluar1.peek().ficha == NUM)
			return Double.valueOf(aEvaluar1.pop());
		if(aEvaluar1.peek().ficha == SUM_RES){
			der = evalua(aEvaluar,x);
			izq = evalua(aEvaluar,x);
			if(aEvaluar1.pop().entrada == "+")
				return izq + der;
			else{
				aEvaluar1.pop();
				return izq - der;
			}
		}
		if(aEvaluar1.peek().ficha == MULT_DIV){
			der = evalua(aEvaluar,x);
			izq = evalua(aEvaluar,x);
			if(aEvaluar1.pop().entrada == "*")
				return izq * der;
			else{
				aEvaluar1.pop();
				if(der != 0)
					return izq / der;
				else
					throw new ExcepcionNoEsUnNumero();
			}
		}
		if(aEvaluar1.peek().ficha == POW){
			aEvaluar1.pop();
			der = evalua(aEvaluar,x);
			izq = evalua(aEvaluar,x);
			return Math.pow(izq,der);
		}
	}

	private double potencia(LinkedList<Ficha> aEvaluar){

	}
}