/*
* @author David Hernández. 
*/
package proyecto1.graficador;

import java.util.*;

public class Graficador{

	private double x1;
	private double x2;
	private double y1;
	private double y2;
	private double x0;
	private double proporcionX;
	private double proporcionY;
	private int ancho;
	private int alto;
	private String funcion;

	public Graficador(double x1,double y1,double x2, double y2, int ancho, int alto, String funcion){

	}

	public static void main(String[] args){
		LinkedList<LinkedList<Double>> ldeRes = new LinkedList<LinkedList<Double>>();
		double x1 = 0;
		double x2 = 10;
		double y1 = -5;
		double y2 = 10;
		double x0 = x1;
		int ancho = 800;
		int alto = 600;
		int pixeles = 0;
		double proporcionX = 0;
		double proporcionY = 0;
		LinkedList<Double> resultados = new LinkedList<Double>();
		if(x2 < x1 || y2 < y1)
			throw new ExcepcionIntervalosInvalidos();
		proporcionY = alto/(y2-y1);
		proporcionX = (ancho/(x2-x1));
		while(proporcionX == 0){
			proporcionX = (pixeles*(ancho/x2-x1));
			pixeles++;
		}
		pixeles = 0;
		while(proporcionY == 0){
			proporcionY = (pixeles*(ancho/y2-y1));
			pixeles++;
		}
		Evaluador eval = new Evaluador();
		eval.hazFichas("x^3");
		eval.setSalida(eval.analizar(eval.getFichas()));
		while(x0 <= x2){
			resultados.add(eval.evalua(eval.getSalida(),x0,true));
			x0 += 1/proporcionX;
		}
		ldeRes.add(resultados);
 		GraficaSVG svg = new GraficaSVG(ancho,alto,proporcionX,proporcionY,x1,x2,y1,y2,ldeRes);
 		LinkedList<String> grafSvg = svg.getSalida();
 		while(grafSvg.peek() != null){
 			System.out.println(grafSvg.poll());
 		}
	}
}