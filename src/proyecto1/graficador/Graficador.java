/*
* @author David Hernández. 
*/
package proyecto1.graficador;

import java.util.*;

public class Graficador{

	public static void main(String[] args){
		double x1 = -10;
		double x2 = 10;
		double y1 = -10;
		double y2 = 10;
		double x0 = x1;
		int ancho = 800;
		int alto = 600;
		int pixeles = 0;
		int proporcion = 0;
		LinkedList<Double> resultados = new LinkedList<Double>();
		if(x2 < x1 || y2 < y1)
			throw new ExcepcionIntervalosInvalidos();
		proporcion = (int)(ancho/(x2-x1));
		while(proporcion == 0){
			proporcion = (int)(pixeles*(ancho/x2-x1));
			pixeles++;
		}
		Evaluador eval = new Evaluador();
		eval.hazFichas("x");
		eval.setSalida(eval.analizar(eval.getFichas()));
		while(x0 <= x2){
			resultados.add(eval.evalua(eval.getSalida(),x0,true));
			x0 += 1/proporcion;
		}
 		GraficaSVG svg = new GraficaSVG(800,600,proporcion,x1,x2,y1,y2,resultados);
	}
}