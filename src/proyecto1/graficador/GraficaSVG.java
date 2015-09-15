package proyecto1.graficador;

import java.util.*;

public class GraficaSVG{

	private Queue<String> salida;
	private LinkedList<Double> valoresY;
	private double x1;
	private double x2;
	private double y1;
	private double y2;
	private int ancho;
	private int alto;
	private int proporcion;

	public GraficaSVG(int ancho, int alto,int proporcion ,double x1, double x2,double y1 , double y2, LinkedList<Double> valoresY){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.valoresY = valoresY;
		this.proporcion = proporcion;
		this.ancho = ancho;
		this.alto = alto;
	}

	private String linea(int x1, double y1){
		return "<line x1='" + x1 + "' y1='" + y1 + "' x2='" + x1 + "' y2='" + y1 + "' stroke='blue' stroke-width='2' />";
    }

    private String dimensiones(int alto, int ancho){
    	return "<svg width='" + ancho + "' height='" + 400 + "'>";
    }

    private String cierraSvg(){
    	return "</svg>";
    }

    
}