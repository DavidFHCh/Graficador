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
	public double evalua(LinkedList<Ficha> aEvaluar,double x){
		double izq = 0;
		double der = 0;
		if(aEvaluar.size() == 0)
			return 0;
		LinkedList<Ficha> aEvaluar1 = aEvaluar;
		if(aEvaluar1.peek().ficha == SUM_RES){
			der = evalua(aEvaluar1,x);
			izq = evalua(aEvaluar1,x);
			if(aEvaluar1.pop().entrada == "+")
				return izq + der;
			else
				return izq - der;
		}
		if(aEvaluar1.peek().ficha == MULT_DIV){
			der = evalua(aEvaluar1,x);
			izq = evalua(aEvaluar1,x);
			if(aEvaluar1.pop().entrada == "*")
				return izq * der;
			else{
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
		if(aEvaluar1.peek().ficha == 0){
			if(aEvaluar1.pop().entrada == "-")
				return (-1)*evalua(aEvaluar1,x);
			else
				return evalua(aEvaluar1,x);
		}
		if(aEvaluar1.peek().ficha == 1){
			funcion(aEvaluar1,x);
		}
	return Double.valueOf(aEvaluar1.pop().entrada);
	}

	private int potencias(LinkedList<Ficha> aEvaluar,int gorritos){
		aEvaluar.pop();
		gorritos++;
		if(aEvaluar.peek().ficha == POW)
			return potencias(aEvaluar,gorritos);
		return gorritos;
	}

	private double elevar(LinkedList<Ficha> aEvaluar, int gorritos, int numEval,double x){
		if(numEval++ <= gorritos){// cuenta el numero de pows que se tienen que hacer.
			double der = evalua(aEvaluar,x);
			return Math.pow(elevar(aEvaluar,gorritos,numEval,x), der);
		}
		return evalua(aEvaluar,x);
	}

	private double funcion(LinkedList<Ficha> aEvaluar,double x){
		String func = aEvaluar.pop().entrada;
		switch(func){
			case "sin(":
				return Math.sin(evalua(aEvaluar,x));
			case "cos(":
				return Math.cos(evalua(aEvaluar,x));
			case "tan(":
				return Math.tan(evalua(aEvaluar,x));
			case "cot(":
				return 1/Math.tan(evalua(aEvaluar,x));
			case "sec(":
				return 1/Math.cos(evalua(aEvaluar,x));
			case "csc(":
				return 1/Math.sin(evalua(aEvaluar,x));
			case "sqrt(":
				return Math.sqrt(evalua(aEvaluar,x));
		}
		throw new ExcepcionOperacionNoSoportada(func);
	}
}