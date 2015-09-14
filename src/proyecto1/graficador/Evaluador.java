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
	* Metodo recursivo que evalua la expresion.
	* @param Una Linkedlist de fichas, lista para evaluarse.
	* @return El resultado de la operacion.
	*/
	@SuppressWarnings("unchecked") public double evalua(LinkedList<Ficha> aEvaluar,double x,boolean primera){
		double izq = 0;
		double der = 0;
		int k = 0;
		Ficha f1;
		LinkedList<Ficha> aEvaluar1 = aEvaluar;
		if(primera)
			aEvaluar1 = (LinkedList<Ficha>)aEvaluar.clone();
		//System.out.println("cosito ultima ficha" + aEvaluar1.peekLast().ficha);
		if(aEvaluar1.peekLast().ficha == NUM) //caso Base 1
			return Double.valueOf(aEvaluar1.pollLast().entrada);
		if(aEvaluar1.peekLast().ficha == LETRA){ //caso base 2
			aEvaluar1.pollLast();
			return x;
		}
		if(aEvaluar1.peekLast().ficha == SUM_RES){ //evalua sumas y restas
			f1 = aEvaluar1.pollLast();
			der = evalua(aEvaluar1,x,false);
			izq = evalua(aEvaluar1,x,false);
			if(f1.entrada.equals("+"))
				return izq + der;
			else
				return izq - der;
		}
		if(aEvaluar1.peekLast().ficha == MULT_DIV){//evalua multiplicaciones y divisiones
			f1 = aEvaluar1.pollLast();
			der = evalua(aEvaluar1,x,false);
			izq = evalua(aEvaluar1,x,false);
			if(f1.entrada.equals("*"))
				return izq * der;
			else{
				if(der != 0)
					return izq / der;
				else
					throw new ExcepcionNoEsUnNumero();
			}
		}
		if(aEvaluar1.peekLast().ficha == POW){// evalua potencias.
			int gorritos = 0;// numero de '^' que hay seguidos en la expresion.
			int numEval = 0;// numero de veces que se tiene que repetir la potencia.
			return elevar(aEvaluar1,potencias(aEvaluar1,gorritos),numEval,x);
		}
		if(aEvaluar1.peekLast().ficha == 0){// evalua a los signos unarios.
			if(aEvaluar1.pollLast().entrada == "-")
				return (-1)*evalua(aEvaluar1,x,false);
			else
				return evalua(aEvaluar1,x,false);
		}
		if(aEvaluar1.peekLast().ficha == 1){
			return funcion(aEvaluar1,x);
		}
		return 0;
	}

	private int potencias(LinkedList<Ficha> aEvaluar,int gorritos){
		aEvaluar.pollLast();
		gorritos++;
		if(aEvaluar.peekLast().ficha == POW)
			return potencias(aEvaluar,gorritos);
		return gorritos;
	}

	private double elevar(LinkedList<Ficha> aEvaluar, int gorritos, int numEval,double x){
		if(numEval < gorritos){// cuenta el numero de pows que se tienen que hacer.
			double der = evalua(aEvaluar,x,false);
			double izq =elevar(aEvaluar,gorritos,++numEval,x);
			return Math.pow(izq, der);
		}
		return evalua(aEvaluar,x,false);
	}

	private double funcion(LinkedList<Ficha> aEvaluar,double x){// para poder hacer mas funciones basta con agregarlas aqui y en la gramatica.
		String func = aEvaluar.pollLast().entrada;
		switch(func){
			case "sin(":
				return Math.sin(evalua(aEvaluar,x,false));
			case "cos(":
				return Math.cos(evalua(aEvaluar,x,false));
			case "tan(":
				return Math.tan(evalua(aEvaluar,x,false));
			case "cot(":
				return 1/Math.tan(evalua(aEvaluar,x,false));
			case "sec(":
				return 1/Math.cos(evalua(aEvaluar,x,false));
			case "csc(":
				return 1/Math.sin(evalua(aEvaluar,x,false));
			case "sqrt(":
				return Math.sqrt(evalua(aEvaluar,x,false));
		}
		throw new ExcepcionOperacionNoSoportada(func);
	}
}