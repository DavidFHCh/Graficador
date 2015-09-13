package proyecto1.graficador;

import java.util.*;

/**
 * Clase que realiza la Evaluacion
 */
public class Evaluador extends Analizador{

	/**
	* Constructor vacio.
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
			int gorritos = 0;
			int numEval = 0;
			return elevar(aEvaluar1,potencias(aEvaluar1,gorritos),numEval,x);
		}
	}

	private int potencias(LinkedList<Ficha> aEvaluar,int gorritos){
		aEvaluar.pop();
		gorritos++;
		if(aEvaluar.peek().ficha == POW)
			return potencias(aEvaluar,gorritos);
		return gorritos;
	}

	private double elevar(LinkedList<Ficha> aEvaluar, int gorritos, int numEval,double x){
		if(numEval++ <= gorritos){
			double der = evalua(aEvaluar,x);
			return Math.pow(elevar(aEvaluar,gorritos,numEval,x), der);
		}
		return evalua(aEvaluar,x);
	}
}