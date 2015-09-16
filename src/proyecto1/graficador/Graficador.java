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
	private double proporcionX;
	private double proporcionY;
	private int ancho;
	private int alto;
	private String funcion;
	LinkedList<Double> lDeRes;

	public Graficador(double x1,double y1,double x2, double y2, int ancho, int alto, String funcion){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.ancho = ancho;
		this.alto = alto;
		this.funcion = funcion;
		proporcionX = setProporcion(this.ancho,x1,x2);
		proporcionY = setProporcion(this.alto,y1,y2);
		lDeRes = new LinkedList<Double>();
	}

	public Graficador(int ancho,int alto,String funcion){
		this(-20,-20,20,20,ancho,alto,funcion);
	}

	public Graficador(String funcion){
		this(-20,-20,20,20,600,400,funcion);	
	}

	public void setX1(double x){
		this.x1 = x;
		proporcionX = setProporcion(this.ancho,this.x1,this.x2);
	}

	public void setX2(double x){
		this.x2 = x;
		proporcionX = setProporcion(this.ancho,this.x1,this.x2);
	}

	public void setY1(double x){
		this.y1 = x;
		proporcionY = setProporcion(this.alto,this.y1,this.y2);
	}

	public void setY2(double x){
		this.y2 = x;
		proporcionY = setProporcion(this.alto,this.y1,this.y2);
	}

	public void setAncho(int x){
		this.ancho = x;
		proporcionX = setProporcion(this.ancho,this.x1,this.x2);
	}

	public void setAlto(int x){
		this.alto = x;
		proporcionY = setProporcion(this.alto,this.y1,this.y2);
	}

	public LinkedList<Double> evalua(){
		LinkedList<Double> resultados = new LinkedList<Double>();
		double x0 = 0;
		Evaluador eval = new Evaluador();
		eval.hazFichas(funcion);
		eval.setSalida(eval.analizar(eval.getFichas()));
		while(x0 <= x2){
			resultados.add(eval.evalua(eval.getSalida(),x0,true));
			x0 += 1/proporcionX;
		}
		lDeRes = resultados;
		return resultados;
	}

	public LinkedList<String> aSVG(){
		GraficaSVG svg = new GraficaSVG(ancho,alto,proporcionX,proporcionY,x1,x2,y1,y2,lDeRes);
 		LinkedList<String> grafSvg = svg.getSalida();
 		return grafSvg;
	}

	private double setProporcion(int altoAncho, double x1, double x2){
		if(this.x2 < this.x1 || this.y2 < this.y1)
			throw new ExcepcionIntervalosInvalidos();
		double prop = altoAncho/(x2-x1);
		return prop;
	}

	public static void main(String[] args){
 		System.out.println("Holi"); 		
	}
}